package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonMod;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractSpell implements ISpell {
    protected PyCode pythonCompiledCode;
    protected Map<String, String> spellMetadata;
    protected boolean metadataStale = true;

    @Override
    public abstract String getPythonScriptAsString();

    protected void compileSpell(PythonInterpreter interpreter) {
        pythonCompiledCode = interpreter
                .compile(this.getPythonScriptAsString());
    }

    @Override
    public int getRequiredExperienceLevels() {
        return getIntMetadataOrZero(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL);
    }

    @Override
    public String getAnvilIngredient(){
        return getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_ANVIL_ITEM);
    }

    @Override
    public int getAnvilCost(){
        return getIntMetadataOrZero(SpellMetadataConstants.KEY_ANVIL_COST);
    }

    @Override
    public int getRequiredExperiencePoints() {
        return getIntMetadataOrZero(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS);
    }

    @Override
    public int getConsumedExperienceLevels() {
        return getIntMetadataOrZero(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS);
    }

    @Override
    public int getConsumedExperiencePoints() {
        return getIntMetadataOrZero(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS);
    }

    protected int getIntMetadataOrZero(String metadataKey) {
        String value = getMetadataValueOrNONEIfNotPresent(metadataKey);
        if (value.equals(SpellMetadataConstants.NONE)) {
            return 0;
        } else {
            int valueAsInt = 0;
            try {
                valueAsInt = Integer.parseInt(value);
            } catch (Exception e) {
                JavaProblemHandler.printErrorMessageToDialogBox(e);
            }
            return valueAsInt;
        }
    }

    @Override
    public String getDisplayName() {
        return getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_SPELL_NAME);
    }

    @Override
    public String getSmeltingIngredient() {
        return getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_SMELTING_INGREDIENT);
    }

    @Override
    public List<String> getCraftingIngredients(){
        String ingredientString = getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_CRAFTING_INGREDIENTS);
        if(ingredientString.equals(SpellMetadataConstants.NONE)){
            return new ArrayList<>();
        }
        else{
            return Arrays.stream(ingredientString.split(",",9)).collect(Collectors.toList());
        }
    }

    @Override
    public List<String> getDispenserBehaviors(){
        String behaviorsString = getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_DISPENSER_BEHAVIOR);
        if(behaviorsString.equals(SpellMetadataConstants.NONE)){
            return new ArrayList<>();
        }
        else{
            return Arrays.stream(behaviorsString.split(",",9)).collect(Collectors.toList());
        }
    }

    @Override
    public String getAuthorName() {
        String value = getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_AUTHOR_NAME);
        if (value.equals(SpellMetadataConstants.NONE)) {
            return "Anonymous";
        }
        return value;

    }

    @Override
    public long getCooldownInMilliseconds() {

        if (getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_COOLDOWN_MILLIS).equals(SpellMetadataConstants.NONE)) {
            return MinecraftPythonMod.SCRIPT_RUN_COOLDOWN;
        } else {
            return Long.parseLong(getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_COOLDOWN_MILLIS));
        }
    }

    private String getMetadataValueOrNONEIfNotPresent(String key) {
        if (metadataStale) {
            spellMetadata = readMetadata();
            metadataStale = false;
        }
        String value = spellMetadata.get(key);
        if (value != null) {
            return value;
        }
        return SpellMetadataConstants.NONE;
    }

    @Override
    public PyCode getCompiledPythonCode(PythonInterpreter interpreter) {
        compileSpell(interpreter);
        return this.pythonCompiledCode;
    }

    protected Map<String, String> readMetadata() {
        Map<String, String> metadataMap = new LinkedHashMap<String, String>();
        String pythonScriptAsString = getPythonScriptAsString();
        String[] linesWithWhitespace = pythonScriptAsString.split(System
                .lineSeparator());

        for (String lineWithWhitespace : linesWithWhitespace) {
            String line = lineWithWhitespace.trim();

            boolean lineIsComment = line.startsWith("#");
            boolean lineHasKeyValueDefinition = line
                    .contains(SpellMetadataConstants.PAIR_DEFINITION_GLYPH);

            if (lineIsComment && lineHasKeyValueDefinition) {
                int delimiterIndex = line.indexOf(SpellMetadataConstants.PAIR_DEFINITION_GLYPH);

                String key = line.substring(1, delimiterIndex).trim().toLowerCase();

                int startOfValue = SpellMetadataConstants.PAIR_DEFINITION_GLYPH.length();
                String value = line.substring(delimiterIndex + startOfValue)
                        .trim();

                metadataMap.put(key, value);
            }

        }
        return metadataMap;
    }

}
