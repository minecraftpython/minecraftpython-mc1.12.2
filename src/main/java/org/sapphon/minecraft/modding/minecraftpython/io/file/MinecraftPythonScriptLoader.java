package org.sapphon.minecraft.modding.minecraftpython.io.file;

import org.sapphon.minecraft.modding.minecraftpython.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.factory.MagicItemFactory;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.PythonProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.factory.SpellFactory;

import java.io.File;

import static org.sapphon.minecraft.modding.minecraftpython.io.file.ScriptLoaderConstants.DEFAULT_PYTHON_SCRIPT_FILENAME;

public class MinecraftPythonScriptLoader {

	private BasicMagicItem magicVessel;

	private static MinecraftPythonScriptLoader SINGLETON;

	public static MinecraftPythonScriptLoader SINGLETON() {
		if (SINGLETON == null)
			SINGLETON = new MinecraftPythonScriptLoader(DEFAULT_PYTHON_SCRIPT_FILENAME);
		return SINGLETON;
	}

	private MinecraftPythonScriptLoader(String scriptFileName) {
		File scriptsDirectory = new File(ScriptLoaderConstants.MINECRAFT_PROGRAMMING_PATH);
		if (scriptsDirectory.canRead() && scriptsDirectory.isDirectory()) {
			try {
				File script = new File(ScriptLoaderConstants.MINECRAFT_PROGRAMMING_PATH
						+ File.separatorChar + scriptFileName + ScriptLoaderConstants.PYTHON_SCRIPT_EXTENSION);
				magicVessel = MagicItemFactory.createBasic(SpellFactory.createNonCachingSpell(script));
			} catch (Exception e) {
				PythonProblemHandler.printErrorMessageToDialogBox(e);
			}
		}
	}

	public BasicMagicItem getMagicVessel(){
		return magicVessel;
	}

}
