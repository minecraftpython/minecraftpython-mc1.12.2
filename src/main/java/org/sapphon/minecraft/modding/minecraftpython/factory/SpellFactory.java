package org.sapphon.minecraft.modding.minecraftpython.factory;

import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.NeverCachingDefaultFileSpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.StringSpell;

import java.io.File;

public class SpellFactory {
	public static ISpell createNonCachingSpell(File script){
		return new NeverCachingDefaultFileSpell();
	}

	public static ISpell createStringSpell(String script){
		return new StringSpell(script);
	}
}
