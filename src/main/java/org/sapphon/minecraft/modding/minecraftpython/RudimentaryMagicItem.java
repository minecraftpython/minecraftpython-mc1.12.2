package org.sapphon.minecraft.modding.minecraftpython;

import org.sapphon.minecraft.modding.minecraftpython.command.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellCastingRunnable;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellThreadFactory;

public class RudimentaryMagicItem implements IArcane {
	private ISpell storedSpell;
	public SpellInterpreter spellInterpreter;
	private long lastCast = 0;
	private int cooldown;

	public RudimentaryMagicItem(ISpell boundSpell, int cooldownInMillis) {
		storedSpell = boundSpell;
		this.cooldown = cooldownInMillis;
		this.spellInterpreter = new SpellInterpreter();
	}

	private long timer() {
		return System.currentTimeMillis() - lastCast;
	}

	@Override
	public void doMagic() {
		if (timer() > this.cooldown) {
			castStoredSpell();
			lastCast = System.currentTimeMillis();
		}
	}

	protected synchronized void castStoredSpell() {
		SpellThreadFactory.makeSpellThread(
				new SpellCastingRunnable(this.storedSpell, new SpellInterpreter()))
				.start();
	}
}
