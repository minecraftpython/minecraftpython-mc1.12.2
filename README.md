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
#### Testing a Script
While playing, you can strike a key (default 'P') to run the contents of your script file immediately.  This is useful while you are still developing your script, to test it.  Any costs attached to execution of the script (in experience points, etc.) are ignored in this mode.

#### Saving a Script
Once a script works as you wish, you can hold an item (using a valuable one isn't recommended) and strike a key (default 'K') to record your script onto the item, turning it into a Pythonic wand.

#### Running a Script from an Item
To run the script stored in a Pythonic wand, right-click your mouse while holding the wand.  (If you can't pay the script's costs, you'll receive a message with details.)

#### Reading a Script from an Item
If you know that the item you are holding in your main hand is a Pythonic wand, you can replace the contents of your script file with the script stored on the item by striking a key (default 'M').

## Issues
### Known Issues
* Recording a magic wand over a magic wand currently creates a Frankenbeast.  Beware!  This will be corrected in 0.7.2.
* The supported version of Python is 2.7.2.  I feel I must personally apologize for that - I know that's not how it should be, but don't know how to fix it.

### How To Report Issues
Have a comment, suggestion, or problem? Find help [here](https://github.com/minecraftpython/minecraftpython-mc1.12.2/issues).
