#name<=>Comfortable Abode
#cooldown<=>30000
#texture<=>bed
def stash(x,y,z):
	cube(x,y,z,AIR,3)
	teleport(x+1,y,z+1)

def window(x,y,z,blocktype):
	setblock(x,y,z,blocktype)
	setblock(x,y+1,z,blocktype)

def lights(x,y,z,blocktype):
	setblock(x+1,y+3,z+1,blocktype)
	setblock(x+3,y+3,z+1,blocktype)
	setblock(x+1,y+3,z+3,blocktype)
	setblock(x+3,y+3,z+3,blocktype)

def windows(x,y,z,blocktype):
	window(x+2,y+2,z,blocktype)
	window(x,y+2,z+2,blocktype)
	window(x+4,y+2,z+2,blocktype)

def door(x,y,z):
	setblock(x+2,y+1,z+4,WOODEN_DOOR)
	setblock(x+2,y,z+5,BRICK_BLOCK)

def comfortable_abode(x,y,z):
	stash(30000,6,30000)
	cube(x,y,z,BRICK_BLOCK,5)
	cube(x+1,y+1,z+1,AIR,3)
	windows(x,y,z,GLASS)
	lights(x,y,z,TORCH)
	door(x,y,z)
	setblock(x+1,y+1,z+1,CHEST)
	spawnitem(x+2,y+2,z+2,BRICK_BLOCK,64)
	spawnitem(x+2,y+2,z+2,IRON_PICKAXE)
	spawnitem(x+2,y+2,z+2,GOLDEN_APPLE,4)
	spawnitem(x+2,y+2,z+2,WATER_BUCKET)
	spawnitem(x+2,y+2,z+2,BED)
	teleport(x+2,y+2,z+2)
if(lookY() > 0):
	comfortable_abode(myX(),myY()+20,myZ())
else:
	comfortable_abode(myX()-2,myY()-2,myZ()-2)