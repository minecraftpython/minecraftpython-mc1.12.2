package org.sapphon.minecraft.modding.minecraftpython.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;
import org.sapphon.minecraft.modding.minecraftpython.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.ModConfigurationFlags;
import org.sapphon.minecraft.modding.minecraftpython.factory.MagicItemFactory;
import org.sapphon.minecraft.modding.minecraftpython.factory.SpellFactory;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

import java.util.LinkedHashMap;
import java.util.Map;

public class MagicItemEventHandler {

    Map<ItemStack, BasicMagicItem> magicItems = new LinkedHashMap<>();

    public MagicItemEventHandler() {
    }

    @SubscribeEvent
    public void HandleRightClickMagicWandEvent(PlayerInteractEvent.RightClickItem event) {
        ItemStack rightClickedStack = event.getItemStack();
        if (PlayerHelper.isOnLogicalClient(event.getEntityPlayer()) && ModConfigurationFlags.WAND_USE() && BasicMagicItem.isMagicWand(rightClickedStack)) {
            if (magicItems.containsKey(rightClickedStack)) {
                magicItems.get(rightClickedStack).attemptMagic(event.getEntityPlayer());
            } else {
                String pythonScript = rightClickedStack.getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON);
                BasicMagicItem newMagicItem = MagicItemFactory.createBasic(SpellFactory.createStringSpell(pythonScript));
                magicItems.put(rightClickedStack, newMagicItem);
                newMagicItem.attemptMagic(event.getEntityPlayer());
            }
            event.setCanceled(true);
            event.setResult(Event.Result.DENY);
            event.setCancellationResult(EnumActionResult.FAIL);
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack item = event.getItemStack();
        if (BasicMagicItem.isMagicWand(item) && item.getTagCompound() != null) {
            if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_COOLDOWN_MILLIS)) {
                event.getToolTip().add("Cooldown: " + item.getTagCompound().getLong(SpellMetadataConstants.KEY_COOLDOWN_MILLIS));
            }
            if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_AUTHOR_NAME)) {
                event.getToolTip().add("Author : " + item.getTagCompound().getString(SpellMetadataConstants.KEY_AUTHOR_NAME));
            }
            addConstraintTooltips(event, item);
        }
    }

    private void addConstraintTooltips(ItemTooltipEvent event, ItemStack item) {
        if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS) &&
                item.getTagCompound().getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS) > 0) {
            event.getToolTip().add(buildConstraintTooltip(false, true, item.getTagCompound().getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS)));

        } else if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) &&
                item.getTagCompound().getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL) > 0) {
            event.getToolTip().add(buildConstraintTooltip(false, false, item.getTagCompound().getInteger(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL)));
        }
        if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS) &&
                item.getTagCompound().getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS) > 0) {
            event.getToolTip().add(buildConstraintTooltip(true, true, item.getTagCompound().getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS)));
        } else if (item.getTagCompound().hasKey(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS) &&
                item.getTagCompound().getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS) > 0) {
            event.getToolTip().add(buildConstraintTooltip(true, false, item.getTagCompound().getInteger(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS)));
        }
    }

    protected String buildConstraintTooltip(boolean isPermanentCost, boolean isXpPoints, int costOrMinimum) {
        return (isPermanentCost ? "Costs " : "Requires ") + costOrMinimum + (isXpPoints ? " Experience Points" : " Levels") + " To Use";
    }
}