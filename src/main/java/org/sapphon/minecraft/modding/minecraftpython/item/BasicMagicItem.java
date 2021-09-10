package org.sapphon.minecraft.modding.minecraftpython.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.sapphon.minecraft.modding.mcutil.PlayerHelper;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonMod;
import org.sapphon.minecraft.modding.minecraftpython.async.SpellCastingRunnable;
import org.sapphon.minecraft.modding.minecraftpython.async.ThreadFactory;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandMPTakeItem;
import org.sapphon.minecraft.modding.minecraftpython.interpreter.SpellInterpreter;
import org.sapphon.minecraft.modding.minecraftpython.io.file.MinecraftPythonScriptLoader;
import org.sapphon.minecraft.modding.minecraftpython.network.meta.PacketMinecraftPythonDeductExperience;
import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;

import static org.sapphon.minecraft.modding.mcutil.PlayerHelper.logToPlayer;

//For dealing with the client-side aspects of a Pythonic wand
public class BasicMagicItem {
    private ISpell storedSpell;
    private long lastCast = 0;

    public BasicMagicItem(ISpell boundSpell) {
        storedSpell = boundSpell;
    }


    private long timer() {
        return System.currentTimeMillis() - lastCast;
    }

    public void attemptMagic(EntityPlayer spellcaster, ItemStack wand) {
        if (meetsMinima(spellcaster)) {
            if (canPayCost(spellcaster)) {
                if (timer() > storedSpell.getCooldownInMilliseconds()) {
                    deductCastingCost(spellcaster);
                    damageOrDecrementItemStackUnlessInCreative(wand, spellcaster);
                    this.doMagic();
                    lastCast = System.currentTimeMillis();
                } else {
                    logToPlayer(spellcaster, "Cooldown not finished");
                }
            } else {
                logToPlayer(spellcaster, "Not enough experience to pay the casting cost.");
            }
        } else {
            logToPlayer(spellcaster, "Not enough experience to know how to use this.");
        }
    }

    private void damageOrDecrementItemStackUnlessInCreative(ItemStack wand, EntityPlayer spellcaster) {
        new CommandMPTakeItem(spellcaster.getName()).execute();
    }

    protected void deductCastingCost(EntityPlayer spellcaster) {
        int consumedExperiencePoints = this.storedSpell.getConsumedExperiencePoints();
        int consumedExperienceLevels = this.storedSpell.getConsumedExperienceLevels();

        if (consumedExperiencePoints > 0) {
            MinecraftPythonMod.serverMetaPacketChannel.sendToServer(new PacketMinecraftPythonDeductExperience(consumedExperiencePoints, false));
        } else if (consumedExperienceLevels > 0) {
            MinecraftPythonMod.serverMetaPacketChannel.sendToServer(new PacketMinecraftPythonDeductExperience(consumedExperienceLevels, true));
        }
    }

    protected boolean canPayCost(EntityPlayer player) {
        int pointCost = this.storedSpell.getConsumedExperiencePoints();
        int levelCost = this.storedSpell.getConsumedExperienceLevels();
        if (pointCost == 0 && levelCost == 0) {
            return true;
        } else if (pointCost != 0) {
            return PlayerHelper.canPayExperiencePointCost(player, pointCost);
        } else {
            return player.experienceLevel >= levelCost;

        }
    }

    protected boolean meetsMinima(EntityPlayer player) {
        int pointMin = this.storedSpell.getRequiredExperiencePoints();
        int levelMin = this.storedSpell.getRequiredExperienceLevels();
        if (pointMin == 0 && levelMin == 0) {
            return true;
        } else if (pointMin != 0) {
            return PlayerHelper.canPayExperiencePointCost(player, pointMin);
        } else {
            return player.experienceLevel >= levelMin;

        }
    }

    protected synchronized void castStoredSpell() {
        ThreadFactory.makeSpellThread(
                new SpellCastingRunnable(this.storedSpell, new SpellInterpreter()))
                .start();
    }

    public ISpell getStoredSpell() {
        return this.storedSpell;
    }

    public void readFromItem(ItemStack heldItemMainhand) {
        if (WandReaderWriter.isMagicWand(heldItemMainhand)) {
            MinecraftPythonScriptLoader.SINGLETON().writeToScript(WandReaderWriter.getWandPython(heldItemMainhand));
        }
    }

    public void doMagic() {
        castStoredSpell();
    }
}
