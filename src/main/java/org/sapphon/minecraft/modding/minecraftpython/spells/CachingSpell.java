package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

import java.io.File;


public class CachingSpell extends AbstractSpell{

	public CachingSpell(String name, File pythonScript){
		super(name, pythonScript);
	}

	private boolean isCached(){
		if(pythonCompiledCode == null)
			return false;
		return true;
	}
	
	@Override
	public PyCode getCompiledPythonCode(PythonInterpreter interpreter) {
		if(!isCached()){
			compileSpell(interpreter);
		}
		return this.pythonCompiledCode;
	
	}
}
