

def set_flat_rectangle(airx, airy, airz, BLOCK, size):
	for nextx in range(size):
		for nextz in range(size):
			setblock( airx + nextx, airy, airz + nextz, BLOCK)

def arena (x=-37, y=64, z=-37):

	for height in range(20):
		set_flat_rectangle(x, y+height, z,  SANDSTONE, 75)

	set_flat_rectangle(x+0, y+20, z, AIR, 75)
	
	set_flat_rectangle(x+1, y+19, z+1,  AIR, 73)
	set_flat_rectangle(x+1, y+18, z+1,  AIR, 73)
	set_flat_rectangle(x+1, y+17, z+1,  AIR, 73)

	set_flat_rectangle(x+2, y+16, z+2,  AIR, 71)

	set_flat_rectangle(x+3, y+15, z+3,  AIR, 69)

	set_flat_rectangle(x+4, y+14, z+4,  AIR,  67)

	set_flat_rectangle (x+5, y+13, z+5, AIR, 65)

	set_flat_rectangle (x+6, y+12, z+6, AIR, 63)
	
	set_flat_rectangle (x+7, y+11, z+7, AIR, 61)
	
	set_flat_rectangle (x+8, y+10, z+8, AIR, 59)
	
	set_flat_rectangle (x+9, y+9, z+9, AIR, 57)
	
	set_flat_rectangle (x+10, y+8, z+10, AIR, 55)
	
	set_flat_rectangle (x+11, y+7, z+11, AIR, 53)
	
	set_flat_rectangle (x+12, y+6, z+12, AIR, 51)
	
	set_flat_rectangle (x+12, y+5, z+12, AIR, 51)
	set_flat_rectangle (x+12, y+4, z+12, AIR, 51)
	set_flat_rectangle (x+12, y+3, z+12, AIR, 51)
	set_flat_rectangle (x+12, y+2, z+12, AIR, 51)
	set_flat_rectangle (x+12, y+1, z+12, AIR, 51)

	for n in range (12, 64):
		setblock (x+n, y+6, z+11, SANDSTONE)
	for n in range (12, 64):
		setblock (x+n, y+6, z+63, SANDSTONE)
	for n in range (12, 64):
		setblock (x+11, y+6, z+n, SANDSTONE)
	for n in range (12, 64):
		setblock (x+63, y+6, z+n, SANDSTONE)
	for n in range (12, 64):
		setblock (x+n, y+7, z+11, SANDSTONE)
	for n in range (12, 64):
		setblock (x+n, y+7, z+63, SANDSTONE)
	for n in range (12, 64):
		setblock (x+11, y+7, z+n, SANDSTONE)
	for n in range (12, 64):
		setblock (x+63, y+7, z+n, SANDSTONE)
	for n in range (12, 64):
		setblock (x+n, y+8, z+11, SANDSTONE)
	for n in range (12, 64):
		setblock (x+n, y+8, z+63, SANDSTONE)
	for n in range (12, 64):
		setblock (x+11, y+8, z+n, SANDSTONE)
	for n in range (12, 64):
		setblock (x+63, y+8, z+n, SANDSTONE)
	for n in range (12, 63):
		setblock (x+n, y+6, z+12, TORCH)
	for n in range (12, 63):
		setblock (x+n, y+6, z+62, TORCH)
	for n in range (12, 63):
		setblock (x+12, y+6, z+n, TORCH)
	for n in range (12, 63):
		setblock (x+62, y+6, z+n, TORCH)
	cube (x+37, y+1, z+0, AIR, 3)
	cube (x+37, y+2, z+0, AIR, 3)
	cube (x+37, y+1, z+3, AIR, 3)
	cube (x+37, y+2, z+3, AIR, 3)
	cube (x+37, y+1, z+6, AIR, 3)
	cube (x+37, y+2, z+6, AIR, 3)
	cube (x+37, y+1, z+8, AIR, 3)
	cube (x+37, y+2, z+8, AIR, 3)
	cube (x+37, y+1, z+10, AIR, 3)
	cube (x+37, y+2, z+10, AIR, 3)

def make_3d_construction_area(floor=56):
	FRAMEBLOCK = STONE
	height=50

	cube(-26,56,-26,AIR,53)

	for n in range(height):
		setblock(-26,floor+n,-26,FRAMEBLOCK)
		setblock(-26,floor+n, 26,FRAMEBLOCK)
		setblock( 26,floor+n,-26,FRAMEBLOCK)
		setblock( 26,floor+n, 26,FRAMEBLOCK)

	for n in range(53):
		setblock(-26,	floor-1,-26+n,	FRAMEBLOCK)
		setblock( 26,	floor-1,-26+n,	FRAMEBLOCK)
		setblock(-26+n,	floor-1,-26,	FRAMEBLOCK)
		setblock(-26+n,	floor-1, 26,	FRAMEBLOCK)

		setblock(-26,	floor+height,-26+n,	FRAMEBLOCK)
		setblock( 26,	floor+height,-26+n,	FRAMEBLOCK)
		setblock(-26+n,	floor+height,-26,	FRAMEBLOCK)
		setblock(-26+n,	floor+height, 26,	FRAMEBLOCK)

	setblock(-25, floor-0,	-25,DIAMOND_BLOCK)
	setblock(-25, floor-1, 	-25,GOLD_BLOCK)
	setblock(-25, floor-2,	-25,IRON_BLOCK)

	setblock(25, floor-3+height, 25,DIAMOND_BLOCK)
	setblock(25, floor-2+height, 25,GOLD_BLOCK)
	setblock(25, floor-1+height, 25,IRON_BLOCK)