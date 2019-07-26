package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;

public abstract class CommandMinecraftPythonClient extends CommandMinecraftPythonAbstract {
	protected String targetPlayerName;
	public static final String CHANGESETTINGS_NAME = "cs";
	public static final String SPAWNPARTICLE_NAME = "pa";
	public static final String SECRETSETTINGS_NAME= "ss";
	public abstract String serialize();
	protected abstract String getTargetPlayerName();
	public boolean isIntendedForThisClient(Minecraft minecraft){
		return minecraft.player.getDisplayName().equals(this.getTargetPlayerName());
	}
}
