package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;
import java.util.List;


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

    public String getSmeltingIngredient();

    public String getAnvilIngredient();

    public int getAnvilCost();

    List<String> getCraftingIngredients();
}
