package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sapphon.minecraft.modding.minecraftpython.async.ThreadFactory;
import org.sapphon.minecraft.modding.minecraftpython.event.MagicItemEventHandler;
import org.sapphon.minecraft.modding.minecraftpython.event.MinecraftPythonKeyHandler;
import org.sapphon.minecraft.modding.minecraftpython.io.file.MinecraftPythonScriptLoader;
import org.sapphon.minecraft.modding.minecraftpython.io.file.ScriptLoaderConstants;
import org.sapphon.minecraft.modding.minecraftpython.item.recipe.RecipePythonicWand;
import org.sapphon.minecraft.modding.minecraftpython.network.meta.PacketHandlerMinecraftPythonMeta;
import org.sapphon.minecraft.modding.minecraftpython.network.meta.PacketMinecraftPythonDeductExperience;
import org.sapphon.minecraft.modding.minecraftpython.network.python.PacketHandlerMinecraftPythonClientCommand;
import org.sapphon.minecraft.modding.minecraftpython.network.python.PacketHandlerMinecraftPythonServerCommand;
import org.sapphon.minecraft.modding.minecraftpython.network.python.PacketMinecraftPythonClientCommand;
import org.sapphon.minecraft.modding.minecraftpython.network.python.PacketMinecraftPythonServerCommand;
import org.sapphon.minecraft.modding.minecraftpython.proxy.ClientProxy;
import org.sapphon.minecraft.modding.minecraftpython.proxy.CommonProxy;
import org.sapphon.minecraft.modding.minecraftpython.proxy.DedicatedServerProxy;

@Mod(modid = MinecraftPythonMod.MODID, version = MinecraftPythonMod.VERSION, name = MinecraftPythonMod.MODID)
public class MinecraftPythonMod {
    public static final String MODID = "minecraftpython";
    public static final String VERSION = "1.12.2-0.7.2";
    public static final int SCRIPT_RUN_COOLDOWN = 1500;
    public static final Logger logger = LogManager.getLogger(MinecraftPythonMod.MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.Instance(value = MinecraftPythonMod.MODID)
    public static MinecraftPythonMod instance;

    @SidedProxy(clientSide = "org.sapphon.minecraft.modding.minecraftpython.proxy.CombinedClientProxy", serverSide = "org.sapphon.minecraft.modding.minecraftpython.proxy.DedicatedServerProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper serverCommandPacketChannel;
    public static SimpleNetworkWrapper clientCommandPacketChannel;
    public static SimpleNetworkWrapper serverMetaPacketChannel;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (isEnabled() && !(proxy instanceof DedicatedServerProxy)) {
            if (!ScriptLoaderConstants.resourcePathExists()) {
                ScriptLoaderConstants.setResourcePath(event);
            }
        }
        registerMetaPacketChannels();
        registerPythonCommandPacketChannels();
    }

    private void registerPythonCommandPacketChannels() {
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

    private void registerMetaPacketChannels() {
        serverMetaPacketChannel = NetworkRegistry.INSTANCE
                .newSimpleChannel("MPServerMeta");
        serverMetaPacketChannel.registerMessage(
                PacketHandlerMinecraftPythonMeta.class,
                PacketMinecraftPythonDeductExperience.class, 0, Side.SERVER);
    }

    private boolean isEnabled() {
        return ModConfigurationFlags.MINECRAFT_PYTHON_PROGRAMMING();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        if (!(proxy instanceof DedicatedServerProxy)) {
            MinecraftForge.EVENT_BUS.register(
                    new MinecraftPythonKeyHandler(
                            MinecraftPythonScriptLoader.SINGLETON()
                                    .getMagicVessel()));
            MinecraftForge.EVENT_BUS.register(new MagicItemEventHandler());
        }
        GameRegistry.findRegistry(IRecipe.class).register(RecipePythonicWand.SINGLETON());
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (!(proxy instanceof ClientProxy) && ModConfigurationFlags.HTTP_INTERFACE_ENABLED()) {
            ThreadFactory.makeHttpListenerRunnable().start();
        }
    }

}