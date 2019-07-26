#name<=>Proximity Grenade
#cooldown<=>15000
#target_type<=>projectile

def getSelectorString(prefix,x,y,z,radius=3):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'

def setblock_yscandown(x,y,z,blockType=DIRT,forceTheIssue=True,scanDistance=10):
	yOrig=y
	while(getblock(x,y-1,z) == AIR):
		y-=1
		if(forceTheIssue == False and yOrig-y>scanDistance):
			break
	setblock(x,y,z,blockType)
	return (x,y,z)

class SpotExploder:
	def __init__(self,x,y,z):
		self.x=x
		self.y=y
		self.z=z
	def emplace(self, targetX, targetY, targetZ, strength=5):
		setblock(self.x,self.y,self.z,COMMAND_BLOCK,tileEntityNbtData='{Command:"summon Creeper '+str(int(targetX))+' '+str(int(targetY))+' '+str(int(targetZ)) + ' {CustomName:Proximity Device,Fuse:0,ExplosionRadius:'+str(int(strength))+'}"}')
	def remove(self):
		setblock(self.x,self.y,self.z,AIR)

class PlayerDetector:
	def __init__(self,x,y,z):
		self.x=x
		self.y=y
		self.z=z
	def emplace(self,targetX,targetY,targetZ,radius=5):
		setblock(self.x,self.y,self.z,COMMAND_BLOCK,tileEntityNbtData='{Command:"testfor '+getSelectorString('p',targetX,targetY,targetZ,radius)+'"}')
		setblock(self.x+1,self.y-1,self.z,STONE)
		setblock(self.x+1,self.y,self.z,POWERED_COMPARATOR,1)
	def remove(self):
		setblock(self.x,self.y,self.z,AIR)

class Walls:
	def __init__(self,x,y,z,radius=5,material=STONE):
		self.x=x
		self.y=y
		self.z=z
		self.radius=radius
		self.material=material
	def emplace(self):
		for n in range(self.x-self.radius,self.x+self.radius):
			setblock_yscandown(n,self.y,self.z-self.radius,self.material)
			setblock_yscandown(n,self.y,self.z+self.radius,self.material)
		for m in range(self.z-self.radius,self.z+self.radius):
			setblock_yscandown(self.x-self.radius,self.y,m,self.material)
			setblock_yscandown(self.x+self.radius,self.y,m,self.material)
	def remove(self):
		for n in range(self.x-self.radius,self.x+self.radius):
			setblock_yscandown(n,self.y,self.z-self.radius,AIR)
			setblock_yscandown(n,self.y,self.z+self.radius,AIR)
		for m in range(self.z-self.radius,self.z+self.radius):
			setblock_yscandown(self.x-self.radius,self.y,m,AIR)
			setblock_yscandown(self.x+self.radius,self.y,m,AIR)

def explosive_trap(x,y,z,radius=5,duration=10,delay=0,strength=5):
	trapY=5
	cube(x-2,trapY-2,z-2,AIR,8)
	pd = PlayerDetector(x,trapY,z)
	pd.emplace(x,y,z,radius)
	se = SpotExploder(x+2,trapY,z)
	se.emplace(x,y,z,strength)
	setblock(x-1,trapY,z,REDSTONE_BLOCK)
	for n in range(duration * 10):
		time.sleep(.1)
		if(getblock(x-1,trapY,z) == REDSTONE_BLOCK):
			setblock(x-1,trapY,z, AIR)
		else:
			setblock(x+1,trapY,z,UNPOWERED_COMPARATOR,1)
			setblock(x-1,trapY,z,REDSTONE_BLOCK)

	pd.remove()
	se.remove()		
def proximity_grenade(x,y,z,radius=5,duration=10):
	wa = Walls(x,y+10,z,radius,TORCH)
	wa.emplace()
	yell('Proximity device out...')
	time.sleep(2)
	yell('...and armed!')
	explosive_trap(x,y,z,radius,duration)
	wa.remove()
proximity_grenade(impact_x,impact_y,impact_z)
