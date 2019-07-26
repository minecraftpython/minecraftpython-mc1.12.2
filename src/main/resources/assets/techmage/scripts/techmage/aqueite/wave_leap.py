#name<=>Wave Leap
#texture<=>boat
#cooldown<=>4000
def setblock_yscandown(x,y,z,blockType=DIRT,forceTheIssue=True,scanDistance=10,blockMetadata=0):
	yOrig=y
	while(getblock(x,y-1,z) == AIR):
		y-=1
		if(forceTheIssue == False and yOrig-y>scanDistance):
			break
	setblock(x,y,z,blockType,blockMetadata)
	return (x,y,z)

def replace_block_within_radius(x,y,z,radius=8,blockTypeToReplace=DIRT,replacement=AIR,replacementMeta=0):
	for i in range(x-radius,x+radius):
		for j in range(y-radius,y+radius):
			for k in range(z-radius,z+radius):
				if(getblock(i,j,k) == blockTypeToReplace):
					setblock(i,j,k,replacement,replacementMeta)

def wave_leap(duration=2.0, increment=0.06,propulsion=2, blockType=WATER, size=5):
	import random
	import time
	eyeHeightCompensator=2
	yell('Wave Leap!')
	blocksPlaced=list()
	startTime=time.time()
	propel(propulsion*lookX(),propulsion,propulsion*lookZ())
	while(time.time() < startTime+duration):
		(x,y,z) = (myX(),myY()-eyeHeightCompensator,myZ())
		time.sleep(increment)
		(x,y,z) = setblock_yscandown(x,y,z,blockType)
		blocksPlaced.append((x,y,z))
		spawnparticle(x+random.randint(-size,size),y+random.randint(1,size),z+random.randint(-size,size),2,SPLASH)
	(x, groundY, z) = setblock_yscandown(myX(),myY(),myZ(),blockType,forceTheIssue=True)
	time.sleep(.2)
	teleport(x,groundY,z)
	blocksPlaced.append((x, groundY, z))
	time.sleep(1)
	for (x,y,z) in blocksPlaced:
		replace_block_within_radius(x,y,z,4,WATER,AIR)
wave_leap()