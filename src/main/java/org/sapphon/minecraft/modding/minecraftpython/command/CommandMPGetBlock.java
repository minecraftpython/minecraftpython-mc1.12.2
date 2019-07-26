package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class CommandMPGetBlock {

	private int x;
	private int y;
	private int z;

	public CommandMPGetBlock(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public CommandMPGetBlock(double x, double y, double z){
		this((int)x,(int)y,(int)z);
	}
	public String execute() {
		return Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(x, y, z)).getBlock().getRegistryName().getResourcePath();
	}
}
