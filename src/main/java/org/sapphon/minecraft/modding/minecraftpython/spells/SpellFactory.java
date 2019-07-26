package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class SpellFactory {
	public static ISpell createNonCachingSpell(File script){
		return new NeverCachingSpell(FilenameUtils.getBaseName(script.getAbsolutePath())
				.toLowerCase(), script);
	}
	
	public static ISpell createCachingSpell(File script){
		return new CachingSpell(FilenameUtils.getBaseName(script.getAbsolutePath())
				.toLowerCase(), script);
	}
	
}
