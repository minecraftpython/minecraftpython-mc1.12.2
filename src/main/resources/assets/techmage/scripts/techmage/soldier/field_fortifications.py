#name<=>Field Fortifications
#texture<=>brick
#cooldown<=>20000
def wall(x,y,z,x2,z2,material):
	if(x!=x2 and z!=z2):
		return # this isn't how wall works
	elif(x!=x2):
		for n in range(x,x2):
			for m in range(3):
				setblock(n,y+m,z,GLASS if m == 1 and n % 2 != 0 else material)
	else:
		for n in range(z,z2):
			for m in range(3):
				setblock(x,y+m,n,GLASS if m == 1 and n % 2 != 0 else material)

def field_fortifications(x,y,z,material=STONE_BRICK,size=4):
	yell('Erecting redoubt in my A-O!')
	#walls
	wall(x-size,y,z-size,x+size,z-size,material)
	wall(x-size,y,z+size,x+size,z+size,material)

	wall(x-size,y,z-size,x-size,z+size,material)
	wall(x+size,y,z-size,x+size,z+size,material)
	#doorway
	setblock(x,y,z-size,AIR)
	setblock(x,y+1,z-size,AIR)
	#clear the area of hostile blocks
	cube(x-(size-1),y,z-(size-1),AIR,(size-1)*2)
	#floor and ceiling
	for n in range(size*2):
		for m in range(size*2):
			setblock(x-(size-1)+n,y-1,z-(size-1)+m,material)
			if(n<(size*2)-1 and m<(size*2)-1):
				setblock(x-(size-1)+n,y+4,z-(size-1)+m, GLASS if m % 2 == 0 and n % 2 != 0 else material)
	teleport(x,y,z)
	#lights
	setblock(x-(size-1),y+2,z-(size-1),TORCH)
	setblock(x-(size-1),y+2,z+(size-1),TORCH)
	setblock(x+(size-1),y+2,z-(size-1),TORCH)
	setblock(x+(size-1),y+2,z+(size-1),TORCH)
field_fortifications(myX(),myY()-2,myZ())