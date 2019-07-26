package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.FilenameUtils;
import org.sapphon.minecraft.modding.base.JavaFileIOHelper;

import java.io.File;

public class ScriptLoaderConstants {

	public static String RESOURCES_PATH;
	public static final String PYTHON_SCRIPT_EXTENSION = ".py";
	public static String SCRIPTS_PATH;
	public static String MINECRAFT_PROGRAMMING_PATH;
	public static String TECHMAGE_SCRIPTS_PATH;
	public static String WAND_TEXTURE_LOCATION;
	public static final String WAND_TEXTURE_NAME_SUFFIX = "Wand";

	public static boolean isAPythonScript(File script) {
		return FilenameUtils.getExtension(script.getAbsolutePath()).matches(
				"[Pp][Yy][Cc]?");
	}
	
	public static boolean resourcePathExists() {
		return RESOURCES_PATH != null && new File(RESOURCES_PATH).exists();
	}

	public static boolean setResourcePath(FMLPreInitializationEvent event){
		if(new File(event.getModConfigurationDirectory().getAbsolutePath()).exists()){
			RESOURCES_PATH = event.getModConfigurationDirectory().getAbsolutePath();
			SCRIPTS_PATH = RESOURCES_PATH + File.separatorChar + "scripts";
			MINECRAFT_PROGRAMMING_PATH = SCRIPTS_PATH
					+ File.separatorChar+"mp";
			TECHMAGE_SCRIPTS_PATH = SCRIPTS_PATH
					+ File.separatorChar+"techmage";
			WAND_TEXTURE_LOCATION = RESOURCES_PATH + File.separatorChar+"assets"+File.separatorChar+"techmage"+File.separatorChar+"textures"+File.separatorChar+"items"+File.separatorChar;
			
			//emplace default Python script resources if they aren't there
			if(!new File(SCRIPTS_PATH).exists()){
				JavaFileIOHelper.SINGLETON.emplaceDefaultResources();
			}
			return true;
		}
		else{
			return false;
		}
	}
}
