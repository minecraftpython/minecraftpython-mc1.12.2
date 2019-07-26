package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandMPBroadcast extends CommandMinecraftPythonServer {

	public String toBroadcast;

	public CommandMPBroadcast(String toBroadcast) {
		this.toBroadcast=toBroadcast;
	}

	public CommandMPBroadcast(String[] commandAndArgsToDeserialize) {
		toBroadcast=commandAndArgsToDeserialize[1];
	}

	@Override
	public void doWork() {
		FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendChatMsg(new TextComponentString(this.toBroadcast));
	}

	@Override
	public String serialize() {
		return CommandMinecraftPythonServer.BROADCAST_NAME + CommandMinecraftPythonAbstract.SERIAL_DIV + toBroadcast;
	}


}
