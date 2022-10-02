package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

public class CommandMPGetPlayerLookedAtBlock {

    private String nameOfPlayer = "";

    public CommandMPGetPlayerLookedAtBlock() {

    }

    public CommandMPGetPlayerLookedAtBlock(String playerNameToGetPositionFor) {
        nameOfPlayer = playerNameToGetPositionFor;
    }

    public int[] execute() {
        EntityPlayer player = determineObjectOfCommand();
        RayTraceResult rayTraceResult = player.rayTrace(1024, 0.5f);
        BlockPos blockPos = rayTraceResult.getBlockPos();
        ;
        return new int[]{
                blockPos.getX(),
                blockPos.getY(),
                blockPos.getZ()
        };
    }

    private EntityPlayer determineObjectOfCommand() {
        if (nameOfPlayer.equals("")) {
            return Minecraft.getMinecraft().player;
        } else {
            EntityPlayer possibleAnswer = Minecraft.getMinecraft().world.getPlayerEntityByName(nameOfPlayer);
            if (possibleAnswer == null) {
                JavaProblemHandler.printErrorMessageToDialogBox(new Exception("Problem finding player " + this.nameOfPlayer + " by name.  Are you sure that player exists on this server?"));
                possibleAnswer = Minecraft.getMinecraft().player;
            }
            return possibleAnswer;
        }
    }
}
