package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;

public class CommandMPApplyShader extends CommandMinecraftPythonClient {

	private String targetName;

	public CommandMPApplyShader(String targetName) {
		this.targetName = targetName;
	}

	public CommandMPApplyShader(String[] commandAndArgsToDeserialize) {
		this(commandAndArgsToDeserialize[1]);
	}

	@Override
	public void doWork() {
		Minecraft minecraft = Minecraft.getMinecraft();
			minecraft.entityRenderer.switchUseShader();
	}

	@Override
	public String serialize() {
		return SECRETSETTINGS_NAME + SERIAL_DIV + targetName;
	}

	@Override
	protected String getTargetPlayerName() {
		return targetName;
	}

}
