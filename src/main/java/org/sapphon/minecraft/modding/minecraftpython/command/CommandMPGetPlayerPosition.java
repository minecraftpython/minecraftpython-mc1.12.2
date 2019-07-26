package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

public class CommandMPGetPlayerPosition{
	
	private String nameOfPlayer = "";
	
	public CommandMPGetPlayerPosition(){
		
	}
	public CommandMPGetPlayerPosition(String playerNameToGetPositionFor){
		nameOfPlayer = playerNameToGetPositionFor;
	}
	
	public int[] execute(){
		EntityPlayer player = getCorrectPlayer();
		return new int[]{
			(int)Math.round(player.posX),
			(int)Math.round(player.posY + player.getEyeHeight()),
			(int)Math.round(player.posZ)
		};
	}
	
	private EntityPlayer getCorrectPlayer() {
		if(nameOfPlayer.equals("")){
			 return Minecraft.getMinecraft().player;
		}
		else{
			EntityPlayer possibleAnswer = Minecraft.getMinecraft().world.getPlayerEntityByName(nameOfPlayer);//Note this compares by getCommandSenderName whereas GameStart uses DisplayNames.  Never been a problem...yet.
			if(possibleAnswer == null){
				JavaProblemHandler.printErrorMessageToDialogBox(new Exception("Problem finding player " + this.nameOfPlayer +  " by name.  Are you sure that player exists on this server?"));
				possibleAnswer = Minecraft.getMinecraft().player;	//this isn't great behavior; we're just returning the only player we know for sure we have.  TODO would be implementing a DoNothingPlayerEntity to return.
			}
			return possibleAnswer;
		}
	}
}
