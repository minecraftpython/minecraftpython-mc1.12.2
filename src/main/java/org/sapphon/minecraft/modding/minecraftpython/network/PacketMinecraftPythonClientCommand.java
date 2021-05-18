package org.sapphon.minecraft.modding.minecraftpython.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.sapphon.minecraft.modding.minecraftpython.command.*;

public class PacketMinecraftPythonClientCommand implements IMessage {

	
	private CommandMinecraftPythonClient command;

	public PacketMinecraftPythonClientCommand(CommandMinecraftPythonClient commandToPackUp){
		this.command = commandToPackUp;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		String text = ByteBufUtils.readUTF8String(buf);
		String[] commandAndArgsToDeserialize = text.split(CommandMinecraftPythonAbstract.SERIAL_DIV);
		String commandName = commandAndArgsToDeserialize[0].trim();

		if(commandName.equals(CommandMinecraftPythonClient.SPAWNPARTICLE_NAME)){
			command = new CommandMPSpawnParticle(commandAndArgsToDeserialize);
			
		} else if (commandName.equals(CommandMinecraftPythonClient.SECRETSETTINGS_NAME)) {
			command = new CommandMPApplyShader(commandAndArgsToDeserialize);
	
		} else if (commandName.equals(CommandMinecraftPythonClient.CHANGESETTINGS_NAME)) {
			command = new CommandMPChangeSettings(commandAndArgsToDeserialize);	
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		String serializedCommand = command.serialize();
		ByteBufUtils.writeUTF8String(buf, serializedCommand);
	}

	public CommandMinecraftPythonClient getCommand() {
		return command;
	}
}
