#name<=>Call the Stars
#texture<=>nether_star
#target_type<=>projectile
#cooldown<=>15000
import random
class Comet:
	def __init__(self,x,y,z,duration=16,starttime=0,substance=GLOWSTONE,strength=6):
		self.x = x
		self.y = y
		self.z = z
		self.substance=substance
		self.starttime = starttime
		self.duration = duration
		self.strength = strength
		self.directionx = random.random()
		self.directionx = random.choice([self.directionx,-self.directionx]) 
		self.directionz = random.random()
		self.directionz = random.choice([self.directionz, - self.directionz])
		self.curx = self.beginx = self.x - self.directionx * random.randrange(15,46)
		self.cury = self.beginy = self.y + 80
		self.curz = self.beginz = self.z - self.directionz * random.randrange(15,46)
		self.speedx = (self.x-self.beginx)/duration
		self.speedy = (self.y-self.beginy)/duration
		self.speedz = (self.z-self.beginz)/duration
		
	def _setblocks(self,blocktype):
		setblock(self.curx,self.cury,self.curz,blocktype)

	def _spawnparticles(self):
		for n in range(4):
			spawnparticle(self.curx + random.randrange(-2,2), self.cury + random.randrange(-2,2), self.curz + random.randrange(-2,2), 1, LARGEEXPLODE)
		for m in range(20):
			spawnparticle(self.curx + random.randrange(-2,2),self.cury + random.randrange(-2,2),self.curz + random.randrange(-2,2),5,LARGE_SMOKE)

	def emplace(self):
		self._setblocks(self.substance)
		self._spawnparticles()

	def remove(self):
		self._setblocks(AIR)

	def update(self,tickNumber):
		if(tickNumber >= self.starttime and tickNumber <= self.starttime + self.duration):
			self.remove()
			if(tickNumber==self.starttime):
				self.emplace()
			elif(tickNumber<self.starttime+self.duration):
				self.curx += self.speedx
				self.cury += self.speedy
				self.curz += self.speedz
				self.emplace()
			else:
				self.emplace()
				explosion(self.curx,self.cury,self.curz,self.strength)

def cometloop(comet,numberOfTicks):
	for tick in range(numberOfTicks + 1):
		comet.update(tick)
		time.sleep(.14)

def singlecomet(x,y,z):
	yell("Extraplanetarily yours!")
	cometloop(Comet(x,y,z), 16)
singlecomet(impact_x,impact_y,impact_z)