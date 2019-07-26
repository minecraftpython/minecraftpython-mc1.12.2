#name<=>What in Blazes...
#texture<=>magma_cream
#cooldown<=>5000
#Student-inspired!
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

def what_the_blazes(distance=10,forward=False,numberOfBlazes=1, ignoreYAxis=False):
	for i in range(numberOfBlazes):
		(x,y,z) = spawn_yscan(myX(),myY(),myZ(),BLAZE)
		spawnparticle(x,y,z,10,LAVA)
	teleport_yscan(myX()-lookX()*distance,myY()-lookY()*distance if ignoreYAxis else myY(),myZ()-lookZ()*distance)
	yell('What the Blazes?')
what_the_blazes()