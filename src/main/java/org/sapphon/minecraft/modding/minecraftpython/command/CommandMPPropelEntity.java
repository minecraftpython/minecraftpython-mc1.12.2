package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;
import java.util.List;

public class CommandMPPropelEntity extends CommandMinecraftPythonServer {

	public double xVel;
	public double yVel;
	public double zVel;
	public int idToPropel;

	public CommandMPPropelEntity(double x, double y, double z) {
		this(x, y, z, Minecraft.getMinecraft().player.getEntityId());
	}

	public CommandMPPropelEntity(double x, double y, double z,
			int entityIdToPropel) {
		this.xVel = x;
		this.yVel = y;
		this.zVel = z;
		this.idToPropel = entityIdToPropel;
	}

	public CommandMPPropelEntity(String[] commandAndArgsToDeserialize) {
		this(Double.parseDouble(commandAndArgsToDeserialize[1]), Double
				.parseDouble(commandAndArgsToDeserialize[2]), Double
				.parseDouble(commandAndArgsToDeserialize[3]), Integer
				.parseInt(commandAndArgsToDeserialize[4]));
	}

	public void doWork() {
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
		List<Entity> list = new ArrayList<Entity>(world.loadedEntityList);
		Entity toPropel = null;
		for (Entity entity : list) {
			if (entity.getEntityId() == this.idToPropel) {
				toPropel = entity;
				break;
			}
		}
		if (toPropel != null) {
			toPropel.addVelocity(xVel, yVel, zVel);
			toPropel.velocityChanged = true;
		}
	}

	@Override
	public String serialize() {
		return CommandMinecraftPythonServer.PROPEL_NAME
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + xVel
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + yVel
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + zVel
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + idToPropel;
	}
}