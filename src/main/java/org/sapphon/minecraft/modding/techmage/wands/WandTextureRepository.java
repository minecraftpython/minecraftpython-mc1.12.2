package org.sapphon.minecraft.modding.techmage.wands;

import org.sapphon.minecraft.modding.techmage.TechMageMod;

import java.util.ArrayList;
import java.util.List;

public class WandTextureRepository {
	
	private List<String> wandTextureNames;
	private int index = 0;
	private static WandTextureRepository SINGLETON;
	
	public static WandTextureRepository SINGLETON(){
		if(SINGLETON == null)
			SINGLETON = new WandTextureRepository();
		return SINGLETON;
	}

	public String getNextWandTextureName(){
		return getWandTextureNameSafely(index++);
	}
	
	private String getWandTextureNameSafely(int index){
		return wandTextureNames.get(index % wandTextureNames.size());
	}
	
	private WandTextureRepository(){
		wandTextureNames = new ArrayList<String>();
		wandTextureNames.add(TechMageMod.MODID +":"+ "dungeonWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "lavaBoltWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "wrath_of_dardWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "ap_attackWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "armyofdarknessWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "blazestormWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "bossmoveWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "destructiveboltWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "frisbeebombWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "ironarmorWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "liearmorWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "plainBlueWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "plainGreenWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "plainRedWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "plainYellowWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "sidestepWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "snowarmyWand");
		wandTextureNames.add(TechMageMod.MODID +":"+ "tntvanishWand");
		wandTextureNames.add("minecraft:stick");
		wandTextureNames.add("minecraft:blaze_rod");
		wandTextureNames.add("minecraft:diamond_shovel");
		wandTextureNames.add("minecraft:bone");
	}
	
}
