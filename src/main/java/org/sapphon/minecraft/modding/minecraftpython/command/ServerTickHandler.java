package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ServerTickHandler {
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event){
		if(event.phase.compareTo(TickEvent.Phase.END) == 0){
			CommandQueueServerSide.SINGLETON().runAndClearScheduledCommands();
		}
	}
}
