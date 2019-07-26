#name<=>Build a Buddy
#cooldown<=>6000

def raycast_distance(x,y,z,dirx,diry,dirz,acceptableBlocks=[AIR,WATER,GLASS,ICE],distance=300):
	for n in range(distance):
		if(getblock(x+dirx*n,y+diry*n,z+dirz*n) not in acceptableBlocks):
			return n
#This spell is written to be simpler than my usual, and even contain mistakes.  It is an exercise in refactoring.
def build_a_buddy(x,y,z):
	delay=1
	#body
	setblock(x,y+1,z,IRON_BLOCK)
	time.sleep(.2)
	setblock(x,y-1,z,IRON_BLOCK)
	time.sleep(.2)
	#arms
	setblock(x+2,y+1,z,IRON_BLOCK)
	time.sleep(.2)
	setblock(x-2,y+1,z,IRON_BLOCK)
	time.sleep(.2)
	#pistons
	setblock(x-3,y+1,z,PISTON,5)
	time.sleep(.2)
	setblock(x+3,y+1,z,PISTON,4)
	time.sleep(.2)
	setblock(x,y-2,z,PISTON,1)
	time.sleep(.2)
	setblock(x,y+4,z,PISTON,0)
	time.sleep(.2)
	#head(fake)
	setblock(x,y+3,z,PUMPKIN)

	time.sleep(delay)
	
	#activate pistons
	setblock(x-4,y+1,z,REDSTONE_BLOCK)
	time.sleep(.2)
	setblock(x+4,y+1,z,REDSTONE_BLOCK)
	time.sleep(.2)
	setblock(x,y-3,z,REDSTONE_BLOCK)
	time.sleep(.2)
	setblock(x,y+5,z,REDSTONE_BLOCK)
	#head (real)
	setblock(x,y+2,z,PUMPKIN)

def build_a_buddy_raycast(x,y,z,lx,ly,lz):
	n = raycast_distance(x,y,z,lx,ly,lz)
	build_a_buddy(x+lx*n,y+ly*n+2,z+lz*n)
build_a_buddy_raycast(myX(),myY(),myZ(),lookX(),lookY(),lookZ())