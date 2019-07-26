package org.sapphon.minecraft.modding.techmage;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.sapphon.minecraft.modding.minecraftpython.IArcane;
import org.sapphon.minecraft.modding.minecraftpython.ScriptLoaderConstants;
import org.sapphon.minecraft.modding.minecraftpython.command.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellCastingRunnable;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellThreadFactory;
import org.sapphon.minecraft.modding.techmage.wands.WandTextureRepository;
import org.sapphon.minecraft.modding.techmage.wands.WandType;

import java.io.File;

public class MagicWand extends Item implements IArcane {
	private WandType wandType;
	private ISpell storedSpell;
	public SpellInterpreter spellInterpreter;
	private long lastCast = 0;
	private int experienceLevelRequirement;

	public MagicWand(ISpell spell, int experienceLevelRequirement,
			CreativeTabs creativeModeInventoryTab) {
		super();
		this.experienceLevelRequirement = experienceLevelRequirement;
		this.setCreativeTab(creativeModeInventoryTab);
		storedSpell = spell;
		if (wandHasHardcodedTexture()) {
			this.setUnlocalizedName(TechMageMod.MODID + ":"
					+ spell.getSpellShortName()
					+ ScriptLoaderConstants.WAND_TEXTURE_NAME_SUFFIX);
		} else if (this.getSpell().hasCustomTexture()) {
			String customTextureName = this.getSpell().getCustomTextureName();
			if (customTextureName.contains(":")) {
				this.setUnlocalizedName(customTextureName);
			} else {
				this.setUnlocalizedName("minecraft:" + customTextureName);
			}
		} else {
			this.setUnlocalizedName(WandTextureRepository.SINGLETON()
					.getNextWandTextureName());
		}
		this.maxStackSize = 1;
		this.setUnlocalizedName(spell.getSpellShortName()
				+ ScriptLoaderConstants.WAND_TEXTURE_NAME_SUFFIX);
		this.wandType = spell.getWandType();
		this.spellInterpreter = new SpellInterpreter();
	}

	private boolean wandHasHardcodedTexture() {// TODO this needs to look at the
												// textures wrapped up in the
												// jar with the mod
		return new File(ScriptLoaderConstants.WAND_TEXTURE_LOCATION
				+ this.storedSpell.getSpellShortName()
				+ ScriptLoaderConstants.WAND_TEXTURE_NAME_SUFFIX + ".png")
				.exists();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemThatWasRightClicked,
													World world, EntityPlayer techmage, EnumHand hand) {
		if (techmage.experienceLevel < this.experienceLevelRequirement) {
			if (world.isRemote) {
				techmage.addChatMessage(new TextComponentString(
						"Not enough experience!"));
			}
		} else if (timer() > this.getSpell().getCooldownInMilliseconds()) {
			this.castSpellFromWand(techmage, world);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemThatWasRightClicked);
	}

	private long timer() {
		return System.currentTimeMillis() - lastCast;
	}

	public void castSpellFromWand(EntityLivingBase magicCaster, World world) {
		if (this.wandType.equals(WandType.LOCAL) && world.isRemote) {
			castStoredSpell();
		} else if (this.wandType.equals(WandType.PROJECTILE)) {
			world.spawnEntityInWorld(new EntityWandProjectile(world,
					magicCaster, this, false));
		} else if (this.wandType.equals(WandType.RAY) && world.isRemote) {
			RayTraceResult rayTrace = Minecraft.getMinecraft().getRenderViewEntity()
					.rayTrace(300, 1.0f);
			spellInterpreter.setupRayVariablesInPython(rayTrace);
			castStoredSpell();
		}
		lastCast = System.currentTimeMillis();
	}

	protected synchronized void castStoredSpell() {
		SpellThreadFactory.makeSpellThread(
				new SpellCastingRunnable(this.storedSpell, spellInterpreter))
				.start();
	}

	public int getExperienceLevelRequiredForUse() {
		return this.experienceLevelRequirement;
	}

	public ISpell getSpell() {
		return this.storedSpell;
	}

	@Override
	public void doMagic() {
		castStoredSpell();
	}
}
