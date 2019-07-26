package org.sapphon.minecraft.modding.minecraftpython.command;

import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonMod;

import java.util.ArrayList;

public class CommandQueueClientSide extends CommandQueueAbstract {
	private static CommandQueueClientSide SINGLETON;
	
	public static CommandQueueClientSide SINGLETON(){
		if(SINGLETON == null)
			SINGLETON = new CommandQueueClientSide();
		return SINGLETON;
	}
	
	private CommandQueueClientSide(){
		scheduledCommands = new ArrayList<ICommand>();
	}

	@Override
	public synchronized void scheduleCommand(ICommand command) {
		if(command instanceof CommandMinecraftPythonClient){
			this.scheduledCommands.add(command);
		}
		else if (command instanceof CommandMinecraftPythonServer){
			sendToServerQueue(command);
		}
	}

	private void sendToServerQueue(ICommand command) {
		CommandMinecraftPythonServer cast = (CommandMinecraftPythonServer)command;
		MinecraftPythonMod.serverCommandPacketChannel.sendToServer(new PacketMinecraftPythonServerCommand(cast));
	}
}
