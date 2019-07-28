package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sapphon.minecraft.modding.base.ClientProxy;
import org.sapphon.minecraft.modding.base.CommonProxy;
import org.sapphon.minecraft.modding.base.DedicatedServerProxy;
import org.sapphon.minecraft.modding.base.ModConfigurationFlags;
import org.sapphon.minecraft.modding.minecraftpython.command.PacketHandlerMinecraftPythonServerCommand;
import org.sapphon.minecraft.modding.minecraftpython.command.PacketMinecraftPythonServerCommand;
import org.sapphon.minecraft.modding.minecraftpython.spells.ThreadFactory;

@Mod(modid = MinecraftPythonMod.MODID, version = MinecraftPythonMod.VERSION, name = MinecraftPythonMod.MODID)
public class MinecraftPythonMod {
	public static final String MODID = "minecraftpython";
	public static final String VERSION = "1.12.2-0.6.0";
	public static final int SCRIPT_RUN_COOLDOWN = 1500;
	public static final Logger logger = LogManager.getLogger(MinecraftPythonMod.MODID);
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Mod.Instance(value = MinecraftPythonMod.MODID)
	public static MinecraftPythonMod instance;

	@SidedProxy(clientSide = "org.sapphon.minecraft.modding.base.CombinedClientProxy", serverSide = "org.sapphon.minecraft.modding.base.DedicatedServerProxy")
	public static CommonProxy proxy;
	public static SimpleNetworkWrapper serverCommandPacketChannel;
	public static SimpleNetworkWrapper clientCommandPacketChannel;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (isEnabled()) {
			if(!ScriptLoaderConstants.resourcePathExists()){
				ScriptLoaderConstants.setResourcePath(event);
			}
				serverCommandPacketChannel = NetworkRegistry.INSTANCE
						.newSimpleChannel("MPServerCommand");
				serverCommandPacketChannel.registerMessage(
						PacketHandlerMinecraftPythonServerCommand.class,
						PacketMinecraftPythonServerCommand.class, 0, Side.SERVER);
				clientCommandPacketChannel = NetworkRegistry.INSTANCE
						.newSimpleChannel("MPClientCommand");
				clientCommandPacketChannel.registerMessage(
						PacketHandlerMinecraftPythonClientCommand.class,
						PacketMinecraftPythonClientCommand.class, 0, Side.CLIENT);
		}
	}

	private boolean isEnabled() {
		return ModConfigurationFlags.MINECRAFT_PYTHON_PROGRAMMING();
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		if (isEnabled() && !(proxy instanceof DedicatedServerProxy)) {
			FMLCommonHandler
					.instance()
					.bus()
					.register(
							new MinecraftPythonKeyHandler(
									MinecraftPythonScriptLoader.SINGLETON()
											.getMagicVessel()));
		}
		proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (!(proxy instanceof ClientProxy) && ModConfigurationFlags.MPPM_WEB()) {
			ThreadFactory.makeJavaGameLoopThread().start();
		}
	}
	
}