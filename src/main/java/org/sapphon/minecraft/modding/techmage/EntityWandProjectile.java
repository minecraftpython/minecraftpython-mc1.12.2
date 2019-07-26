package org.sapphon.minecraft.modding.techmage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

public class EntityWandProjectile extends EntityEgg {

	private static double speed = 1.15;

	public EntityWandProjectile(World par1World, EntityLivingBase par2EntityLivingBase, MagicWand magicWand,
			boolean isInaccurate) {
		super(par1World, par2EntityLivingBase);
		this.wand = magicWand;
		if (isInaccurate)
			randomizeVelocity();
		this.motionX = par2EntityLivingBase.getLookVec().x * speed;
		this.motionY = par2EntityLivingBase.getLookVec().y * speed;
		this.motionZ = par2EntityLivingBase.getLookVec().z * speed;
	}

	private MagicWand wand;

	@Override
	protected void onImpact(RayTraceResult raytraceHit) {
		Vec3d hitLocation = raytraceHit.hitVec;
		if (this.wand == null) {
			JavaProblemHandler.printErrorMessageToDialogBox(new Exception(
					"Problems with spell projectile not understanding which wand shot it."));
		} else if (hitLocation == null) {
			JavaProblemHandler.printErrorMessageToDialogBox(new Exception(
					"Problems with the projectile knowing where it landed."));
		}
		if (this.worldObj.isRemote) {
			wand.spellInterpreter.setupImpactVariablesInPython(hitLocation);
			wand.castStoredSpell();
			this.setDead();
		}
	}

	private void randomizeVelocity() {
		double xVelRandom = Math.random() + 0.5f;
		double yVelRandom = Math.random() + 0.5f;
		double zVelRandom = Math.random() + 0.5f;
		this.setVelocity(this.motionX * xVelRandom, this.motionY * yVelRandom, this.motionZ * zVelRandom);
	}

}
