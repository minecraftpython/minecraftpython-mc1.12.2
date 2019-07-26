package org.sapphon.minecraft.modding.techmage.wands;

import org.sapphon.minecraft.modding.minecraftpython.RudimentaryMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

public class MagicItemFactory {

	public static RudimentaryMagicItem create(ISpell boundSpell, int cooldownInMillis) {
		return new RudimentaryMagicItem(boundSpell, cooldownInMillis);
	}

}
