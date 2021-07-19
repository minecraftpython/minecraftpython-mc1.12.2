# minecraftpython
minecraftpython ('Minecraft Python Programming' if you insist) is a mod for Minecraft that provides a Python API allowing users to customize their Minecraft worlds in real time while playing using Python code they've written.
## What's this repo?
This repository is intended for those who are curious about the source code for the mod.  If you're looking to use the mod at home on your computer, you don't need this source code - just a working copy of Minecraft Forge and the mod's .jar file from the [releases](https://github.com/minecraftpython/minecraftpython-mc1.12.2/releases) page.
### How can I use this repo?
See [here](https://github.com/minecraftpython/minecraftpython-mc1.12.2/blob/master/CONTRIBUTING.md) for contributing instructions, including build info.  The rest of *this* document is for end users.

## Installing the Mod
This mod requires Minecraft Forge, and the installation of Forge will not be detailed here.  See the Forge docs for help.

Once Forge is working, this mod is installed the usual way: place the .jar file into your Minecraft copy's `mods` directory.  See the Forge docs for how to find it.

Lastly, since this mod runs Python scripts, you will need to know where to find those!  After running Minecraft with the mod installed for the first time, your Minecraft copy will have a folder inside of it `config/scripts/mp` with a script file `your_code_here.py`.  That's where to write your code, and read others'!

## Using the Mod
### Testing a Script
While playing, you can strike a key (default 'P') to run the contents of your script file immediately.  This is useful while you are still developing your script, to test it.  Any costs attached to execution of the script (in experience points, etc.) are ignored in this mode.

### Saving a Script
Once a script works as you wish, you can hold an item (using a valuable one isn't recommended) and strike a key (default 'K') to record your script onto the item, turning it into a Pythonic wand.

#### Adding Script Metadata when Saving
You can control several things about how a Python script is saved onto an item.  Each is specified by including a 'comment' line in your Python code - a statement that does nothing programmatically, but will be picked up by the mod and respected.

Use the glyph `<=>` between the name of the piece of metadata you want to configure, and its value.
* Name - you can control what name the item used to save the script will take.  Example: `#name<=>Abracadabra` will produce "A Pythonic Wand of Abracadabra".
* Cooldown - a user can be made to wait between uses of the saved script.  This is in milliseconds.  Example: `#cooldown<=>2000` for a 2-second wait.
* Author - you can immortalize yourself for all time as the source of a saved script.  Example: `#author<=>sapphon` will show my username in the tooltip of the item.
* Minimum level - you can prevent players below a certain experience level from running the saved script.  Example: `#cast_min_level<=>4` means Level 4 and above players may use the saved script.
* Minimum XP - similar to the above, but counted in experience points rather than levels.  Example: `#cast_min_xp<=>200` will restrict use to players who possess more than 200 total experience points. (Note: if you specify both this and cast_min_level, cast_min_level will be ignored.)
* Level cost - the specified number of levels will be deducted from the user's experience level every time the saved script is used.  Example: `#cast_level_cost<=>3` will make the saved script 'cost' 3 levels with every use.
* XP cost - more granular than levels, you can also deduct a certain number of experience points per use.  Example: `#cast_xp_cost<=>175` will deduct 175 experience points with every use of the stored script.  (Note: if you specify both this and cast_level_cost, cast_level_cost will be ignored.)

### Running a Script from an Item
To run the script stored in a Pythonic wand, right-click your mouse while holding the wand.  (If you can't pay the script's costs, you'll receive a message with details.)

### Reading a Script from an Item
If you know that the item you are holding in your main hand is a Pythonic wand, you can replace the contents of your script file with the script stored on the item by striking a key (default 'M').

## Issues
### Known Issues
* There is a delay after the use, but before the enaction, of your first Python script after starting Minecraft.  
* The supported version of Python is 2.7.2.  I feel I must personally apologize for that - I know that's not how it should be, but don't know how to fix it.

### How To Report Issues
Have a comment, suggestion, or problem? Find help [here](https://github.com/minecraftpython/minecraftpython-mc1.12.2/issues).
