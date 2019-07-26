#name<=>Land Mine
#cooldown<=>7500
#texture<=>record_chirp
def yscan_down(x,y,z):
	while(getblock(x,y,z) == AIR):
		y-=1
	return y

def teleport_yscan(x,y,z,forceTheIssue=True,scanDistance=10):
	yOrig=y
	while(getblock(x,y,z) != AIR or (forceTheIssue == False and y-yOrig>scanDistance)):
		y+=1
	teleport(x,y,z)


def landmine(distance=6,ignoreYAxis=False):
	yell('Arming stationary device.')
	y = yscan_down(myX(),myY()-1,myZ())
	x=myX()
	z=myZ()
	teleport_yscan(myX()+lookX()*distance,myY() if ignoreYAxis else myY()+lookY()*distance,myZ()+lookZ()*distance)
	time.sleep(.8)
	setblock(x,y,z,TNT)
	setblock(x,y+1,z,STONE_PRESSURE_PLATE)
landmine()