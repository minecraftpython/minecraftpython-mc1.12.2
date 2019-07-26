#name<=>Call the Fog
#cooldown<=>7500
#texture<=>dye_powder_white
#target_type<=>ray
def call_the_fog(x,y,z,length=8, width=8, height=10, material=WEB,undo=False):
	import random
	for n in range(x-width/2,x+width/2):
		for k in range(z-length/2,z+length/2):
			for m in range(y+height/2,y-height/2,-1):
				time.sleep(.001)
				if(getblock(n,m,k)!=AIR):
					if(undo and getblock(n,m-1,k)==WOOL):
						setblock(n,m-1,k,AIR)
					if(not undo and random.randint(0,4) == 0):
						setblock(n,m,k,WOOL if not undo else AIR)
						setblock(n,m+1,k,material if not undo else AIR)
					else:
						setblock(n,m,k,material if not undo else AIR)
					break
call_the_fog(ray_x,ray_y,ray_z)
time.sleep(7)
call_the_fog(ray_x,ray_y,ray_z,undo=True)