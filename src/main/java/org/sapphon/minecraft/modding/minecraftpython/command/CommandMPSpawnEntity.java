package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandMPSpawnEntity extends CommandMinecraftPythonServer {
	public double x;
	public double y;
	public double z;
	public String nameOfEntityToSpawn;
	public String nbtData;

	public CommandMPSpawnEntity(double x, double y, double z,
			String entityName, String nbtData) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.nameOfEntityToSpawn = entityName;
		this.nbtData = nbtData;
	}

	public CommandMPSpawnEntity(int x, int y, int z, String name,
			String nbtData) {
		this((double) x, (double) y, (double) z, name, nbtData);
	}

	public CommandMPSpawnEntity(String[] commandAndArgsToDeserialize) {
		this(Double.parseDouble(commandAndArgsToDeserialize[1]), Double
				.parseDouble(commandAndArgsToDeserialize[2]), Double
				.parseDouble(commandAndArgsToDeserialize[3]),
				commandAndArgsToDeserialize[4], commandAndArgsToDeserialize[5]);
	}

	public void doWork() {
		MinecraftServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance();
		World world = worldserver.getEntityWorld();

		Entity entity = EntityLookup.getEntityByName(this.nameOfEntityToSpawn,
				world);
		if (!nbtData.isEmpty() && !nbtData.equals("{}")) {
			try {
				entity.readFromNBT((NBTTagCompound) JsonToNBT
						.getTagFromJson(nbtData));
			} catch (NBTException e) {
				e.printStackTrace();
			}
		}
		entity.setPositionAndRotation(x, y, z, 0, 0);

		// Tamed horse hack
		if (entity instanceof EntityHorse) {
			EntityHorse horse = (EntityHorse) entity;
			horse.setHorseTamed(true);
			horse.setHorseSaddled(true);
		}
		world.spawnEntityInWorld(entity);
	}

	@Override
	public String serialize() {
		return CommandMinecraftPythonServer.SPAWNENTITY_NAME
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + x
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + y
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + z
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + nameOfEntityToSpawn
				+ CommandMinecraftPythonAbstract.SERIAL_DIV + nbtData;
	}

}
