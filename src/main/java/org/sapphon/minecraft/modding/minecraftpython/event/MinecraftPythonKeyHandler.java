package org.sapphon.minecraft.modding.minecraftpython.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandMPEnsorcelItem;
import org.sapphon.minecraft.modding.minecraftpython.item.BasicMagicItem;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonMod;
import org.sapphon.minecraft.modding.minecraftpython.ModConfigurationFlags;

public class MinecraftPythonKeyHandler {
	public static final int CAST_SPELL_KEY_INDEX = 0;
	public static final int RECORD_WAND_KEY_INDEX = 1;
	public static final int READ_WAND_KEY_INDEX = 2;
	private static final String[] keyDescriptions = { "key.castspell.desc", "key.recordwand.desc", "key.readwand.desc"};
	private static final int[] defaultKeyValues = { Keyboard.KEY_P, Keyboard.KEY_K, Keyboard.KEY_M };
	private final KeyBinding[] keyBindings;
	private BasicMagicItem device;
	
	public MinecraftPythonKeyHandler(BasicMagicItem magicDeviceToActivateWhenCastSpellIsPressed) {
		this.device = magicDeviceToActivateWhenCastSpellIsPressed;
		keyBindings = new KeyBinding[keyDescriptions.length];
		for (int i = 0; i < keyDescriptions.length; ++i) {
			keyBindings[i] = new KeyBinding(keyDescriptions[i],
					defaultKeyValues[i], "key."
							+ MinecraftPythonMod.MODID + ".category");
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (ModConfigurationFlags.MINECRAFT_PYTHON_PROGRAMMING() &&
				keyBindings[CAST_SPELL_KEY_INDEX].isPressed()) {
			device.doMagic();
		}
		else if (ModConfigurationFlags.WAND_RECORDING() &&
				keyBindings[RECORD_WAND_KEY_INDEX].isPressed()) {
			new CommandMPEnsorcelItem(Minecraft.getMinecraft().player.getName(), device.getStoredSpell().getPythonScriptAsString()).execute();
		} else if (ModConfigurationFlags.WAND_READING() &&
			keyBindings[READ_WAND_KEY_INDEX].isPressed()) {
			device.readFromItem(Minecraft.getMinecraft().player.getHeldItemMainhand());
		}
	}
}