package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;


public interface ISpell {
    public String getPythonScriptAsString();

    public PyCode getCompiledPythonCode(PythonInterpreter interpreter);

    public int getRequiredExperienceLevels();

    public int getRequiredExperiencePoints();

    public int getConsumedExperienceLevels();

    public int getConsumedExperiencePoints();

    public String getAuthorName();

    public String getDisplayName();

    public long getCooldownInMilliseconds();
}
