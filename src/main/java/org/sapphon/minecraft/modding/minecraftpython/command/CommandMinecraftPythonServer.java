package org.sapphon.minecraft.modding.minecraftpython.command;


public abstract class CommandMinecraftPythonServer extends CommandMinecraftPythonAbstract {
    //items
    public static final String TAKEITEM_NAME = "ti";
    public static final String ENSORCELITEM_NAME = "ei";
    public static final String SPAWNITEM_NAME = "si";
    //blocks
    public static final String SETBLOCK_NAME = "sb";
    //entities
    public static final String SPAWNENTITY_NAME = "se";
    public static final String TELEPORT_NAME = "tp";
    public static final String LIGHTNINGBOLT_NAME = "lb";
    public static final String PROPEL_NAME = "pr";
    //general
    public static final String BROADCAST_NAME = "bc";
    public static final String CONSOLECOMMAND_NAME = "cc";
    public static final String CREATEEXPLOSION_NAME = "ce";

    public abstract String serialize();

}
