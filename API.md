# `minecraftpython` API reference

## Functions for discovering information about the Minecraft world

None of these functions alter your game world, only inform your script about it.

* `myX` 
	* IN: nothing
	* OUT: floating-point number
	* USES: returns the current X-coordinate (east/west in Minecraft) of the executing player.  `player.x` is a property that does the same.

* `myY` 
	* IN: nothing
	* OUT: floating-point number
	* USES: returns the current Y-coordinate (up/down in Minecraft) of the executing player.  `player.y` is a property that does the same.

* `myZ` 
	* IN: nothing
	* OUT: floating-point number
	* USES: returns the current Z-coordinate (north/south in Minecraft) of the executing player.  `player.z` is a property that does the same.

* `lookX`
	* IN: nothing
	* OUT: floating-point number, normalized
	* USES: returns the current X-component (east/west in Minecraft) of the executing player's 'look vector'. `player.lookx` is a property that does the same.

* `lookY`
	* IN: nothing
	* OUT: floating-point number, normalized
	* USES: returns the current Y-component (up/down in Minecraft) of the executing player's 'look vector'. `player.looky` is a property that does the same.

* `lookZ`
	* IN: nothing
	* OUT: floating-point number, normalized
	* USES: returns the current Z-component (north/south in Minecraft) of the executing player's 'look vector'. `player.lookz` is a property that does the same.

