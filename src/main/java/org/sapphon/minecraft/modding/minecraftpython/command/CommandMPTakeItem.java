package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;

public class CommandMPTakeItem extends CommandMinecraftPythonServer {

    private String playerName;

    public CommandMPTakeItem(String playerName) {

        this.playerName = playerName;
    }

    public CommandMPTakeItem(String[] commandAndArgsToDeserialize) {
        this.playerName = commandAndArgsToDeserialize[1];
    }

    @Override
    public String serialize() {
        return CommandMinecraftPythonServer.TAKEITEM_NAME + SERIAL_DIV + playerName;
    }

    @Override
    public void doWork() {
        EntityPlayerMP playerFound = PlayerHelper.getPlayerByUsernameOrNull(FMLCommonHandler.instance().getMinecraftServerInstance(), playerName);
        if (playerFound != null) {
            ItemStack toDamage = playerFound.getHeldItemMainhand();
            if (toDamage != ItemStack.EMPTY && !playerFound.capabilities.isCreativeMode) {
                toDamage.shrink(1);
            }
        }
    }

}
