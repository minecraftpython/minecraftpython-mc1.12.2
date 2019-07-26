package org.sapphon.minecraft.modding.minecraftpython.spells;

public class SpellThreadFactory {

	public static Thread makeSpellThread(
			SpellCastingRunnable spellCastingRunnable) {
		return new Thread(spellCastingRunnable);
	}

}
