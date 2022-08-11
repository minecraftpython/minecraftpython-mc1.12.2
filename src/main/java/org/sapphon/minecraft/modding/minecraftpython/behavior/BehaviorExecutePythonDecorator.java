package org.sapphon.minecraft.modding.minecraftpython.behavior;

import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import org.sapphon.minecraft.modding.minecraftpython.item.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.item.WandReaderWriter;
import org.sapphon.minecraft.modding.minecraftpython.spells.StringSpell;

public class BehaviorExecutePythonDecorator implements IBehaviorDispenseItem {

    private IBehaviorDispenseItem defaultBehavior;

    public BehaviorExecutePythonDecorator(IBehaviorDispenseItem defaultBehavior){
        this.defaultBehavior = defaultBehavior;
    }
    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        if (WandReaderWriter.isMagicWand(stack)) {
            new BasicMagicItem(new StringSpell(WandReaderWriter.getWandPython(stack))).doMagic();
        }
        return defaultBehavior.dispense(source, stack);
    }
}
