package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.sapphon.minecraft.modding.minecraftpython.io.file.JavaFileIOHelper;

import java.io.File;


public class NeverCachingFileSpell extends AbstractSpell{
	protected File pythonScript;
	protected String fileName;

	public NeverCachingFileSpell(String name, File pythonScript) {
		this.pythonScript = pythonScript;
		this.fileName = name;
	}

	@Override
	public String getPythonScriptAsString() {
		metadataStale = true;
		return JavaFileIOHelper.SINGLETON.getTextContentOfFile(pythonScript);
	}
}
