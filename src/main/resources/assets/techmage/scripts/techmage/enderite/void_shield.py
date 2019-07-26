#name<=>Void Shield
#cooldown<=>18000
def void_shield(x,y,z,radius=5,duration=8):
	for m in range(y-radius,y+radius):
		for n in range(x-radius,x+radius):
			for k in [z-radius,z+radius]:
				setblock(n,m,k,END_PORTAL if m % 2 == 0 and n % 2 == 0 else GLASS)
	for m in range(y-radius,y+radius):
		for n in range(z-radius,z+radius):
			for k in [x-radius,x+radius]:
				setblock(k,m,n,END_PORTAL if m % 2 == 0 and n % 2 == 0 else GLASS)
	time.sleep(duration)
	for m in range(y-radius,y+radius):
		for n in range(x-radius,x+radius):
			for k in [z-radius,z+radius]:
				setblock(n,m,k,AIR)
	for m in range(y-radius,y+radius):
		for n in range(z-radius,z+radius):
			for k in [x-radius,x+radius]:
				setblock(k,m,n,AIR)
void_shield(myX(),myY(),myZ())