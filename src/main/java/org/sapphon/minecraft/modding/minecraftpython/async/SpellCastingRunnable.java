package org.sapphon.minecraft.modding.minecraftpython.async;

import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

public class SpellCastingRunnable implements Runnable {

	private ISpell spell;
	private SpellInterpreter spellInterpreter;

	public SpellCastingRunnable(ISpell spell, SpellInterpreter spellInterpreter){
		this.spell = spell;
		this.spellInterpreter = spellInterpreter;
	}
	
	public void run() {
		boolean failure = !(spellInterpreter.interpretSpell(spell));
    }
}
