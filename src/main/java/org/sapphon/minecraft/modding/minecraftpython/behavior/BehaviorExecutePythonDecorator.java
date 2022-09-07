package org.sapphon.minecraft.modding.minecraftpython.behavior;

import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import org.sapphon.minecraft.modding.minecraftpython.item.DispenserMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.item.WandReaderWriter;
import org.sapphon.minecraft.modding.minecraftpython.spells.StringSpell;

import java.util.List;

public class BehaviorExecutePythonDecorator implements IBehaviorDispenseItem {

    private IBehaviorDispenseItem defaultBehavior;

    public BehaviorExecutePythonDecorator(IBehaviorDispenseItem defaultBehavior) {
        if(defaultBehavior instanceof BehaviorExecutePythonDecorator) {
            this.defaultBehavior = ((BehaviorExecutePythonDecorator) defaultBehavior).getWrappedBehavior();
        }
        else {
            this.defaultBehavior = defaultBehavior;
        }
    }

    public IBehaviorDispenseItem getWrappedBehavior(){
        return this.defaultBehavior;
    }

    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        if (WandReaderWriter.isMagicWand(stack)) {
            List<String> wandDispenserBehaviors = WandReaderWriter.getWandDispenserBehaviors(stack);
            wandDispenserBehaviors.forEach(System.out::println);
            if (wandDispenserBehaviors.isEmpty() || wandDispenserBehaviors.contains("cast")) {
                new DispenserMagicItem(new StringSpell(WandReaderWriter.getWandPython(stack)), source).doMagic();
            }

            if(wandDispenserBehaviors.contains("dispense")){
                return defaultBehavior.dispense(source, stack);
            } else{
                if (stack.getCount() <= 1) {
                    return ItemStack.EMPTY;
                } else {
                    stack.shrink(1);
                    return stack;
                }
            }

        } else return defaultBehavior.dispense(source, stack);
    }
}
