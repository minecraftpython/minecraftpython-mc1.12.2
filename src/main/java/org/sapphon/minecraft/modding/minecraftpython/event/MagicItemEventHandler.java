package org.sapphon.minecraft.modding.minecraftpython.event;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;
import org.sapphon.minecraft.modding.minecraftpython.ModConfigurationFlags;
import org.sapphon.minecraft.modding.minecraftpython.factory.MagicItemFactory;
import org.sapphon.minecraft.modding.minecraftpython.factory.SpellFactory;
import org.sapphon.minecraft.modding.minecraftpython.item.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.item.WandReaderWriter;
import org.sapphon.minecraft.modding.minecraftpython.item.tooltip.TooltipWriter;
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
        if (PlayerHelper.isOnLogicalClient(event.getEntityPlayer()) && ModConfigurationFlags.WAND_USE() && WandReaderWriter.isMagicWand(rightClickedStack)) {
            if (magicItems.containsKey(rightClickedStack)) {
                magicItems.get(rightClickedStack).attemptMagic(event.getEntityPlayer(), rightClickedStack);
            } else {
                String pythonScript = rightClickedStack.getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON);
                BasicMagicItem newMagicItem = MagicItemFactory.createBasic(SpellFactory.createStringSpell(pythonScript));
                magicItems.put(rightClickedStack, newMagicItem);
                newMagicItem.attemptMagic(event.getEntityPlayer(), rightClickedStack);
            }
            event.setCanceled(true);
            event.setResult(Event.Result.DENY);
            event.setCancellationResult(EnumActionResult.FAIL);
        }
    }

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
        if (WandReaderWriter.isMagicWand(event.getLeft())) {
            Item itemOrNull = Item.getByNameOrId(WandReaderWriter.getWandAnvilItem(event.getLeft()));
            if (itemOrNull != null) {
                ItemStack copy = event.getLeft().copy();
                copy.grow(1);
                int wandAnvilCost = WandReaderWriter.getWandAnvilCost(event.getLeft());
                if(wandAnvilCost >= 0) {
                    event.setCost(wandAnvilCost);
                }
                event.setOutput(copy);
            }
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack item = event.getItemStack();
        if (WandReaderWriter.isMagicWand(item)) {
            TooltipWriter.forItem(event, item);
        }
    }
}