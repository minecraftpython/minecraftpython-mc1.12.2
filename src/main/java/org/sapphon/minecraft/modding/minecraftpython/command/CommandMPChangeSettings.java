package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;

public class CommandMPChangeSettings extends CommandMinecraftPythonClient {

    private String targetName;

    public CommandMPChangeSettings(String targetPlayerName) {
        this.targetName = targetPlayerName;
    }

    public CommandMPChangeSettings(String[] commandAndArgsToDeserialize) {
        this(commandAndArgsToDeserialize[1]);
    }

    @Override
    public void doWork() {
        Minecraft.getMinecraft().gameSettings.setOptionFloatValue(Options.FOV, 0);
        Minecraft.getMinecraft().gameSettings.setOptionValue(Options.RENDER_DISTANCE, Math.min(Minecraft.getMinecraft().gameSettings.renderDistanceChunks, 4));
        Minecraft.getMinecraft().gameSettings.setOptionValue(Options.FRAMERATE_LIMIT, 24);
        Minecraft.getMinecraft().gameSettings.setOptionValue(Options.GRAPHICS, 0);
        Minecraft.getMinecraft().gameSettings.saveOptions();
    }

    @Override
    public String serialize() {
        return CHANGESETTINGS_NAME + SERIAL_DIV + targetName;
    }

    @Override
    protected String getTargetPlayerName() {
        return this.targetName;
    }

}
