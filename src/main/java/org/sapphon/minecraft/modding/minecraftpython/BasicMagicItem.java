package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;
import org.sapphon.minecraft.modding.minecraftpython.async.SpellCastingRunnable;
import org.sapphon.minecraft.modding.minecraftpython.async.ThreadFactory;
import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.io.file.MinecraftPythonScriptLoader;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

public class BasicMagicItem {
    private ISpell storedSpell;
    private long lastCast = 0;

    public BasicMagicItem(ISpell boundSpell) {
        storedSpell = boundSpell;
    }

    public static boolean isMagicWand(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return (tagCompound != null && tagCompound.hasKey(SpellMetadataConstants.KEY_SPELL_PYTHON));
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
        toCostify.setTagInfo(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL, new NBTTagInt(getStoredSpell().getRequiredExperienceLevels()));
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

    public void attemptMagic(EntityPlayer spellcaster) {
        if (meetsMinima(spellcaster)) {
            if (canPayCost(spellcaster)) {
                if (timer() > storedSpell.getCooldownInMilliseconds()) {
                    deductCastingCost(spellcaster);
                    doMagic();
                    lastCast = System.currentTimeMillis();
                } else {
                    spellcaster.sendMessage(new TextComponentString("Cooldown not finished."));
                }
            } else {
                spellcaster.sendMessage(new TextComponentString("Not enough experience to pay the casting cost."));
            }
        } else {
            spellcaster.sendMessage(new TextComponentString("Not enough experience to know how to use this."));
        }
    }

    protected void deductCastingCost(EntityPlayer spellcaster) {
        int consumedExperiencePoints = this.storedSpell.getConsumedExperiencePoints();
        int consumedExperienceLevels = this.storedSpell.getConsumedExperienceLevels();

        if (consumedExperiencePoints > 0) {
            int availableToDeduct = (int) (spellcaster.experience * spellcaster.xpBarCap());
            while (availableToDeduct < consumedExperiencePoints) {
                spellcaster.addExperienceLevel(-1);
                availableToDeduct += spellcaster.xpBarCap();
            }
            int remainder = availableToDeduct - consumedExperiencePoints;
            spellcaster.experience = remainder / (float)spellcaster.xpBarCap();
        } else if (consumedExperienceLevels > 0) {
            spellcaster.addExperienceLevel(-consumedExperienceLevels);
        }
    }

    protected boolean canPayCost(EntityPlayer player) {
        int pointCost = this.storedSpell.getConsumedExperiencePoints();
        int levelCost = this.storedSpell.getConsumedExperienceLevels();
        if (pointCost == 0 && levelCost == 0) {
            return true;
        } else if (pointCost != 0) {
            return PlayerHelper.canPayExperiencePointCost(player, pointCost);
        } else {
            return player.experienceLevel >= levelCost;

        }
    }

    protected boolean meetsMinima(EntityPlayer player) {
        int pointMin = this.storedSpell.getRequiredExperiencePoints();
        int levelMin = this.storedSpell.getRequiredExperienceLevels();
        if (pointMin == 0 && levelMin == 0) {
            return true;
        } else if (pointMin != 0) {
            return PlayerHelper.canPayExperiencePointCost(player, pointMin);
        } else {
            return player.experienceLevel >= levelMin;

        }
    }

    protected synchronized void castStoredSpell() {
        ThreadFactory.makeSpellThread(
                new SpellCastingRunnable(this.storedSpell, new SpellInterpreter()))
                .start();
    }

    public ISpell getStoredSpell() {
        return this.storedSpell;
    }

    public void readFromItem(ItemStack heldItemMainhand) {
        if (isMagicWand(heldItemMainhand)) {
            MinecraftPythonScriptLoader.SINGLETON().writeToScript(heldItemMainhand.getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON));
        }
    }

    public void doMagic() {
        castStoredSpell();
    }
}
