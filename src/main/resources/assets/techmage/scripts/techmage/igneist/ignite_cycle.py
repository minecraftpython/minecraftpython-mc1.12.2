#name<=>Ignite Cycle
#texture<=>blaze_powder
#cooldown<=>7500
#This one's for Nate.
def setblock_yscan(x,y,z,blockType=DIRT,forceTheIssue=True,scanDistance=10,blockMetadata=0):
	yOrig=y
	while(getblock(x,y,z) != AIR or (forceTheIssue == False and y-yOrig>scanDistance)):
		y+=1
	setblock(x,y,z,blockType,blockMetadata)
	return (x,y,z)

def ignite_cycle(duration=5.0, increment=0.1,distanceBack=2,maxDistanceVertical=3):
	yell('Ignite Cycle!')
	eyeHeightCompensator=2
	console('effect @p 12 '+str(int(duration)))
	console('effect @p 1 '+str(int(duration)))
	for i in range(increment/duration):
		(x,y,z) = (myX()-lookX()*distanceBack,myY()-eyeHeightCompensator,myZ()-lookZ()*distanceBack)
		time.sleep(increment)
		(x,y,z) = setblock_yscan(x,y-maxDistanceVertical,z,FIRE,False,maxDistanceVertical*2)
		spawnparticle(x,y,z,10,SMOKE)
ignite_cycle()