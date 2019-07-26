package org.sapphon.minecraft.modding.minecraftpython.command;

import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonMod;
import org.sapphon.minecraft.modding.minecraftpython.PacketMinecraftPythonClientCommand;

import java.util.ArrayList;

public class CommandQueueServerSide extends CommandQueueAbstract {

	private static CommandQueueServerSide SINGLETON;

	private CommandQueueServerSide() {
		scheduledCommands = new ArrayList<ICommand>();
	}

	public synchronized void scheduleCommand(ICommand command) {
		if (command instanceof CommandMinecraftPythonServer) {
			this.scheduledCommands.add(command);
		} else if (command instanceof CommandMinecraftPythonClient) {
			CommandMinecraftPythonClient cast = (CommandMinecraftPythonClient) command;
			MinecraftPythonMod.clientCommandPacketChannel.sendToAll(new PacketMinecraftPythonClientCommand(
					cast));
		}
	}

	public static ICommandQueue SINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new CommandQueueServerSide();
		}
		return SINGLETON;
	}

}
