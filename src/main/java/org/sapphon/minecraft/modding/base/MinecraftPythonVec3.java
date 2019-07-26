package org.sapphon.minecraft.modding.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class MinecraftPythonVec3 {
		 public MinecraftPythonVec3(double x2, double y2, double z2) {
			 x=x2;
			 y=y2;
			 z=z2;
		 }
		 public MinecraftPythonVec3(EntityPlayer player) {
			 this(player.posX,player.posY,player.posZ);
		
		 }
		public MinecraftPythonVec3(Vec3d lookVec) {
			this(lookVec.x, lookVec.y,lookVec.z);
		}
		public double x;
		 public double y;
		 public double z;
		 
		 public int blockX(){return (int) Math.round(x);}
		 public int blockY(){return (int) Math.round(y);}
		 public int blockZ(){return (int) Math.round(z);}
		 
		 public MinecraftPythonVec3 add(MinecraftPythonVec3 other){
			 return  new MinecraftPythonVec3(this.x+other.x, this.y+other.y, this.z+other.z);
		 }
		 
		 public MinecraftPythonVec3 multiplyBy(double multiplier){
			 return  new MinecraftPythonVec3(this.x*multiplier, this.y*multiplier, this.z*multiplier);
		 }
		 
		 @Override
		 public String toString(){
			 return "["+x+","+y+","+z+"]";
		 }
		 
		 public static MinecraftPythonVec3 UP = new MinecraftPythonVec3(0,1,0);
		 public static MinecraftPythonVec3 DOWN = new MinecraftPythonVec3(0, -1, 0);
		 public static MinecraftPythonVec3 LEFT = new MinecraftPythonVec3(-1,0,0);
		 public static MinecraftPythonVec3 RIGHT = new MinecraftPythonVec3(1, 0, 0);
		 public static MinecraftPythonVec3 FORWARD = new MinecraftPythonVec3(0,0,1);
		 public static MinecraftPythonVec3 BACK = new MinecraftPythonVec3(0, 0, -1);
}
