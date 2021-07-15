package org.sapphon.minecraft.modding.minecraftpython.command;

import org.sapphon.minecraft.modding.minecraftpython.command.queue.CommandQueueClientSide;

public abstract class CommandMinecraftPythonAbstract implements ICommand {

    public static final String SERIAL_DIV = "KwK40";

    @Override
    public void execute() {
        CommandQueueClientSide.SINGLETON().scheduleCommand(this);
    }
}
