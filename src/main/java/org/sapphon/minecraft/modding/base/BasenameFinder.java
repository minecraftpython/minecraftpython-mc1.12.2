package org.sapphon.minecraft.modding.base;

import net.minecraft.item.Item;

public class BasenameFinder {
public static String getBaseNameForItem(Item item){
	String fullName = item.getUnlocalizedName();
	String baseName = fullName.replaceFirst("\\.name", "").replaceFirst("item\\.", "").trim();
	return baseName;
}
}
