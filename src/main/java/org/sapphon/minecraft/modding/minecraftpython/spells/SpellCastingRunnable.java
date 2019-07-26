package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.sapphon.minecraft.modding.minecraftpython.command.SpellInterpreter;

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
