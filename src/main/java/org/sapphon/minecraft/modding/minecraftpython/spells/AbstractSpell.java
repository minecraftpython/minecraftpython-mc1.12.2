package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;
import org.sapphon.minecraft.modding.base.JavaFileIOHelper;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.techmage.ArcaneArmory;
import org.sapphon.minecraft.modding.techmage.SpellMetadataConstants;
import org.sapphon.minecraft.modding.techmage.wands.WandType;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractSpell implements ISpell {
	protected String name;
	protected File pythonScript;
	protected PyCode pythonCompiledCode;
	private Map<String, String> spellMetadata;
	
	@Override
	public abstract PyCode getCompiledPythonCode(PythonInterpreter interpreter);

	public AbstractSpell(String name, File pythonScript){
		this.name = name;
		this.pythonScript = pythonScript;
		this.spellMetadata = this.readMetadata();
	}

	protected void compileSpell(PythonInterpreter interpreter) {
		pythonCompiledCode = interpreter
				.compile(this.getPythonScriptAsString());
	}

	@Override
	public String getPythonScriptAsString() {
		return JavaFileIOHelper.SINGLETON.getTextContentOfFile(pythonScript);
	}

	@Override
	public File getPythonScriptAsFile() {
		return pythonScript;
	}

	@Override
	public String getSpellShortName() {
		return name;
	}

	@Override
	public int getRequiredExperienceLevel() {
		String value = getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL);
		if (value.equals(SpellMetadataConstants.NONE)) {
			return 0;
		}
		int valueAsInt = 0;
		try {
			valueAsInt = Integer.parseInt(value);
		} catch (Exception e) {
			JavaProblemHandler.printErrorMessageToDialogBox(e);
		}
		return valueAsInt;
	}
	
	@Override
	public String getDisplayName(){
		return getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_SPELL_NAME);
	}
	
	@Override
	public boolean hasCustomTexture(){
		return getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_TEXTURE_NAME) != SpellMetadataConstants.NONE;
	}
	
	@Override
	public String getCustomTextureName(){
		return getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_TEXTURE_NAME);
	}
	

	@Override
	public String getAuthorName() {
		String value = getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_AUTHOR_NAME);
		if(value.equals(SpellMetadataConstants.NONE)){
			return "Anonymous";
		}
		return value;

	}
	@Override
	public WandType getWandType(){
		if (getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_WAND_TYPE) != SpellMetadataConstants.NONE){
			String wandTypeString=getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_WAND_TYPE);
			if(wandTypeString.toLowerCase().equals("ray")){
					return WandType.RAY;
			}
			else if(wandTypeString.toLowerCase().equals("projectile")){
					return WandType.PROJECTILE;
			}
		}
		return WandType.LOCAL;
	}
	
	@Override
	public long getCooldownInMilliseconds(){
		
		if (getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_COOLDOWN_MILLIS) != SpellMetadataConstants.NONE){
			return Long.parseLong(getMetadataValueOrNONEIfNotPresent(SpellMetadataConstants.KEY_COOLDOWN_MILLIS));
		}
		else{
			return ArcaneArmory.GLOBAL_WAND_COOLDOWN;
		}
	}

	private String getMetadataValueOrNONEIfNotPresent(String key) {
		String value = spellMetadata.get(key);
		if (value != null) {
			return value;
		}
		return SpellMetadataConstants.NONE;
	}

	private Map<String, String> readMetadata() {
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
