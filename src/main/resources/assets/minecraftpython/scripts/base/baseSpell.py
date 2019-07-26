import sys
from org.sapphon.minecraft.modding.minecraftpython import command
from java.util import List, ArrayList
import minecraftpythonvec3 as vector
import time
from blocks import *
from items import *
from entities import *
from particles import *
from colors import *
###ORIGINS
WORLD='world'
PLAYER='player'
###ENDORIGINS

def _get_my_position():
	return command.CommandMPGetPlayerPosition().execute()
	
def _get_my_look():
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

class Player():
    @property
    def position(self):
        return vector.MinecraftPythonVec3(self.x,self.y,self.z)

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
        return vector.MinecraftPythonVec3(self.lookx,self.looky,self.lookz)

    @property
    def lookx(self):
        return _get_my_look()[0]

    @property
    def looky(self):
        return _get_my_look()[1]

    @property
    def lookz(self):
        return _get_my_look()[2]

player=Player()

def console(commandText):
	command.CommandMPExecuteConsoleCommand(str(commandText)).execute()

def explosion(x,y,z,size):
	command.CommandMPCreateExplosion(x,y,z,size).execute()

def spawnitem(x,y,z,name=BOAT, numberOfItems=1,nbtData='{}'): 
	command.CommandMPSpawnItem(x,y,z,name,numberOfItems,nbtData).execute()

def spawnentity(x,y,z,name=PIG,nbtData='{}'):
	command.CommandMPSpawnEntity(x,y,z,name,nbtData).execute()

def spawnparticle(x,y,z,number=10, name=""):
    command.CommandMPSpawnParticle(x,y,z,number,name).execute()

def setblock(x=None,y=None,z=None,blocktype=DIRT,meta=0,origin=WORLD,tileEntityNbtData='{}'):
	if(meta is None):
			meta=0
	if(blocktype is None):
		if(not x.isdigit()): #if the argument in the X position is actually a block type, react appropriately
			blocktype=x
			x=None
		else:
			blocktype=DIRT
	if(x is None):
		x=myX()
		y=myY()
		z=myZ()
	command.CommandMPSetBlock(x,y,z,blocktype,meta,tileEntityNbtData).execute()
	#_setblock_sparkle(x,y,z)   #currently not performant enough for use

def propel(x,y,z,entityId="",origin=WORLD):
	if(origin==PLAYER):
		x=x*lookX()
		y=y*lookY()
		z=z*lookZ()
	if(entityId != ""):
		command.CommandMPPropelEntity(x,y,z,entityId).execute()
	else:
		#no entity specified, so propel the player
		command.CommandMPPropelEntity(x,y,z).execute()

def getblock(x,y,z):
		blockName = command.CommandMPGetBlock(x,y,z).execute()
		if('water' in blockName):
	   		return WATER
		elif('lava' in blockName):
	   		return LAVA
		return blockName
	
def teleport(x,y,z):
		command.CommandMPTeleport(x,y,z).execute()

def cube(x,y,z,blocktype=DIRT,size=2):
		for cubeX in range(0,size):
			for cubeY in range(0,size):
				for cubeZ in range(0, size):
					setblock(x+cubeX, y+cubeY, z+cubeZ, blocktype)

def getrect(x,y,z,x2,y2,z2):    #since these names are less than cool, x,y,z, is one corner and x2,y2,z2 are the other corner of an imaginary a rectangle
   		return [[[getblock(i,j,k) for k in range(z,z2)] for j in range(y,y2)] for i in range(x,x2)]

def setrect(x,y,z, listOfListOfListsOfBlockTypes):
		for i in range(len(listOfListOfListsOfBlockTypes) - 1):
			for j in range(len(listOfListOfListsOfBlockTypes[0]) - 1):
				for k in range(len(listOfListOfListsOfBlockTypes[0][0]) - 1):
					setblock(x+i,y+j,z+k,listOfListOfListsOfBlockTypes[i][j][k])    

def yell(toYell):
	command.CommandMPBroadcast(str(toYell)).execute()

def makeitsuperweird(targetPlayerName):
	command.CommandMPApplyShader(targetPlayerName).execute()
