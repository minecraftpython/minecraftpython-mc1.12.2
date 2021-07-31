package org.sapphon.minecraft.modding.minecraftpython.item.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecipePythonicWand extends ShapedRecipes {
    private static RecipePythonicWand instance;
    private Map<List<String>,ItemStack> ingredientsToWands;

    public synchronized static RecipePythonicWand SINGLETON() {
        if (instance == null) {
            instance = new RecipePythonicWand("",3,3, NonNullList.create(), new ItemStack(Blocks.DIRT));
        }
        return instance;
    }

    private RecipePythonicWand(String group, int width, int height, NonNullList<Ingredient > ingredients, ItemStack result) {
        super(group,width,height,ingredients,result);
        ingredientsToWands = new LinkedHashMap<>();
        this.setRegistryName("minecraftpython","recipePythonicWand");
    }

    public void registerRecipeForWand(ItemStack wand, List<String> itemNames){
        this.ingredientsToWands.put(itemNames, wand.copy());
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn){
        for (List<String> ingredientList : ingredientsToWands.keySet()) {
            if(compareInvToItemList(inv, ingredientList)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        for (List<String> ingredientList : ingredientsToWands.keySet()) {
            if(compareInvToItemList(inv, ingredientList)){
                return ingredientsToWands.get(ingredientList).copy();
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareInvToItemList(InventoryCrafting inv, List<String> itemNames) {
        for(int i=0;i<itemNames.size();i++){
            if(inv.getStackInSlot(i).getItem().getUnlocalizedName().compareToIgnoreCase("item."+itemNames.get(i)) != 0 && (!itemNames.get(i).isEmpty() || !inv.getStackInSlot(i).getItem().getUnlocalizedName().equals("tile.air"))){
                return false;
            }
        }
        return true;
    }
}
