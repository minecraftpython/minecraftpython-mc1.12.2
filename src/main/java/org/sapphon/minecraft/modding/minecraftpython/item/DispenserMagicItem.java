package org.sapphon.minecraft.modding.minecraftpython.item;

import net.minecraft.dispenser.IBlockSource;
import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

import java.util.LinkedHashMap;

public class DispenserMagicItem extends BasicMagicItem {
    private final int dispenserFacingX;
    private final int dispenserFacingY;
    private final int dispenserFacingZ;
    private double dispenserX;
    private double dispenserY;
    private double dispenserZ;

    public DispenserMagicItem(ISpell boundSpell, IBlockSource dispenser) {
        super(boundSpell);
        this.dispenserX = dispenser.getX();
        this.dispenserY = dispenser.getY();
        this.dispenserZ = dispenser.getZ();
        this.dispenserFacingX = dispenser.getBlockPos().getX();
        this.dispenserFacingY = dispenser.getBlockPos().getY();
        this.dispenserFacingZ = dispenser.getBlockPos().getZ();
    }

    @Override
    protected SpellInterpreter getSpellInterpreter() {
        return new SpellInterpreter(getDispenserPositionAndFacing());
    }

    private LinkedHashMap<String, String> getDispenserPositionAndFacing() {
        LinkedHashMap<String, String> variableMap = new LinkedHashMap<>();
        variableMap.put("dispenser_x", Double.toString(this.dispenserX));
        variableMap.put("dispenser_y", Double.toString(this.dispenserY));
        variableMap.put("dispenser_z", Double.toString(this.dispenserZ));
        variableMap.put("dispenser_look_x", Double.toString(this.dispenserFacingX));
        variableMap.put("dispenser_look_y", Double.toString(this.dispenserFacingY));
        variableMap.put("dispenser_look_z", Double.toString(this.dispenserFacingZ));
        return variableMap;
    }
}
