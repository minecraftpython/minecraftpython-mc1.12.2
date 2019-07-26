package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

import java.io.File;

public class NullSpell extends AbstractSpell {

	public NullSpell(String name, File pythonScript) {
		super(name, pythonScript);
	}

	@Override
	public PyCode getCompiledPythonCode(PythonInterpreter interpreter) {
		return interpreter.compile("");
	}

}
