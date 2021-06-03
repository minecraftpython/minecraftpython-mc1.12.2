package org.sapphon.minecraft.modding.minecraftpython.network.meta;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketMinecraftPythonDeductExperience implements IMessage {
    public PacketMinecraftPythonDeductExperience(){}

    public int toDeduct;
    public boolean isLevels;

    public PacketMinecraftPythonDeductExperience(int toDeduct, boolean isLevels) {
        this.toDeduct = toDeduct;
        this.isLevels = isLevels;
    }

    @Override public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isLevels);
        buf.writeInt(toDeduct);
    }

    @Override public void fromBytes(ByteBuf buf) {
        isLevels = buf.readBoolean();
        toDeduct = buf.readInt();
    }
}