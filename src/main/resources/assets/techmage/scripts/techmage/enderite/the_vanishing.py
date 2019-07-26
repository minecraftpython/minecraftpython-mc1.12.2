#name<=>The Vanishing
#texture<=>ender_pearl
#cooldown<=>3000
def spawn_yscan(x,y,z,entity):
	while(getblock(x,y,z) != AIR):
		y+=1
	spawnentity(x,y,z,entity)
	return (x,y,z)
def teleport_yscan(x,y,z,forceTheIssue=True,scanDistance=10):
	yOrig=y
	while(getblock(x,y,z) != AIR or (forceTheIssue == False and y-yOrig>scanDistance)):
		y+=1
	teleport(x,y,z)

def the_vanishing(distance=16,forward=True,numberOfEndermites=1, ignoreYAxis=False):
	for i in range(numberOfEndermites):
		(x,y,z) = spawn_yscan(myX(),myY(),myZ(),SILVERFISH) #1.7.2 stand-in
		spawnparticle(x,y,z,10,PORTAL)
		spawnparticle(x,y+1,z,10,PORTAL)
		spawnparticle(x,y,z+1,10,PORTAL)
		spawnparticle(x-1,y,z,10,PORTAL)
	teleport_yscan(myX()+lookX()*distance if forward else myX()-lookX()*distance,myY() if ignoreYAxis else myY()+lookY()*distance,myZ()+lookZ()*distance if forward else myZ()-lookZ()*distance)
	time.sleep(.2)
	spawnparticle(myX(),myY()-2,myZ(),10,PORTAL)
	spawnparticle(myX(),myY()-1,myZ(),10,PORTAL)
	spawnparticle(myX(),myY()-2,myZ()+1,10,PORTAL)
	spawnparticle(myX()-1,myY()-2,myZ(),10,PORTAL)
the_vanishing()