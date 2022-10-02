from org.sapphon.minecraft.modding.minecraftpython import command
import minecraftpythonvec3 as vector
from entities import *
from org.sapphon.minecraft.modding.minecraftpython import command
from particles import *

###ORIGINS
WORLD = 'world'
PLAYER = 'player'


###ENDORIGINS

def _get_my_position():
    if ('dispenser_x' in globals()):
        return [globals()['dispenser_x'], globals()['dispenser_y'], globals()['dispenser_z']]
    return command.CommandMPGetPlayerPosition().execute()


def _get_looked_at_block_position():
    return command.CommandMPGetPlayerLookedAtBlock().execute()


def _get_my_look():
    if ('dispenser_look_x' in globals()):
        return [globals()['dispenser_look_x'], globals()['dispenser_look_y'], globals()['dispenser_look_z']]
    return command.CommandMPGetPlayerLookVector().execute()


def _get_player_name():
    return command.CommandMPGetPlayerName().execute()


def _get_player_position(playerName):
    return command.CommandMPGetPlayerPosition(playerName).execute()


def myX():
    return _get_my_position()[0]


def myY():
    return _get_my_position()[1]


def myZ():
    return _get_my_position()[2]


def lookX():
    return _get_my_look()[0]


def lookY():
    return _get_my_look()[1]


def lookZ():
    return _get_my_look()[2]


def lookBlockX():
    return _get_looked_at_block_position()[0]


def lookBlockY():
    return _get_looked_at_block_position()[1]


def lookBlockZ():
    return _get_looked_at_block_position()[2]


class Player():
    @property
    def position(self):
        return vector.MinecraftPythonVec3(self.x, self.y, self.z)

    @property
    def x(self):
        return _get_my_position()[0]

    @property
    def y(self):
        return _get_my_position()[1]

    @property
    def z(self):
        return _get_my_position()[2]

    @property
    def look(self):
        return vector.MinecraftPythonVec3(self.lookx, self.looky, self.lookz)

    @property
    def lookx(self):
        return _get_my_look()[0]

    @property
    def looky(self):
        return _get_my_look()[1]

    @property
    def lookz(self):
        return _get_my_look()[2]

    @property
    def looked_at_block_x(self):
        return _get_looked_at_block_position()[0]

    @property
    def looked_at_block_y(self):
        return _get_looked_at_block_position()[1]

	@property
	def looked_at_block_z(self):
		return _get_looked_at_block_position()[2]


player = Player()


def console(commandText):
    command.CommandMPExecuteConsoleCommand(str(commandText)).execute()


def explosion(x, y, z, size):
    command.CommandMPCreateExplosion(x, y, z, size).execute()


def spawnitem(x, y, z, name=BOAT, numberOfItems=1, nbtData='{}'):
    command.CommandMPSpawnItem(x, y, z, name, numberOfItems, nbtData).execute()


def spawnentity(x, y, z, name=PIG, nbtData='{}'):
    command.CommandMPSpawnEntity(x, y, z, name, nbtData).execute()


def spawnparticle(x, y, z, number=10, name=""):
    command.CommandMPSpawnParticle(x, y, z, number, name).execute()


def setblock(x=None, y=None, z=None, blocktype=DIRT, meta=0, origin=WORLD, tileEntityNbtData='{}'):
    if (meta is None):
        meta = 0
    if (blocktype is None):
        if (not x.isdigit()):  # if the argument in the X position is actually a block type, react appropriately
            blocktype = x
            x = None
        else:
            blocktype = DIRT
    if (x is None):
        x = myX()
        y = myY()
        z = myZ()
    command.CommandMPSetBlock(x, y, z, blocktype, meta, tileEntityNbtData).execute()


def propel(x, y, z, entityId=""):
    if (entityId != ""):
        command.CommandMPPropelEntity(x, y, z, entityId).execute()
    else:
        # no entity specified, so propel the executing player
        command.CommandMPPropelEntity(x, y, z).execute()


def getblock(x, y, z):
    blockName = command.CommandMPGetBlock(x, y, z).execute()
    if ('water' in blockName):
        return WATER
    elif ('lava' in blockName):
        return LAVA
    return blockName


def teleport(x, y, z):
    command.CommandMPTeleport(x, y, z).execute()


def cube(x, y, z, blocktype=DIRT, size=2):
    for cubeX in range(0, size):
        for cubeY in range(0, size):
            for cubeZ in range(0, size):
                setblock(x + cubeX, y + cubeY, z + cubeZ, blocktype)


def xrow(startx, y, z, length=2, blocktype=DIRT):
    for n in range(startx, startx + length):
        setblock(n, y, z, blocktype)


def octahedron(x, y, z, blocktype=DIRT, size=3):
    if (size % 2 == 0):
        size += 1
    radius = int((size - 1) / 2)

    def horizontal_slice_of_sphere(slice_y):
        def distance_from_center(level):
            return abs(y - level)

        step = radius - distance_from_center(slice_y)
        step_size = step * 2 + 1
        xrow(x - step, slice_y, z, step_size, blocktype)
        z_offset = 0
        for row_size in range(step_size - 2, 0, -2):
            z_offset += 1
            xrow(x - row_size / 2, slice_y, z + z_offset, row_size, blocktype)
            xrow(x - row_size / 2, slice_y, z - z_offset, row_size, blocktype)

    for n in range(y - radius, y + radius + 1):
        horizontal_slice_of_sphere(n)


def sphere(x, y, z, blocktype=DIRT, size=3):
    octahedron(x, y, z, blocktype, size)


def getrect(x, y, z, x2, y2, z2):
    return [[[getblock(i, j, k) for k in range(z, z2)] for j in range(y, y2)] for i in range(x, x2)]


def setrect(x, y, z, blockTypes):
    for i in range(len(blockTypes) - 1):
        for j in range(len(blockTypes[0]) - 1):
            for k in range(len(blockTypes[0][0]) - 1):
                setblock(x + i, y + j, z + k, blockTypes[i][j][k])


def yell(toYell):
    command.CommandMPBroadcast(str(toYell)).execute()
