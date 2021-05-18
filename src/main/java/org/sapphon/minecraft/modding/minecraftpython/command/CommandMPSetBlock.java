package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.block.Block;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.sapphon.minecraft.modding.mcutil.BlockLookup;

public class CommandMPSetBlock extends CommandMinecraftPythonServer {

	private int x;
	private int y;
	private int z;
	private String blockType;
	private int metadata;
	private String tileEntityNbtData;

	public CommandMPSetBlock(int x, int y, int z, String blockType,
			int metadata, String nbtDataForTileEntity) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.blockType = blockType;
		this.metadata = metadata;
		this.tileEntityNbtData = nbtDataForTileEntity;
	}

	public CommandMPSetBlock(double x, double y, double z,
			String blockType, int metadata, String nbtDataForTileEntity) {
		this((int) x, (int) y, (int) z, blockType, metadata, nbtDataForTileEntity);
	}

	public CommandMPSetBlock(String[] commandAndArgsToDeserialize) {
		this(Integer.parseInt(commandAndArgsToDeserialize[1]), Integer
				.parseInt(commandAndArgsToDeserialize[2]), Integer
				.parseInt(commandAndArgsToDeserialize[3]),
				commandAndArgsToDeserialize[4], Integer
						.parseInt(commandAndArgsToDeserialize[5]), commandAndArgsToDeserialize[6]);
	}

	public void doWork() {
		WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance()
				.getWorld(0);// TODO
		Block blocky = BlockLookup.getBlockWithName(blockType);
		
		boolean setBlock = worldserver.setBlockState(new BlockPos(x, y, z), blocky.getStateFromMeta(metadata));
		if (!tileEntityNbtData.isEmpty() && !tileEntityNbtData.equals("{}")) {
			NBTTagCompound nbtTagCompound = null;
			try {
				nbtTagCompound = (NBTTagCompound) JsonToNBT.getTagFromJson(tileEntityNbtData);
			} catch (NBTException e) {
				e.printStackTrace();
			}
			TileEntity tileentity = worldserver.getTileEntity(new BlockPos(x, y, z));

			if (tileentity != null && nbtTagCompound != null) {
				nbtTagCompound.setInteger("x", x);
				nbtTagCompound.setInteger("y", y);
				nbtTagCompound.setInteger("z", z);
				tileentity.readFromNBT(nbtTagCompound);
			}
		}
	}

	@Override
	public String serialize() {
		return CommandMinecraftPythonServer.SETBLOCK_NAME + SERIAL_DIV + x
				+ SERIAL_DIV + y + SERIAL_DIV + z + SERIAL_DIV + blockType
				+ SERIAL_DIV + metadata + SERIAL_DIV + tileEntityNbtData;
	}

}
