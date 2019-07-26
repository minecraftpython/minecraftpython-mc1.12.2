#name<=>Dimension Door
#texture<=>door_wood
#cooldown<=>20000
def makeNetherPortalAndLightIt(x,y,z,undo=False):
		cube(x,y,z,OBSIDIAN if not undo else AIR,6)
		cube(x+1,y,z,AIR,6)
		cube(x-1,y+1,z+1,AIR,4)
		time.sleep(1)
		setblock(x,y+1,z+1,FIRE if not undo else AIR)

def dimension_door(x,y,z,dimensionType="Nether",duration=9):
	if(dimensionType == "Nether"):
		makeNetherPortalAndLightIt(x,y,z)
	else:
		pass # not implemented
	time.sleep(duration-1)#make portal sleeps for 1 second
	if(dimensionType == "Nether"):
		makeNetherPortalAndLightIt(x,y,z,undo=True)
dimension_door(myX()+lookX()*5, myY()-2, myZ()+lookZ()*5)
