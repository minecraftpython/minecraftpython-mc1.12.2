package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

import java.io.File;


public interface ISpell {
	public String getPythonScriptAsString();

	public File getPythonScriptAsFile();

	public PyCode getCompiledPythonCode(PythonInterpreter interpreter);

	String getSpellShortName();

	public int getRequiredExperienceLevel();
	
	public String getAuthorName();
	
	public String getDisplayName();

	public long getCooldownInMilliseconds();
}
