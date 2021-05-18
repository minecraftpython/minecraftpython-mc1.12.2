package org.sapphon.minecraft.modding.minecraftpython.command.queue;

import org.sapphon.minecraft.modding.minecraftpython.command.ICommand;

public interface ICommandQueue {
	public void scheduleCommand(ICommand command);
	public void runAndClearScheduledCommands();
	public void toggleSlowMode();
}
