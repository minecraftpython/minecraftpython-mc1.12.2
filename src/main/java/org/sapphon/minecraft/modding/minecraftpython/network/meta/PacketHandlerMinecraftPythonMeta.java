package org.sapphon.minecraft.modding.minecraftpython.network.meta;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;

public class PacketHandlerMinecraftPythonMeta implements IMessageHandler<PacketMinecraftPythonDeductExperience, IMessage> {
    @Override
    public IMessage onMessage(PacketMinecraftPythonDeductExperience message, MessageContext ctx) {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
        if (message.isLevels) {
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                serverPlayer.addExperienceLevel(-message.toDeduct);
            });
        } else {
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                PlayerHelper.deductExperiencePoints(serverPlayer, message.toDeduct);
            });
        }
        return null;
    }
}