#name<=>Lava Bolt
#texture<=>blaze_rod
#courtesy of David Arditti
def lavabolt ():
	lx = lookX()
	ly = lookY()
	lz = lookZ()
	mx = myX()
	my = myY()
	mz = myZ()
	for n in range (2,18):
		setblock (mx+lx*n, my-1+ly*n, mz+lz*n, LAVA)
		time.sleep (.04)
		setblock (mx+lx*n, my-1+ly*n, mz+lz*n, FIRE)
		time.sleep (.04)
	setblock (mx+lx*18, my-1+ly*18, mz+lz*18, LAVA)
lavabolt()
