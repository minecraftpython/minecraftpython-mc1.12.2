package org.sapphon.minecraft.modding.minecraftpython.factory;

import org.apache.commons.io.FilenameUtils;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.NeverCachingFileSpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.StringSpell;

import java.io.File;

public class SpellFactory {
	public static ISpell createNonCachingSpell(File script){
		return new NeverCachingFileSpell(FilenameUtils.getBaseName(script.getAbsolutePath())
				.toLowerCase(), script);
	}

	public static ISpell createStringSpell(String script){
		return new StringSpell(script);
	}
}
