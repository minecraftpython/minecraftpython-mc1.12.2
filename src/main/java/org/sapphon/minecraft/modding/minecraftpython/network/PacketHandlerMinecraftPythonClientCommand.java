package org.sapphon.minecraft.modding.minecraftpython.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.sapphon.minecraft.modding.minecraftpython.command.queue.CommandQueueClientSide;

public class PacketHandlerMinecraftPythonClientCommand implements IMessageHandler<PacketMinecraftPythonClientCommand, IMessage> {

	@Override
	public IMessage onMessage(PacketMinecraftPythonClientCommand message,
							  MessageContext ctx) {
		CommandQueueClientSide.SINGLETON().scheduleCommand(message.getCommand());
		return null;
	}

}
