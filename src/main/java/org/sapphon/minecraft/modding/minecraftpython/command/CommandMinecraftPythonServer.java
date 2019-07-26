package org.sapphon.minecraft.modding.minecraftpython.command;


public abstract class CommandMinecraftPythonServer extends CommandMinecraftPythonAbstract {

	public static final String CREATEEXPLOSION_NAME = "ce"; 
	public static final String SETBLOCK_NAME = "sb";
	public static final String SPAWNENTITY_NAME = "se";     
	public static final String TELEPORT_NAME = "tp";        
	public static final String BROADCAST_NAME = "bc";       
	public static final String LIGHTNINGBOLT_NAME = "lb";      
	public static final String PROPEL_NAME = "pr";
	public static final String SPAWNITEM_NAME = "si";
	public static final String CONSOLECOMMAND_NAME = "cc";
	
	public abstract String serialize() ;
	
}
