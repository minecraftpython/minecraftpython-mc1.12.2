package org.sapphon.minecraft.modding.techmage.wands;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WandCreativeTab extends CreativeTabs {

	public WandCreativeTab(String tabLabel) {
		super(tabLabel);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
//		return ScriptLoader.SINGLETON.getWands().get(new Random().nextInt() % ScriptLoader.SINGLETON.getWands().size());	//TODO circular reference
		return new ItemStack(Items.BLAZE_ROD);
	}

}