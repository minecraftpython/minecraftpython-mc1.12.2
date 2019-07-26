#name<=>Porks Pexlosion
#texture<=>porkchop_cooked
def porks_pexlosion(explosionStrength=2,numberOfPigs=1,delay=2,distance=8,ignoresYAxis=False):
	yell('Ahhh; fresh meat.')
	for i in range(numberOfPigs):
		x=player.x+lookX()*distance
		y=player.y if ignoresYAxis else player.y+lookY()*distance
		z=player.z+lookZ()*distance
		spawnentity(x,y,z,PIG,'{Attributes:[{Name:generic.maxHealth,Base:.5}]}')
		time.sleep(delay)
		explosion(x,y,z,explosionStrength)
porks_pexlosion()