package org.sapphon.minecraft.modding.techmage;

import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;


public class SpellExperienceLevelArbiter {

	public static int getRequiredExperienceLevelForSpell(ISpell spellForWand) {
		return spellForWand.getRequiredExperienceLevel();
	}

}
