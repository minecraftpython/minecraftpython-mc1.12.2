#name<=>Discombobulator Ray
#target_type<=>ray
#cooldown<=>20000
import random
def getSelectorString(prefix,x,y,z,radius=3):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'
def getPlayerSelectorString(x,y,z):
	return getSelectorString('p',x,y,z,12)
def getRandomTeleportString(x,y,z):
	return str(x)+' '+str(y)+' '+str(z)# + ' ' + str(random.uniform(-180,180)) + ' ' + str(random.uniform(-90,90))	#maybe in 1.8?  

def discombobulator_ray(x,y,z,discombobulation_limit=5,discombobulations=1):
	time.sleep(1)
	newX=x+random.uniform(-1,1)
	newY=y+random.uniform(-1,1)
	newZ=z+random.uniform(-1,1)
	newHeadBlock=getblock(newX,newY+1,newZ)
	while(newHeadBlock != AIR and newHeadBlock != WATER):
		newHeadBlock=getblock(newX,newY+1,newZ)
		newY+=1
	console('tp '+getPlayerSelectorString(x,y,z)+' '+getRandomTeleportString(newX,newY,newZ))
	if(discombobulations<discombobulation_limit):
		discombobulator_ray(newX,newY,newZ,discombobulation_limit,discombobulations+1)
	time.sleep(1)
	console('tp '+getPlayerSelectorString(newX,newY,newZ)+' '+getRandomTeleportString(x,y,z))
discombobulator_ray(ray_x,ray_y,ray_z)