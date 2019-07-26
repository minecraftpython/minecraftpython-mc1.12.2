#name<=>Barrage
#texture<=>gunpowder
#target_type<=>ray
#cooldown<=>120000
import random
def make_beacon(x,y,z,material=IRON_BLOCK,undo=False): #very sorry Ironheart, but yes, intended.
	for n in range(3):
		for m in range(3):
			setblock(x-1+n,y-2,z-1+m,material if not undo else AIR)	
	setblock(x,y-1,z,BEACON if not undo else AIR)
	for n in range(100):
		setblock(x,y+n,z,AIR)

def barrage(x,y,z,initial_delay=10,number_of_explosives=8,delay_between_explosives=1.5,radius=10,strength=5):
	x=int(x)
	y=int(y)
	z=int(z)
	make_beacon(x,y,z)
	yell('Target coordinates identified.  Rounds inbound.  Over.')
	time.sleep(1)
	yell('T-minus '+str(initial_delay)+'.  Out.')
	time.sleep(initial_delay)
	yell('Splash.  Out.')
	for n in range(number_of_explosives):
		tntx=random.randrange(x-radius,x+radius)
		tnty=y+100
		tntz=random.randrange(z-radius,z+radius)
		#spawnentity(tntx,tnty,tntz,'tnt_primed','{Fuse:'+str(random.randrange(90,150))+'}')	#old version, for reference on how to delay TNT =)
		spawnentity(tntx,tnty,tntz,LARGE_FIREBALL,'{ExplosionPower:'+str(strength)+',direction:[0.0,-8.0,0.0]}')
		time.sleep(delay_between_explosives)
	make_beacon(x,y,z,undo=True)
barrage(ray_x,ray_y,ray_z)