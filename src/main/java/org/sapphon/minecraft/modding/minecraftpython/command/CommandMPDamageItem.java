package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;
import org.sapphon.minecraft.modding.minecraftpython.factory.SpellFactory;
import org.sapphon.minecraft.modding.minecraftpython.item.WandReaderWriter;

public class CommandMPDamageItem extends CommandMinecraftPythonServer {

    private String playerName;

    public CommandMPDamageItem(String playerName) {

        this.playerName = playerName;
    }

    public CommandMPDamageItem(String[] commandAndArgsToDeserialize) {
        this.playerName = commandAndArgsToDeserialize[1];
    }

    @Override
    public String serialize() {
        return CommandMinecraftPythonServer.DAMAGEITEM_NAME + SERIAL_DIV + playerName;
    }

    @Override
    public void doWork() {
        EntityPlayerMP playerFound = PlayerHelper.getPlayerByUsernameOrNull(FMLCommonHandler.instance().getMinecraftServerInstance(), playerName);
        if (playerFound != null) {
            ItemStack toDamage = playerFound.getHeldItemMainhand();
            if (toDamage != ItemStack.EMPTY) {
                damageOrDecrementItemStackUnlessInCreative(toDamage, playerFound);
            }
        }
    }

    private void damageOrDecrementItemStackUnlessInCreative(ItemStack wand, EntityPlayer player) {
        if (wand.getMaxDamage() > 0) {
            wand.damageItem(1, player);
        } else if (!player.capabilities.isCreativeMode) {
            wand.shrink(1);
        }
    }
}
