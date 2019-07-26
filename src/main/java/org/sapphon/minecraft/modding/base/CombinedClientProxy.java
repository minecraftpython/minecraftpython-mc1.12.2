package org.sapphon.minecraft.modding.base;

import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.minecraftpython.command.ClientTickHandler;
import org.sapphon.minecraft.modding.minecraftpython.command.ServerTickHandler;

public class CombinedClientProxy extends CommonProxy {
	public CombinedClientProxy() {
		FMLCommonHandler.instance().bus()
				.register(new ClientTickHandler());
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
	}
}