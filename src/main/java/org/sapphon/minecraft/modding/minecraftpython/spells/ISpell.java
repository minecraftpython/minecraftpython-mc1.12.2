package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;
import java.util.List;


public interface ISpell {
    String getPythonScriptAsString();

    PyCode getCompiledPythonCode(PythonInterpreter interpreter);

    int getRequiredExperienceLevels();

    int getRequiredExperiencePoints();

    int getConsumedExperienceLevels();

    int getConsumedExperiencePoints();

    String getAuthorName();

    String getDisplayName();

    long getCooldownInMilliseconds();

    String getSmeltingIngredient();

    String getAnvilIngredient();

    int getAnvilCost();

    List<String> getCraftingIngredients();

    List<String> getDispenserBehaviors();
}
