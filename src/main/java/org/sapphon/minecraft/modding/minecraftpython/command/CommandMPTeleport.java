package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;
import java.util.List;

public class CommandMPTeleport extends CommandMinecraftPythonServer {

	public double x;
	public double y;
	public double z;
	public String teleportingPlayer;

	public CommandMPTeleport(double x, double y, double z){
		this(x,y,z, Minecraft.getMinecraft().thePlayer.getDisplayName().getUnformattedText());
	}
	
	public CommandMPTeleport(double x, double y, double z, String teleportingPlayerDisplayName){
		this.x = x;
		this.y = y;
		this.z = z;
		this.teleportingPlayer = teleportingPlayerDisplayName;
	}
	
	public CommandMPTeleport(String[] commandAndArgsToDeserialize) {
		this(Double.parseDouble(commandAndArgsToDeserialize[1]),
				Double.parseDouble(commandAndArgsToDeserialize[2]),
				Double.parseDouble(commandAndArgsToDeserialize[3]), commandAndArgsToDeserialize[4]);
	}


	public void doWork(){
		WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
		List<EntityPlayer> players = new ArrayList<EntityPlayer>(world.playerEntities);
		for (EntityPlayer entityPlayerMP : players) {
			if(entityPlayerMP.getDisplayName().getUnformattedText().equals(teleportingPlayer)){
				entityPlayerMP.setPositionAndUpdate(x,y,z);
				return;
			}
		}
	}
	
	@Override
	public String serialize() {
		return CommandMinecraftPythonServer.TELEPORT_NAME + CommandMinecraftPythonAbstract.SERIAL_DIV + x + CommandMinecraftPythonAbstract.SERIAL_DIV + y + CommandMinecraftPythonAbstract.SERIAL_DIV + z + CommandMinecraftPythonAbstract.SERIAL_DIV + teleportingPlayer;
	}
	
	
}