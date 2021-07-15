package org.sapphon.minecraft.modding.mcutil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class PlayerHelper {
    public static EntityPlayerMP getPlayerByUsernameOrNull(MinecraftServer serverToSearch, String playerName) {
        return serverToSearch.getPlayerList().getPlayerByUsername(playerName);
    }

    public static boolean canPayExperiencePointCost(EntityPlayer player, int cost) {
        int availableToDeduct = (int) (player.experience * player.xpBarCap());
        int levelsBorrowed = 0;
        while (availableToDeduct < cost) {
            if (player.experienceLevel < 1) {
                player.addExperienceLevel(levelsBorrowed);
                return false;
            } else {
                levelsBorrowed++;
                player.addExperienceLevel(-1);
                availableToDeduct += player.xpBarCap();
            }
        }
        player.addExperienceLevel(levelsBorrowed);
        return true;
    }

    public static boolean deductExperiencePoints(EntityPlayer player, int cost) {
        int availableToDeduct = (int) (player.experience * player.xpBarCap());
        while (availableToDeduct < cost) {
            if (player.experienceLevel < 1) {
                return false;
            }
            player.addExperienceLevel(-1);
            availableToDeduct += player.xpBarCap();

        }
        int remainder = availableToDeduct - cost;
        player.experience = remainder / (float) player.xpBarCap();
        return true;
    }

    public static boolean isOnLogicalServer(EntityPlayer player) {
        return !isOnLogicalClient(player);
    }

    public static boolean isOnLogicalClient(EntityPlayer player) {
        return player.getEntityWorld().isRemote;
    }

    public static void logToPlayer(EntityPlayer player, String message) {
        player.sendMessage(new TextComponentString(message));
    }
}
