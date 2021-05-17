package org.sapphon.minecraft.modding.techmage;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

public class MagicItemClickHandler {
    @SubscribeEvent
    public static void HandleRightClickMagicWandEvent(PlayerInteractEvent.RightClickItem event){
        boolean isItemMagicWand = event.getItemStack().getDisplayName().toLowerCase().contains("magic wand");
        if(isItemMagicWand){
            event.getEntity().sendMessage(new TextComponentString(event.getItemStack().getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON) + " should be executed at this time"));
        }
        event.setCanceled(isItemMagicWand);
    }
}
