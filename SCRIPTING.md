# minecraftpython Scripting
minecraftpython ('Minecraft Python Programming' if you insist) is a mod for Minecraft that provides a Python API allowing users to customize their Minecraft worlds in real time while playing using Python code they've written.

Those scripts can carry 'metadata', which is information about how and when those scripts are allowed to be run.  Maybe the player needs experience to use them, or must execute a crafting recipe - things of that nature.
## Adding Script Metadata
Script metadata items are specified in comments, one per line.

Use the glyph `<=>` between the name of the piece of metadata you want to configure, and its value.

#### Identity Metadata
* **Name** - you can control what name the item used to save the script will take.  Example: `#name<=>Abracadabra` will produce "A Pythonic Wand of Abracadabra".
* **Author** - you can immortalize yourself for all time as the source of a saved script.  Example: `#author<=>sapphon` will show my username in the tooltip of the item.
#### Use-Limiting Metadata
* **Cooldown** - a user can be made to wait between uses of the saved script.  This is in milliseconds.  
  * Example: `#cooldown<=>2000` for a 2-second wait.
* **Minimum level** - you can prevent players below a certain experience level from running the saved script.  
  * Example: `#cast_min_level<=>4` means Level 4 and above players may use the saved script.
* **Minimum XP** - similar to the above, but counted in experience points rather than levels.  (Note: if you specify both this and cast_min_level, cast_min_level will be ignored.)
  * Example: `#cast_min_xp<=>200` will restrict use to players who possess more than 200 total experience points. 
* **Level cost** - the specified number of levels will be deducted from the user's experience level every time the saved script is used.  
  * Example: `#cast_level_cost<=>3` will make the saved script 'cost' 3 levels with every use.
* **XP cost** - more granular than levels, you can also deduct a certain number of experience points per use.  (Note: if you specify both this and cast_level_cost, cast_level_cost will be ignored.)
  * Example: `#cast_xp_cost<=>175` will deduct 175 experience points with every use of the stored script.
#### Reproduction Metadata
* **Smelting ingredient** - players can create an item with your script on it by smelting the named item in a smelter.
  * Example: `#smelting_ingredient<=>iron_hoe` will make it possible to smelt the scripted item from an iron hoe
* **Anvil ingredient** - players can duplicate the item with your script on it by placing it with the named item in an anvil.
  * Example: `#anvil_ingredient<=>diamond` will make it possible to duplicate the scripted item in an anvil by paying 1 diamond
* **Anvil cost** - Scripts with anvil ingredients can also cost any number of experience levels at the time of the anvil's use.
  * Example: `#anvil_level_cost<=>8` will make players Level 7 and below unable to duplicate your item at an anvil, and Level 8+ players will go down 8 levels when they do.
* **Crafting ingredients** - (this one's complicated) players can craft an item with your script on it at the crafting table.  This piece of metadata specifies the recipe.  It is nine item names separated by commas (so eight commas), starting at the top left of the crafting grid and ending at the bottom right, reading left to right.  So, the recipe for a diamond pickaxe would be `diamond,diamond,diamond,,stick,,,stick,`.
  * Example: `crafting_ingredients<=>,torch,,torch,torch,torch,,stick,` will make it possible to craft the scripted item by crafting a cross shape of 4 torches and 1 stick in the crafting table with the stick bottommost
