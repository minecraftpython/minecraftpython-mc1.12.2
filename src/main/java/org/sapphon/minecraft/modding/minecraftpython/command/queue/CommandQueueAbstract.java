package org.sapphon.minecraft.modding.minecraftpython.command.queue;

import org.sapphon.minecraft.modding.minecraftpython.command.ICommand;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandQueueAbstract implements ICommandQueue {

    public static final long MAXIMUM_MILLIS_PER_TICK_TO_USE_ON_COMMANDS = 200;
    public static final boolean LIMIT_COMMAND_TIME_PER_TICK = false;
    protected boolean slowModeEnabled = false;
    protected List<ICommand> scheduledCommands;


    @Override
    public synchronized void runAndClearScheduledCommands() {
        if (LIMIT_COMMAND_TIME_PER_TICK) {
            runSomeCommandsAndClearThoseThatWereRun();
        } else {
            runAndClearAllCommands();
        }
    }

    private void runAndClearAllCommands() {
        for (ICommand command : this.scheduledCommands) {
            command.doWork();
        }
        this.scheduledCommands.clear();

    }

    private void runSomeCommandsAndClearThoseThatWereRun() {
        long timeToStopCommandExecutionProcess = System.currentTimeMillis() + MAXIMUM_MILLIS_PER_TICK_TO_USE_ON_COMMANDS;
        ArrayList<ICommand> copyOfCommandListToIterateOver = new ArrayList<ICommand>(this.scheduledCommands);

        for (ICommand scheduledCommand : copyOfCommandListToIterateOver) {
            scheduledCommand.doWork();
            this.scheduledCommands.remove(scheduledCommand);
            if (System.currentTimeMillis() > timeToStopCommandExecutionProcess) {
                break;
            }

        }
    }

    @Override
    public void toggleSlowMode() {
        this.slowModeEnabled = true;
    }
}