* `getblock`
 	* IN: three integers, `x`, `y` and `z`
 	* OUT: string
 	* USES: The string is the name of the block present at the coordinates specified by the three arguments.  (There is always a block present in Minecraft, even if it's insensible, like 'air' is.)

* `getrect`
	* IN: six integers, `x`, `y`, `z`, `x2`, `y2`, `z2`
	* OUT: A three-dimensional array of strings
	* USES: This function's returned value names the block types present within a rectangular area, the extents of which are bounded by the points (x,y,z) and (x2,y2,z2).

## Properties for discovering information about the Minecraft world

None of these properties alter your game world, only inform your script about it.

* `player.position`
	* IN: nothing
	* OUT: MinecraftPythonVec3
	* USES: Returns the position of the executing player as a 3-axis vector.

* `player.look`
	* N: nothing
	* OUT: MinecraftPythonVec3
	* USES: Returns the 'look vector' of the executing player.

## Functions for altering the Minecraft world

The Minecraft world is *primarily* made of items, blocks, and entities.  

* Blocks are the air you breathe, the water you (mostly) don't, the ground you walk on, and the stairs you construct.  These are notable for always having integer coordinates in the world.  2 blocks can't exist at the same coordinates.

* Entities are you, your pets and animals, your monstrous opposition, projectiles, and anything else in the world that moves and occupies space.  Entities have floating-point coordinates, and unlike Blocks, are capable of motion.  Blocks that *appear to* move - falling sand or gravel, for instance - are converted into Entities while they fall, and become Blocks again once they land.

* Items are the shield you wield, the hat you wear, the fishing rod you cast, and the blaze rod you carry around.  Some items are only useful for crafting other items.  Items *don't* have coordinates in the world; they either exist in the inventory of an entity, or they exist as an entity themselves (in the case of an item thrown on the ground). 

### Functions for placing blocks

* `setblock`
	* IN: three integer coordinates `x`, `y`, and `z`, a string `blocktype`, an integer `meta`, and a string `tileEntityNbtData`
	* OUT: nothing
	* USES: Place a single block in the world.  Place it at coordinates (`x`,`y`,`z`).  Place the block named in `blocktype`, or dirt if not specified.  Use a meta value (for e.g. the facing of stairs) of `meta`, or use 0 if not specified.  Finally, apply `tileEntityNbtData` to the placed block, or no data ('{}') if not specified.  There's a lot beyond the scope of this document to know about NBT data, see [the wiki for more information.](https://minecraft.fandom.com/wiki/Tutorials/Command_NBT_tags#Blocks)

* `cube`
	* IN: three integer coordinates `x`, `y`, and `z`, a string `blocktype`, and an integer `size`
	* OUT: nothing
	* USES: Place blocks in the world in a cube shape.  Beginning at coordinates (`x`,`y`,`z`), fill with `blocktype` (or dirt if not specified) until a cube with length, height, and width of `size` (or 2 if not specified) is built.

* `setrect`

	* IN: three integer coordinates `x`, `y`, and `z`, and a three-dimensional array of strings `blockTypes`
	* OUT: nothing
	* USES: Beginning at one corner (`x`, `y`, `z`), set blocks in a rectangle described by the extents of the `blockTypes` array.

* `xrow`
	* IN: three integer coordinates `startx`, `y`, and `z`, an integer `length`, and a string `blocktype`
	* OUT: nothing
	* USES: create a row of the same type of block along the X axis, leaving Y and Z coordinates unchanged throughout.  The row will begin at (`startx`, `y`, `z`) and end at (`startx`+`length`, `y`, `z`).  `length` is 2 and `blocktype` is dirt if not specified.

* `octahedron`
	* IN: three integer coordinates `x`, `y`, and `z` for the center of the octahedron, a string `blocktype`, and an integer `size`
	* OUT: nothing
	* USES: Place blocks in the world in an octohedronal shape, centered at (`x`,`y`,`z`).  Blocktype is dirt if not specified.  `size` refers to the diameter, and is 3 if not specified.  Note that if an even number is specified as the size, the odd number that is 1 greater than the specified size will be used.

* `sphere` is currently syntactic sugar for `octahedron`.

### Functions for creating entities

* `spawnentity`
	* IN: three floating point coordinates `x`, `y`, and `z` for the spawn point of the entity, a string `name` for the type of entity to spawn, and a string `nbtData`
	* OUT: nothing
	* USES: Create a new entity in the game world.  What it will do after being created is dependent upon the type of entity, specified by `name`.  Many useful properties specific to the Entity can be set as part of its `nbtData`, see [the wiki for more info.](https://minecraft.fandom.com/wiki/Tutorials/Command_NBT_tags#Entities)

### Functions for transforming entities

* `propel`
	* IN: three floating point force components `x`, `y` and `z`, and a string `entityId`.
	* OUT: nothing
	* USES: propels the identified entity (entityId is optional, defaulting to the executing player) in the direction represented by the vector (`x`, `y`, `z`).  This propulsion is subject to "Minecraft physics" - things don't really slide, so you should typically include a positive Y component, for example.

* `teleport`
	* IN: three floating point destination coordinates `x`, `y` and `z`
	* OUT: nothing
	* USES: Transports the executing player to the given coordinates instantly.  Be cautious in Survival Mode, as teleporting into something solid by accident can be fatal.

### Functions for creating or transforming items

* `spawnitem`
	* IN: three floating point coordinates `x`, `y` and `z` for the spawn point of the Item Entity, a string `name` for the type of item to spawn, an integer `numberOfItems` to spawn, and a string `nbtData`.
	* OUT: nothing
	* USES: Spawn an EntityItem containing a stack of size `numberOfItems` containing the Item type specified by `name` into the gameworld at the given coordinates.  If used at a player's coordinates, the item will be added to the player's inventory near-immediately.  Otherwise, the EntityItems will obey normal Minecraft physics and e.g. fall as normal.  Item NBT as specified in `nbtData` can be extensive, see [the wiki for more info.](https://minecraft.fandom.com/wiki/Tutorials/Command_NBT_tags#Items)

### Other functions that cause a change in game state

* `console`
	* IN: string
	* OUT: nothing
	* USES: Passes the input string directly to Minecraft's command-line interface.

* `explosion`
	* IN: three floating point coordinates `x`, `y`, and `z` for the explosion center, and an integer `size` for the size (or power) of the explosion.
	* OUT: nothing
	* USES: Exploding stuff.  Occurs instantly, but large ones can cause framerate drops.  The `size` of a block of TNT exploding is 3; that of a creeper exploding, 4.  

## Miscellaneous functions

* `spawnparticle`
	* IN: three floating point coordinates `x`, `y`, and `z`, a n integer `number` of particles to spawn, and a string `name`
	* OUT: nothing
	* USES: show a particle - a graphic, in other words - at a location.  `number` will be 10 if not specified.  Particle `name` will determine the behavior next - lava particles will tend to "pop" like popcorn while End particles will hover still, for example.

* `yell`
	* IN: string
	* OUT: nothing
	* USES: Displays the same string in chat to every player on the server.  Does not appear to come from any player.