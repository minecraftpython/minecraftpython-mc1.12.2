#name<=>Tidepools
#texture<=>bucket_water
#cooldown<=>12000
#The spell is so-called because of how it manifests when used near a coast.
def getSelectorString(prefix,x,y,z,radius):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'
def getAllSelectorString(x,y,z,radius):
	return getSelectorString('a',x,y,z,radius)
class Tidepool():
	def __init__(self,x,y,z,radius,liquid,material,healing_power=1):
		self.x=x
		self.y=y
		self.z=z
		self.liquid=liquid
		self.material=material
		self.healing_power=healing_power
		self.radius=radius
	def emplace(self):
		cube(self.x-self.radius,self.y-1,self.z-self.radius,self.material,self.radius*2)
		cube(self.x-self.radius+1,self.y,self.z-self.radius+1,self.liquid,self.radius*2-2)
		cube(self.x-self.radius,self.y+1,self.z-self.radius,AIR,self.radius*2)
	def remove(self):
		cube(self.x-self.radius,self.y-3,self.z-self.radius,AIR,self.radius*2)
	def tick(self):
		for n in range(self.x-self.radius,self.x+self.radius):
			for m in range(self.y,self.y+5):
				for k in range(self.z-self.radius,self.z+self.radius):
					spawnparticle(n,m,k,3,SPLASH)
		console('effect ' + getAllSelectorString(self.x,self.y,self.z,self.radius) + ' 10 1 '+str(int(self.healing_power)))
def tidepools(x,y,z,radius=10,numberOfPools=3,poolRadius=2,duration=10,strength=1):
	import random
	pools = list()
	possible_x = list()
	possible_y = list()
	possible_z = list()
	for n in range(x-radius,x+radius):
		for k in range(z-radius,z+radius):
			for m in range(y+radius,y-radius,-1):
				if(getblock(n,m,k) == WATER):
					possible_x.append(n)
					possible_y.append(m)
					possible_z.append(k)
					break
	if(len(possible_x) < numberOfPools):
		yell('Your spell fizzles!  You require more water.')
	else:
		poolIndices = random.sample(range(len(possible_x)), numberOfPools)
		for index in poolIndices:
			pools.append(Tidepool(possible_x[index], possible_y[index], possible_z[index], poolRadius, WATER, SANDSTONE, healing_power=strength))
		for i in range(duration):
			for pool in pools:
				if(i==0):
					pool.emplace()
				pool.tick()
			time.sleep(1)
tidepools(myX(),myY(),myZ(),16,strength=2)