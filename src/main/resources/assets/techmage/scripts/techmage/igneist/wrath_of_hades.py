#name<=>Wrath of Hades
#texture<=>bucket_lava
#cooldown<=>300000
#This spell would be unfair, if it didn't kill the user too more or less every time.
import random
class Bubble:
	def __init__(self,x,y,z,duration=5,starttime=0,substance=LAVA):
		self.x=x
		self.y=y
		self.z=z
		self.duration=duration
		self.substance=substance
		self.starttime=starttime
	
	def _setblocks(self, blocktype):
		setblock(self.x, self.y, self.z,blocktype)
		setblock(self.x+1, self.y, self.z,blocktype)
		setblock(self.x, self.y, self.z+1,blocktype)

	def emplace(self):
		self._setblocks(self.substance)
	
	def remove(self):
		self._setblocks(AIR)
	
	def update(self,tickNumber):
		if(tickNumber==self.starttime):
			self.emplace()
		if(tickNumber==self.starttime+self.duration):
			self.remove()

class Comet:
	def __init__(self,x,y,z,duration=16,starttime=0,substance=GLOWSTONE):
		self.x = x
		self.y = y
		self.z = z
		self.substance=substance
		self.starttime = starttime
		self.duration = duration
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
				explosion(self.curx,self.cury,self.curz,10)





def generate_random_bubbles(xCenter=0,yCenter=64,zCenter=0,xMin=5,xMax=20,yMin=1,yMax=4,zMin=5,zMax=20,durationMin=30,durationMax=60,timeBetweenMin=20,timeBetweenMax=70,numBubs=12):
	starttime=0
	toReturn=[]
	for i in range(numBubs):
		x=random.randrange(xMin,xMax)
		x=random.choice([x,-x])
		y=random.randrange(yMin,yMax)
		z=random.randrange(zMin,zMax)
		z=random.choice([z,-z])
		starttime += random.randrange(timeBetweenMin,timeBetweenMax)
		duration=random.randrange(durationMin,durationMax)
		toReturn.append(Bubble(xCenter+x,yCenter+y,zCenter+z,duration, starttime))
	return toReturn

def generate_random_comets(xCenter=0,yCenter=64,zCenter=0,xMin=5,xMax=20,zMin=5,zMax=20,durationMin=16,durationMax=30,timeBetweenMin=40,timeBetweenMax=70,numComets=4):
	starttime=0
	toReturn=[]
	for i in range(numComets):
		x=random.randrange(xMin,xMax)
		x=random.choice([x,-x])
		z=random.randrange(zMin,zMax)
		z=random.choice([z,-z])
		starttime += random.randrange(timeBetweenMin,timeBetweenMax)
		duration=random.randrange(durationMin,durationMax)
		toReturn.append(Comet(xCenter+x,yCenter,zCenter+z,duration, starttime))
	return toReturn

def	generate_random_fires(xCenter=0,yCenter=64,zCenter=0,xMin=-15,xMax=15,yMin=-5,yMax=5,zMin=-15,zMax=15,numFires=1000):
	for n in range(numFires):
		setblock(xCenter+random.randrange(xMin,xMax), yCenter+random.randrange(yMin,yMax), zCenter+random.randrange(zMin,zMax),FIRE)

def get_spell_duration(listOfSpellObjects):
	highestEndTime=None
	for so in listOfSpellObjects:
		if(highestEndTime == None or highestEndTime.starttime + highestEndTime.duration < so.starttime + so.duration):
			highestEndTime = so
	return highestEndTime.starttime + highestEndTime.duration

def gameloop(objects,numberOfTicks):
	for tick in range(numberOfTicks + 1):
		for so in objects:
			so.update(tick)
		time.sleep(.1)

def wrathofhades():
	generate_random_fires(myX(),myY(),myZ())
	spellobjects = generate_random_bubbles(myX(),myY(),myZ(),numBubs=12)
	spellobjects += generate_random_comets(myX(),myY(),myZ(),numComets=4)
	spell_duration = get_spell_duration(spellobjects)
	yell("An igneous blight be upon this land!")
	gameloop(spellobjects, spell_duration)
wrathofhades()