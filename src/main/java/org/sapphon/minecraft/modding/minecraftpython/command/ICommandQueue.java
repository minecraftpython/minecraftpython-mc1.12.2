package org.sapphon.minecraft.modding.minecraftpython.command;

public interface ICommandQueue {
	public void scheduleCommand(ICommand command);
	public void runAndClearScheduledCommands();
	public void toggleSlowMode();
}
