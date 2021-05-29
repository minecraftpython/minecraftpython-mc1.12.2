package org.sapphon.minecraft.modding.mcutil;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerHelper {
    public static boolean canPayExperiencePointCost(EntityPlayer player, int cost) {
        int availableToDeduct = (int) (player.experience * player.xpBarCap());
        int levelsBorrowed = 0;
        while (availableToDeduct < cost) {
            if (player.experienceLevel < 1) {
                player.addExperienceLevel(levelsBorrowed);
                return false;
            }else {
                levelsBorrowed++;
                player.addExperienceLevel(-1);
                availableToDeduct += player.xpBarCap();
            }
        }
        player.addExperienceLevel(levelsBorrowed);
        return true;
    }
}
