package org.sapphon.minecraft.modding.minecraftpython.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.sapphon.minecraft.modding.minecraftpython.behavior.BehaviorExecutePythonDecorator;
import org.sapphon.minecraft.modding.minecraftpython.item.recipe.RecipePythonicWand;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.metadata.SpellMetadataConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WandReaderWriter {
    public static void recordOntoItem(ISpell toRecord, ItemStack toRecordOnto) {
        setWandName(toRecordOnto, toRecord.getDisplayName());
        setWandCooldown(toRecordOnto, toRecord.getCooldownInMilliseconds());
        setWandRequiredExperience(toRecordOnto, toRecord);
        setWandAuthor(toRecordOnto, toRecord.getAuthorName());
        setWandPython(toRecordOnto, toRecord.getPythonScriptAsString());
        setWandDispenserBehaviors(toRecordOnto, toRecord.getDispenserBehaviors());
        setAnvilRepair(toRecordOnto, toRecord);
        setWandSmeltingRecipe(toRecordOnto, toRecord.getSmeltingIngredient());
        setWandCraftingRecipe(toRecordOnto, toRecord.getCraftingIngredients());
        registerWandDispenserBehavior(toRecordOnto);
    }

    protected static void registerWandDispenserBehavior(ItemStack toRecordOnto){
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(toRecordOnto.getItem(), new BehaviorExecutePythonDecorator(BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(toRecordOnto.getItem())));
    }

    protected static void setAnvilRepair(ItemStack toRecordOnto, ISpell toRecord) {
        String ingredientName = toRecord.getAnvilIngredient();
        int anvilCost = toRecord.getAnvilCost();
        if(!ingredientName.equals(SpellMetadataConstants.NONE)){
            toRecordOnto.setTagInfo(SpellMetadataConstants.KEY_ANVIL_ITEM, new NBTTagString(ingredientName));
            if(anvilCost != 0){
                toRecordOnto.setTagInfo(SpellMetadataConstants.KEY_ANVIL_COST, new NBTTagInt(anvilCost));
            }
        }
    }

    protected static void setWandCraftingRecipe(ItemStack toRecordOnto, List<String> strings) {
        if(strings.size() == 9) {
            RecipePythonicWand.SINGLETON().registerRecipeForWand(toRecordOnto, strings);
        }
    }

    protected static void setWandDispenserBehaviors(ItemStack toRecordOnto, List<String> dispenserBehaviors) {
            if (dispenserBehaviors.size() > 0) {
                toRecordOnto.setTagInfo(SpellMetadataConstants.KEY_DISPENSER_BEHAVIOR, new NBTTagString(String.join(",", dispenserBehaviors)));
            }
    }

    protected static void setWandSmeltingRecipe(ItemStack toBeSmelted, String ingredientName) {
        if (!ingredientName.equals(SpellMetadataConstants.NONE)) {
            Item ingredientItem = Item.getByNameOrId(ingredientName);
            if (ingredientItem != null) {
                GameRegistry.addSmelting(ingredientItem, toBeSmelted.copy(), 2f);
            }
        }
    }

    public static String getWandAnvilItem(ItemStack toRead){
        return toRead.getTagCompound() != null ? toRead.getTagCompound().getString(SpellMetadataConstants.KEY_ANVIL_ITEM) : SpellMetadataConstants.NONE;
    }

    public static List<String> getWandDispenserBehaviors(ItemStack toRead){
        String behaviorsString = toRead.getTagCompound() != null ? toRead.getTagCompound().getString(SpellMetadataConstants.KEY_DISPENSER_BEHAVIOR) : SpellMetadataConstants.NONE;
        if(behaviorsString.isEmpty() || behaviorsString.equals(SpellMetadataConstants.NONE)) return Collections.emptyList();
        return Arrays.stream(behaviorsString.split(",",9)).collect(Collectors.toList());
    }

    public static int getWandAnvilCost(ItemStack toRead) {
        return toRead.getTagCompound() != null ? toRead.getTagCompound().getInteger(SpellMetadataConstants.KEY_ANVIL_COST) : 0;
    }

    public static boolean isMagicWand(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return (tagCompound != null && tagCompound.hasKey(SpellMetadataConstants.KEY_SPELL_PYTHON));
    }

    protected static void setWandAuthor(ItemStack toAuthorify, String authorName) {
        toAuthorify.setTagInfo(SpellMetadataConstants.KEY_AUTHOR_NAME, new NBTTagString(authorName));
    }

    protected static void setWandRequiredExperience(ItemStack toCostify, ISpell toRecord) {
        toCostify.setTagInfo(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_LEVEL, new NBTTagInt(toRecord.getRequiredExperienceLevels()));
        toCostify.setTagInfo(SpellMetadataConstants.KEY_REQUIRED_EXPERIENCE_POINTS, new NBTTagInt(toRecord.getRequiredExperiencePoints()));
        toCostify.setTagInfo(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_LEVELS, new NBTTagInt(toRecord.getConsumedExperienceLevels()));
        toCostify.setTagInfo(SpellMetadataConstants.KEY_CONSUMED_EXPERIENCE_POINTS, new NBTTagInt(toRecord.getConsumedExperiencePoints()));
    }

    protected static void setWandCooldown(ItemStack toCooldownify, long cooldownMillis) {
        toCooldownify.setTagInfo(SpellMetadataConstants.KEY_COOLDOWN_MILLIS, new NBTTagLong(cooldownMillis));
    }

    protected static void setWandPython(ItemStack toPythonify, String python) {
        toPythonify.setTagInfo(SpellMetadataConstants.KEY_SPELL_PYTHON, new NBTTagString(python));
    }

    protected static void setWandName(ItemStack toName, String name) {
        if (name.equals(SpellMetadataConstants.NONE)) {
            toName.setStackDisplayName("A Mysterious Pythonic Wand");
        } else {
            toName.setStackDisplayName("A Pythonic Wand of " + name);
        }
    }

    public static String getWandPython(ItemStack toRead){
        return toRead.getTagCompound() != null ? toRead.getTagCompound().getString(SpellMetadataConstants.KEY_SPELL_PYTHON) : SpellMetadataConstants.NONE;
    }
}
