package org.sapphon.minecraft.modding.minecraftpython.spells;


public class StringSpell extends AbstractSpell {

    protected String pythonString;

    public StringSpell(String pythonCode) {
        this.pythonString = pythonCode;
        readMetadata();
    }

    @Override
    public String getPythonScriptAsString() {
        return this.pythonString;
    }
}
