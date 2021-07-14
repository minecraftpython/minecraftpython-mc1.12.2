package org.sapphon.minecraft.modding.minecraftpython.factory;

import org.sapphon.minecraft.modding.minecraftpython.item.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

public class MagicItemFactory {
    public static BasicMagicItem createBasic(ISpell boundSpell) {
        return new BasicMagicItem(boundSpell);
    }

}
