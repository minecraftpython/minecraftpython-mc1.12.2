package org.sapphon.minecraft.modding.base;

import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.minecraftpython.command.ServerTickHandler;

public class DedicatedServerProxy extends CommonProxy {
	public DedicatedServerProxy(){
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
	}
}
