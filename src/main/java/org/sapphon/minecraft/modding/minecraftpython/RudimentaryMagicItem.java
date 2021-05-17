package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import org.sapphon.minecraft.modding.minecraftpython.command.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellCastingRunnable;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellThreadFactory;
import org.sapphon.minecraft.modding.techmage.SpellMetadataConstants;

public class RudimentaryMagicItem {
    private ISpell storedSpell;
    private long lastCast = 0;
    private int cooldown;

    public RudimentaryMagicItem(ISpell boundSpell) {
        storedSpell = boundSpell;
    }

    public void recordOntoItem(ItemStack toWandify) {
		setWandName(toWandify);
		setWandCooldown(toWandify);
		setWandRequiredExperience(toWandify);
		setWandAuthor(toWandify);
		setWandPython(toWandify);
    }

	private void setWandAuthor(ItemStack toAuthorify) {
		toAuthorify.setTagInfo(SpellMetadataConstants.KEY_AUTHOR_NAME, new NBTTagString(getStoredSpell().getAuthorName()));
	}

	private void setWandRequiredExperience(ItemStack toCostify) {
		toCostify.setTagInfo(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL, new NBTTagInt(getStoredSpell().getRequiredExperienceLevel()));
	}

	private void setWandCooldown(ItemStack toCooldownify) {
		toCooldownify.setTagInfo(SpellMetadataConstants.KEY_COOLDOWN_MILLIS, new NBTTagLong(getStoredSpell().getCooldownInMilliseconds()));
	}

	private void setWandPython(ItemStack toPythonify) {
		toPythonify.setTagInfo(SpellMetadataConstants.KEY_SPELL_PYTHON, new NBTTagString(getStoredSpell().getPythonScriptAsString()));
	}

	private void setWandName(ItemStack toName) {
		String displayName = getStoredSpell().getDisplayName();
		if (displayName.equals(SpellMetadataConstants.NONE)) {
			toName.setStackDisplayName("A Mysterious Magic Wand");
		} else {
			toName.setStackDisplayName("A Magic Wand of " + displayName);
		}
	}

	private long timer() {
        return System.currentTimeMillis() - lastCast;
    }

    public void doMagic() {
        if (timer() > storedSpell.getCooldownInMilliseconds()) {
            castStoredSpell();
            lastCast = System.currentTimeMillis();
        }
    }

    protected synchronized void castStoredSpell() {
        SpellThreadFactory.makeSpellThread(
                new SpellCastingRunnable(this.storedSpell, new SpellInterpreter()))
                .start();
    }

    public ISpell getStoredSpell() {
        return this.storedSpell;
    }
}
