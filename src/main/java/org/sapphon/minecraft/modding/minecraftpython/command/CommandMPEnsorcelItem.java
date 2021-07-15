package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;
import org.sapphon.minecraft.modding.minecraftpython.item.WandReaderWriter;
import org.sapphon.minecraft.modding.minecraftpython.spells.StringSpell;

public class CommandMPEnsorcelItem extends CommandMinecraftPythonServer {

    private String playerName;
    private String spellText;

    public CommandMPEnsorcelItem(String playerName, String spellText) {

        this.playerName = playerName;
        this.spellText = spellText;
    }

    public CommandMPEnsorcelItem(String[] commandAndArgsToDeserialize) {
        this.playerName = commandAndArgsToDeserialize[1];
        this.spellText = commandAndArgsToDeserialize[2];
    }

    @Override
    public String serialize() {
        return CommandMinecraftPythonServer.ENSORCEL_ITEM_NAME + SERIAL_DIV + playerName + SERIAL_DIV + spellText;
    }

    @Override
    public void doWork() {
        EntityPlayerMP playerFound = PlayerHelper.getPlayerByUsernameOrNull(FMLCommonHandler.instance().getMinecraftServerInstance(), playerName);
        if (playerFound != null) {
            ItemStack toEnsorcel = playerFound.getHeldItemMainhand();
            if (toEnsorcel != ItemStack.EMPTY) {
                WandReaderWriter.recordOntoItem(new StringSpell(spellText), toEnsorcel);
            }
        }
    }
}
