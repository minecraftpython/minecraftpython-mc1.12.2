package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MinecraftPythonCreativeTab extends CreativeTabs {

	private Item itemIcon;

	public MinecraftPythonCreativeTab(String tabLabel, Item itemIcon) {
		super(tabLabel);
		this.itemIcon = itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return itemIcon;
	}
}