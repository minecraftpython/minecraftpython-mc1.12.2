package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonMod;

public class CommandMPExecuteConsoleCommand extends
        CommandMinecraftPythonServer {

    private String commandString;
    private String playerName;

    public CommandMPExecuteConsoleCommand(String commandText) {
        this.commandString = commandText;
        this.playerName = Minecraft.getMinecraft().player.getDisplayName().getUnformattedText();
    }

    public CommandMPExecuteConsoleCommand(String[] commandAndArgsToDeserialize) {
        this.commandString = commandAndArgsToDeserialize[1];
        this.playerName = commandAndArgsToDeserialize[2];
    }

    private static String[] dropFirstString(String[] par0ArrayOfStr) {
        String[] astring1 = new String[par0ArrayOfStr.length - 1];

        for (int i = 1; i < par0ArrayOfStr.length; ++i) {
            astring1[i - 1] = par0ArrayOfStr[i];
        }

        return astring1;
    }


    @Override
    public void doWork() {
        EntityPlayer playerObject = getPlayerByName(playerName);
        String[] astring = commandString.split(" ");
        String s1 = astring[0];
        astring = dropFirstString(astring);
        ICommand icommand = FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().getCommands().get(s1);
        int i = this.getUsernameIndex(icommand, astring);
        int j = 0;

        try {
            if (icommand == null) {
                throw new CommandNotFoundException();
            }

            if (i > -1) {
                EntityPlayerMP[] aentityplayermp = new EntityPlayerMP[0];
                aentityplayermp = EntitySelector.matchEntities(playerObject, astring[i], EntityPlayerMP.class).toArray(aentityplayermp);
                String s2 = astring[i];
                EntityPlayerMP[] aentityplayermp1 = aentityplayermp;
                int k = aentityplayermp.length;

                for (int l = 0; l < k; ++l) {
                    EntityPlayerMP entityplayermp = aentityplayermp1[l];
                    astring[i] = entityplayermp.getName();

                    try {
                        icommand.execute(FMLCommonHandler.instance().getMinecraftServerInstance(), playerObject, astring);
                        ++j;
                    } catch (CommandException commandexception) {
                        TextComponentTranslation chatcomponenttranslation1 = new TextComponentTranslation(commandexception.getMessage(), commandexception.getErrorObjects());
                        chatcomponenttranslation1.getStyle().setColor(TextFormatting.RED);
                        playerObject.sendMessage(chatcomponenttranslation1);
                    }
                }

                astring[i] = s2;
            } else {
                icommand.execute(FMLCommonHandler.instance().getMinecraftServerInstance(), playerObject, astring);
                ++j;
            }
        } catch (Exception e) {

        }

    }

    private EntityPlayer getPlayerByName(String name) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getPlayerEntityByName(name);
    }

    private int getUsernameIndex(ICommand par1ICommand, String[] par2ArrayOfStr) {
        if (par1ICommand == null) {
            return -1;
        } else {
            for (int i = 0; i < par2ArrayOfStr.length; ++i) {
                try {
                    if (par1ICommand.isUsernameIndex(par2ArrayOfStr, i) && EntitySelector.matchesMultiplePlayers(par2ArrayOfStr[i])) {
                        return i;
                    }
                } catch (CommandException e) {
                    MinecraftPythonMod.logger.error("Could not get index of player named " + par2ArrayOfStr[i]);
                    return -1;
                }
            }

            return -1;
        }
    }

    @Override
    public String serialize() {
        return CONSOLECOMMAND_NAME + SERIAL_DIV + commandString + SERIAL_DIV + playerName;
    }
}
