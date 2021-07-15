package org.sapphon.minecraft.modding.minecraftpython.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

public class WandReaderWriter {
    public static void recordOntoItem(ISpell toRecord, ItemStack toRecordOnto) {
        setWandName(toRecordOnto, toRecord.getDisplayName());
        setWandCooldown(toRecordOnto, toRecord.getCooldownInMilliseconds());
        setWandRequiredExperience(toRecordOnto, toRecord);
        setWandAuthor(toRecordOnto, toRecord.getAuthorName());
        setWandPython(toRecordOnto, toRecord.getPythonScriptAsString());
    }

    public static boolean isMagicWand(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return (tagCompound != null && tagCompound.hasKey(SpellMetadataConstants.KEY_SPELL_PYTHON));
    }

    private static void setWandAuthor(ItemStack toAuthorify, String authorName) {
        toAuthorify.setTagInfo(SpellMetadataConstants.KEY_AUTHOR_NAME, new NBTTagString(authorName));
    }

    private static void setWandRequiredExperience(ItemStack toCostify, ISpell toRecord) {
        toCostify.setTagInfo(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL, new NBTTagInt(toRecord.getRequiredExperienceLevels()));
        toCostify.setTagInfo(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS, new NBTTagInt(toRecord.getRequiredExperiencePoints()));
        toCostify.setTagInfo(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS, new NBTTagInt(toRecord.getConsumedExperienceLevels()));
        toCostify.setTagInfo(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS, new NBTTagInt(toRecord.getConsumedExperiencePoints()));
    }

    private static void setWandCooldown(ItemStack toCooldownify, long cooldownMillis) {
        toCooldownify.setTagInfo(SpellMetadataConstants.KEY_COOLDOWN_MILLIS, new NBTTagLong(cooldownMillis));
    }

    private static void setWandPython(ItemStack toPythonify, String python) {
        toPythonify.setTagInfo(SpellMetadataConstants.KEY_SPELL_PYTHON, new NBTTagString(python));
    }

    private static void setWandName(ItemStack toName, String name) {
        if (name.equals(SpellMetadataConstants.NONE)) {
            toName.setStackDisplayName("A Mysterious Pythonic Wand");
        } else {
            toName.setStackDisplayName("A Pythonic Wand of " + name);
        }
    }
}