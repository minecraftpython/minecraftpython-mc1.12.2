#name<=>Sandtraps
#cooldown<=>12000
#This wand demonstrates a client-side "raycast" without the use of Java (ie the "target_type<=>ray" metadata tag currently)

def raycast_distance(x,y,z,dirx,diry,dirz,acceptableBlocks=[AIR,WATER,GLASS,ICE],distance=300):
	for n in range(distance):
		if(getblock(x+dirx*n,y+diry*n,z+dirz*n) not in acceptableBlocks):
			return n

#this one is eventually meant to show inheritance, with Wall being a single-layer thing extended by HighWall, but I need to do some research
class Walls:
	def __init__(self,x,y,z,radius=5,material=STONE):
		self.x=x
		self.y=y
		self.z=z
		self.radius=radius
		self.material=material
	def emplace(self):
		for n in range(self.x-self.radius,self.x+self.radius):
			setblock(n,self.y,self.z-self.radius,self.material)
			setblock(n,self.y,self.z+self.radius,self.material)
		for m in range(self.z-self.radius,self.z+self.radius+1):	
			setblock(self.x-self.radius,self.y,m,self.material)
			setblock(self.x+self.radius,self.y,m,self.material)

	def remove(self):
		for n in range(self.x-self.radius,self.x+self.radius):
			setblock(n,self.y,self.z-self.radius,AIR)
			setblock(n,self.y,self.z+self.radius,AIR)
		for m in range(self.z-self.radius,self.z+self.radius):
			setblock(self.x-self.radius,self.y,m,AIR)
			setblock(self.x+self.radius,self.y,m,AIR)


def sandtraps(x,y,z,radius=6,height=8,duration=5):
	for i in range(radius):
		for n in range(height):
			walls = Walls(x,y+50-n,z,radius-i,SAND)
			walls.emplace()
		time.sleep(.7)
	time.sleep(duration)
	cube(x-radius,y,z-radius,AIR,radius*2+1)

def sandtraps_raycast(x,y,z,lx,ly,lz):
	n = raycast_distance(x,y,z,lx,ly,lz)
	sandtraps(x+lx*n,y+ly*n,z+lz*n)
sandtraps_raycast(myX(),myY(),myZ(),lookX(),lookY(),lookZ())