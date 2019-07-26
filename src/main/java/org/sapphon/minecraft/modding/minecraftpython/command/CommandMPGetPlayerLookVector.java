package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;

public class CommandMPGetPlayerLookVector {
	public double[] execute(){
		Minecraft minecraft = Minecraft.getMinecraft();
		Vec3d lookVector = minecraft.thePlayer.getLook(1.0f);
		return new double[]{
			lookVector.x,
			lookVector.y,
			lookVector.z
		};
	}
}
