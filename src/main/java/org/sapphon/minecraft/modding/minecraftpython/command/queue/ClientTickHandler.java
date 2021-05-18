package org.sapphon.minecraft.modding.minecraftpython.command.queue;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientTickHandler {
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event){
		if(event.phase.compareTo(TickEvent.Phase.END) == 0){
			CommandQueueClientSide.SINGLETON().runAndClearScheduledCommands();
		}
	}
}
