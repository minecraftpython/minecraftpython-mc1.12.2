package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandMPSpawnLightningBolt extends CommandMinecraftPythonServer {


    public CommandMPSpawnLightningBolt(String[] commandAndArgsToDeserialize) {
    }

    @Override
    public void doWork() {
        WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
        int x = -175;
        int z = 60;

        int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY() - 1;
        EntityLightningBolt entityLightningBolt = new EntityLightningBolt(world, x, y, z, false);
        world.addWeatherEffect(entityLightningBolt);
        world.spawnEntity(entityLightningBolt);
    }

    @Override
    public String serialize() {
        return CommandMinecraftPythonServer.LIGHTNINGBOLT_NAME;
    }
}
