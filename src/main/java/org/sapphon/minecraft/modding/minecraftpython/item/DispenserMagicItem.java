package org.sapphon.minecraft.modding.minecraftpython.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;
import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

import java.util.LinkedHashMap;

public class DispenserMagicItem extends BasicMagicItem {
    private int dispenserFacingX;
    private int dispenserFacingY;
    private int dispenserFacingZ;
    private double dispenserX;
    private double dispenserY;
    private double dispenserZ;

    public DispenserMagicItem(ISpell boundSpell, IBlockSource dispenser) {
        super(boundSpell);
        EnumFacing facing = dispenser.getBlockState().getValue(BlockDispenser.FACING);
        Vec3i directionVec = facing.getDirectionVec();
        this.dispenserFacingX = directionVec.getX();
        this.dispenserFacingY = directionVec.getY();
        this.dispenserFacingZ = directionVec.getZ();
        this.dispenserX = dispenser.getX() + directionVec.getX();
        this.dispenserY = dispenser.getY() + directionVec.getY();
        this.dispenserZ = dispenser.getZ() + directionVec.getZ();
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
