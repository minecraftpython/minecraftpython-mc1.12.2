package org.sapphon.minecraft.modding.minecraftpython.spells;

import org.sapphon.minecraft.modding.minecraftpython.io.file.MinecraftPythonScriptLoader;


public class NeverCachingDefaultFileSpell extends AbstractSpell {
    public NeverCachingDefaultFileSpell() {
    }

    @Override
    public String getPythonScriptAsString() {
        metadataStale = true;
        return MinecraftPythonScriptLoader.SINGLETON().readFromScript();
    }
}
