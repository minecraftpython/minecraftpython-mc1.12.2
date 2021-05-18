package org.sapphon.minecraft.modding.minecraftpython.async;

import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;


public class ThreadFactory {
	public static Thread makeHttpListenerRunnable() {
		return new Thread(new HttpPythonListenerRunnable(new SpellInterpreter()));
	}

	public static Thread makeSpellThread(
			SpellCastingRunnable spellCastingRunnable) {
		return new Thread(spellCastingRunnable);
	}
}
