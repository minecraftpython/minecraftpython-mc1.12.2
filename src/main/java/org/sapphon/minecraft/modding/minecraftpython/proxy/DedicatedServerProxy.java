package org.sapphon.minecraft.modding.minecraftpython.proxy;

import net.minecraftforge.common.MinecraftForge;
import org.sapphon.minecraft.modding.minecraftpython.command.queue.ServerTickHandler;

public class DedicatedServerProxy extends CommonProxy {
    public DedicatedServerProxy() {
        MinecraftForge.EVENT_BUS.register(new ServerTickHandler());
    }
}
