package org.sapphon.minecraft.modding.minecraftpython.network.python;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.sapphon.minecraft.modding.minecraftpython.command.*;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

public class PacketMinecraftPythonServerCommand implements IMessage {
	

	private String commandName;
	public CommandMinecraftPythonServer command;

	public PacketMinecraftPythonServerCommand() {

	}

	public PacketMinecraftPythonServerCommand(CommandMinecraftPythonServer commandToPackUp){
		this.command = commandToPackUp;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		String text = ByteBufUtils.readUTF8String(buf);
		String[] commandAndArgsToDeserialize = text.split(CommandMinecraftPythonAbstract.SERIAL_DIV);
		commandName = commandAndArgsToDeserialize[0].trim();

		if(commandName.equals(CommandMPSetBlock.SETBLOCK_NAME)){
			command = new CommandMPSetBlock(commandAndArgsToDeserialize);
			
		} else if (commandName.equals(CommandMinecraftPythonServer.CREATEEXPLOSION_NAME)) {
			command = new CommandMPCreateExplosion(commandAndArgsToDeserialize);
	
		} else if (commandName.equals(CommandMinecraftPythonServer.SPAWNENTITY_NAME)) {
			command = new CommandMPSpawnEntity(commandAndArgsToDeserialize);
			
		} else if (commandName.equals(CommandMinecraftPythonServer.TELEPORT_NAME)) {
			command = new CommandMPTeleport(commandAndArgsToDeserialize);
			
		} else if (commandName.equals(CommandMinecraftPythonServer.BROADCAST_NAME)) {
			command = new CommandMPBroadcast(commandAndArgsToDeserialize);
			
		} else if (commandName.equals(CommandMinecraftPythonServer.LIGHTNINGBOLT_NAME)) {
			command = new CommandMPSpawnLightningBolt(commandAndArgsToDeserialize);
		} else if(commandName.equals(CommandMinecraftPythonServer.PROPEL_NAME)){
			command = new CommandMPPropelEntity(commandAndArgsToDeserialize);
		}else if(commandName.equals(CommandMinecraftPythonServer.SPAWNITEM_NAME)){
			command = new CommandMPSpawnItem(commandAndArgsToDeserialize);
		}else if(commandName.equals(CommandMinecraftPythonServer.CONSOLECOMMAND_NAME)){
			command = new CommandMPExecuteConsoleCommand(commandAndArgsToDeserialize);
		}
		else {
			JavaProblemHandler.printErrorMessageToDialogBox(new Exception(
					"A server-side command  (type " + commandName
							+ ")'s packet could not be interpreted."));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		String serializedCommand = command.serialize();
		ByteBufUtils.writeUTF8String(buf, serializedCommand);
	}

}
