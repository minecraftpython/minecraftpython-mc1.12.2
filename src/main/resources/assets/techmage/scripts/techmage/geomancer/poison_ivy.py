#name<=>Poison Ivy
#texture<=>wheat
#target_type<=>projectile
#cooldown<=>9000
def getSelectorString(prefix,x,y,z,radius):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'
def getAllSelectorString(x,y,z,radius):
	return getSelectorString('a',x,y,z,radius)
def cactusy_wall(x,y,z,radius,xDirection=True):
	for n in range(x-radius,x+radius):
		for k in range(z-radius,z+radius):
			for m in range(y+radius,y-radius,-1):
				if(getblock(n,m,k)!=AIR):
					setblock(n,m,k,SAND)
					break
	time.sleep(.5)
	for n in range(x-radius,x+radius,2):
		for k in range(z-radius,z+radius,2):
			for m in range(y+radius,y-radius,-1):
				if(getblock(n,m,k)==SAND):
					setblock(n,m+1,k,CACTUS)
					break

def poison_ivy(x,y,z,dirX,dirZ,radius=3,initialDelay=2,duration=5,power=3,poison_power=1):
	yell('Rise, earth-spirits!')
	cactusy_wall(x,y,z,radius,True if dirX > dirZ else False)
	time.sleep(initialDelay-.5)#cactusy wall waits .5
	console('effect '+getAllSelectorString(x,y,z,radius)+' 2 '+str(int(duration))+' '+str(int(power)))
	console('effect '+getAllSelectorString(x,y,z,radius)+' 19 '+str(int(duration))+' '+str(int(poison_power)))
poison_ivy(impact_x,impact_y,impact_z,lookX(),lookZ())