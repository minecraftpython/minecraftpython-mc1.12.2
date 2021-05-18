package org.sapphon.minecraft.modding.minecraftpython.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.minecraftpython.command.queue.ClientTickHandler;
import org.sapphon.minecraft.modding.minecraftpython.command.queue.ServerTickHandler;

public class CombinedClientProxy extends CommonProxy {
	public CombinedClientProxy() {
		MinecraftForge.EVENT_BUS
				.register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new ServerTickHandler());
	}
}