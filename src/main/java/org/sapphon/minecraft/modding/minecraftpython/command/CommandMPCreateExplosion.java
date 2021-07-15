package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandMPCreateExplosion extends CommandMinecraftPythonServer {

    public int x;
    public int y;
    public int z;
    public int size;

    public CommandMPCreateExplosion(int x, int y, int z, int size) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
    }

    public CommandMPCreateExplosion(double x, double y, double z,
                                    double size) {
        this((int) x, (int) y, (int) z, (int) size);
    }

    public CommandMPCreateExplosion(String[] commandAndArgs) {
        this(Double.parseDouble(commandAndArgs[1]),
                Double.parseDouble(commandAndArgs[2]),
                Double.parseDouble(commandAndArgs[3]),
                Double.parseDouble(commandAndArgs[4]));
    }

    public void doWork() {
        WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance()
                .getWorld(0); // TODO ONLY WORKS IN OVERWORLD FOR
        // NOW
        worldserver.createExplosion(new Entity(worldserver) {
            @Override
            protected void entityInit() {
                // TODO Auto-generated method stub

            }

            @Override
            protected void readEntityFromNBT(NBTTagCompound var1) {
                // TODO Auto-generated method stub

            }

            @Override
            protected void writeEntityToNBT(NBTTagCompound var1) {
                // TODO Auto-generated method stub

            }
        }, x, y, z, size, true);
    }

    @Override
    public String serialize() {
        return CommandMinecraftPythonServer.CREATEEXPLOSION_NAME + CommandMinecraftPythonAbstract.SERIAL_DIV + x + CommandMinecraftPythonAbstract.SERIAL_DIV + y + CommandMinecraftPythonAbstract.SERIAL_DIV + z + CommandMinecraftPythonAbstract.SERIAL_DIV + size;
    }

}