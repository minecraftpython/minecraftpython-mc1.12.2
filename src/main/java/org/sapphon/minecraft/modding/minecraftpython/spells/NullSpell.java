package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

public class NullSpell extends AbstractSpell {

    @Override
    public String getPythonScriptAsString() {
        return "";
    }

    @Override
    public PyCode getCompiledPythonCode(PythonInterpreter interpreter) {
        return interpreter.compile("");
    }

}
