package org.sapphon.minecraft.modding.mcutil;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class BlockLookup {
    public static Block getBlockWithName(String name) {
        Block registryResult = Block.REGISTRY.getObject(new ResourceLocation(name.toLowerCase()));
        return registryResult == null ? Blocks.DIRT : registryResult;
    }

}
