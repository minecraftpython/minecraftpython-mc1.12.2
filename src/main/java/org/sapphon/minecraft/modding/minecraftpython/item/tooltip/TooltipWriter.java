package org.sapphon.minecraft.modding.minecraftpython.item.tooltip;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

public class TooltipWriter {
    public static void forItem(ItemTooltipEvent event, ItemStack item) {
        NBTTagCompound tagCompound = item.getTagCompound();
        if (tagCompound != null) {
            addCooldownTooltip(event, tagCompound);
            addAuthorTooltip(event, tagCompound);
            addConstraintTooltips(event, tagCompound);
            addUsesTooltips(event);
        }
    }

    protected static void addAuthorTooltip(ItemTooltipEvent event, NBTTagCompound itemTag) {
        if (itemTag.hasKey(SpellMetadataConstants.KEY_AUTHOR_NAME)) {
            event.getToolTip().add("Author : " + itemTag.getString(SpellMetadataConstants.KEY_AUTHOR_NAME));
        }
    }

    protected static void addCooldownTooltip(ItemTooltipEvent event, NBTTagCompound itemTag) {
        if (itemTag.hasKey(SpellMetadataConstants.KEY_COOLDOWN_MILLIS)) {
            event.getToolTip().add("Cooldown: " + itemTag.getLong(SpellMetadataConstants.KEY_COOLDOWN_MILLIS));
        }
    }

    protected static void addUsesTooltips(ItemTooltipEvent event) {
        event.getToolTip().add("Consumed when used");
    }

    protected static void addConstraintTooltips(ItemTooltipEvent event, NBTTagCompound itemTag) {
        if (itemTag.hasKey(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS) &&
                itemTag.getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS) > 0) {
            event.getToolTip().add(buildConstraintTooltip(false, true, itemTag.getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS)));

        } else if (itemTag.hasKey(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) &&
                itemTag.getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) > 0) {
            event.getToolTip().add(buildConstraintTooltip(false, false, itemTag.getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL)));
        }
        if (itemTag.hasKey(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS) &&
                itemTag.getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS) > 0) {
            event.getToolTip().add(buildConstraintTooltip(true, true, itemTag.getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS)));
        } else if (itemTag.hasKey(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS) &&
                itemTag.getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS) > 0) {
            event.getToolTip().add(buildConstraintTooltip(true, false, itemTag.getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS)));
        }
    }

    protected static String buildConstraintTooltip(boolean isPermanentCost, boolean isXpPoints, int costOrMinimum) {
        return (isPermanentCost ? "Costs " : "Requires ") + costOrMinimum + (isXpPoints ? " Experience Points" : " Levels") + " To Use";
    }

}
