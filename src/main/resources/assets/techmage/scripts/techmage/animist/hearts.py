#name<=>Hearts
#cooldown<=>12000

def getSelectorString(prefix,x,y,z,radius=0,duration=8):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+(',r='+str(int(radius)) if radius != 0 else '')+']'
def getAllSelectorString(x,y,z,radius):
	return getSelectorString('a',x,y,z,radius)
def hearts(radius=5,duration=8):
	for n in range(0,duration+1,2):
		selector=getAllSelectorString(myX(),myY(),myZ(),radius)
		console('effect '+selector+' 6')
		console('effect '+ selector + ' 2 2 3')
		spawnparticle(myX(),myY(),myZ(),20,HEART)
		spawnparticle(myX(),myY(),myZ(),20,RED_DUST)
		time.sleep(2)
hearts()