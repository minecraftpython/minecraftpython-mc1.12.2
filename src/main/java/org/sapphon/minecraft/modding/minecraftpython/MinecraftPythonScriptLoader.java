package org.sapphon.minecraft.modding.minecraftpython;

import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.PythonProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellFactory;
import org.sapphon.minecraft.modding.techmage.wands.MagicItemFactory;

import java.io.File;

public class MinecraftPythonScriptLoader {

	private RudimentaryMagicItem magicVessel;

	private static MinecraftPythonScriptLoader SINGLETON;

	public static MinecraftPythonScriptLoader SINGLETON() {
		if (SINGLETON == null)
			SINGLETON = new MinecraftPythonScriptLoader("your_code_here");
		return SINGLETON;
	}

	private MinecraftPythonScriptLoader(String scriptFileName) {
		File scriptsDirectory = new File(ScriptLoaderConstants.MINECRAFT_PROGRAMMING_PATH);
		if (scriptsDirectory.canRead() && scriptsDirectory.isDirectory()) {
			try {
				File script = new File(ScriptLoaderConstants.MINECRAFT_PROGRAMMING_PATH
						+ File.separatorChar + scriptFileName + ScriptLoaderConstants.PYTHON_SCRIPT_EXTENSION);
				magicVessel = MagicItemFactory.create(SpellFactory.createNonCachingSpell(script), MinecraftPythonMod.SCRIPT_RUN_COOLDOWN);
			} catch (Exception e) {
				PythonProblemHandler.printErrorMessageToDialogBox(e);
			}
		}
	}

	public IArcane getMagicVessel(){
		return magicVessel;
	}

}
