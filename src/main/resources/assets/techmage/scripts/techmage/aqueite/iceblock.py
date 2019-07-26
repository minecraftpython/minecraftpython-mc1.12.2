#name<=>Ice Block
#texture<=>diamond
#courtesy of David Arditti
def iceblock ():
	mx = myX()
	my = myY()
	mz = myZ()
	cube (mx-2, my-3, mz-2, ICE, 4)
	cube (mx-1, my-2, mz-1, AIR, 2)
	setblock(mx-2, my, mz-2, AIR)
	setblock(mx+1, my, mz+1, AIR)
	setblock(mx-2, my, mz+1, AIR)
	setblock(mx+1, my, mz-2, AIR)
iceblock()
