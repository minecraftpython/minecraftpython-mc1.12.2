package org.sapphon.minecraft.modding.techmage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.sapphon.minecraft.modding.minecraftpython.RudimentaryMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.spells.StringSpell;

public class MagicItemEventHandler {

    private static boolean isMagicWand(ItemStack itemStack){
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return (tagCompound != null && tagCompound.hasKey(SpellMetadataConstants.KEY_SPELL_PYTHON));
    }

    @SubscribeEvent
    public static void HandleRightClickMagicWandEvent(PlayerInteractEvent.RightClickItem event) {
        if (isMagicWand(event.getItemStack())) {
            String pythonScript = event.getItemStack().getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON);
            new RudimentaryMagicItem(new StringSpell(pythonScript)).doMagic();
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack item = event.getItemStack();
        if (isMagicWand(item) && item.getTagCompound() != null) {
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