package org.sapphon.minecraft.modding.minecraftpython.factory;

import org.sapphon.minecraft.modding.minecraftpython.RudimentaryMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

public class MagicItemFactory {
    public static RudimentaryMagicItem create(ISpell boundSpell) {
        return new RudimentaryMagicItem(boundSpell);
    }

}
