package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;

public class ItemLookup {
	public static Item getItemByName(String name, WorldServer worldserver) {
		Object defaultResult = Item.REGISTRY.getObject(new ResourceLocation(name.toLowerCase()));
		if(defaultResult != null){
			return (Item)defaultResult;
		}
		return Items.BOAT;
		
	}
}
