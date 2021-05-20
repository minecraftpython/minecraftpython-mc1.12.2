package org.sapphon.minecraft.modding.minecraftpython.event;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.sapphon.minecraft.modding.minecraftpython.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.ModConfigurationFlags;
import org.sapphon.minecraft.modding.minecraftpython.factory.MagicItemFactory;
import org.sapphon.minecraft.modding.minecraftpython.factory.SpellFactory;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

public class MagicItemEventHandler {

    @SubscribeEvent
    public static void HandleRightClickMagicWandEvent(PlayerInteractEvent.RightClickItem event) {
        if (ModConfigurationFlags.WAND_USE() && BasicMagicItem.isMagicWand(event.getItemStack())) {
            String pythonScript = event.getItemStack().getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON);
            MagicItemFactory.createBasic(SpellFactory.createStringSpell(pythonScript)).doMagic();
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack item = event.getItemStack();
        if (BasicMagicItem.isMagicWand(item) && item.getTagCompound() != null) {
            if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_COOLDOWN_MILLIS)) {
                event.getToolTip().add("Cooldown: " + item.getTagCompound().getLong(SpellMetadataConstants.KEY_COOLDOWN_MILLIS));
            }
            if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_AUTHOR_NAME)) {
                event.getToolTip().add("Author : " + item.getTagCompound().getString(SpellMetadataConstants.KEY_AUTHOR_NAME));
            }
            if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) &&
                    item.getTagCompound().getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) > 0) {
                event.getToolTip().add("Costs " + item.getTagCompound().getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) + " XP Levels to Use");
            }
        }
    }
}