#name<=>The Cold Never Bothered Me Anyway
#cooldown<=>9000
#texture<=>snowball
#target_type<=>projectile
INSTANT_DAMAGE='7'
def getSelectorString(prefix,x,y,z,radius=3):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'

def Slick(x,y,z,length=8, width=8, height=8, material=ICE,duration=8):
	blocksReplaced=list()
	for n in range(x-width/2,x+width/2):
		for k in range(z-length/2,z+length/2):
			for m in range(y+height/2,y-height/2,-1):
				if(getblock(n,m,k)!=AIR):
					blocksReplaced.append(getblock(n,m,k))
					setblock(n,m,k,material)
					break
	console('effect '+getSelectorString('a',x,y,z,width/2)+ ' ' + INSTANT_DAMAGE)
	time.sleep(duration/2)
	console('effect '+getSelectorString('a',x,y,z,width/2)+ ' ' +INSTANT_DAMAGE)
	time.sleep(duration/2)
	console('effect '+getSelectorString('a',x,y,z,width/2) +' '+ INSTANT_DAMAGE)
	index=0
	for n in range(x-width/2,x+width/2):
		for k in range(z-length/2,z+length/2):
			for m in range(y+height/2,y-height/2,-1):
				if(getblock(n,m,k)!=AIR):
					setblock(n,m,k,blocksReplaced[index])
					index+=1
					break

Slick(impact_x,impact_y,impact_z)