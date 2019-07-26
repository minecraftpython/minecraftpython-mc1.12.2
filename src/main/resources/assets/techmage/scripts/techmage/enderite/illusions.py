#name<=>Illusions
#texture<=>ender_eye
#cooldown<=>5000
def illusions(x,y,z,radius,numberOfIllusions):
	y-=radius
	import random
	for n in range(numberOfIllusions):
		xSpawn=random.choice(range(x-radius,x+radius))
		yOrig=y
		ySpawn=y
		zSpawn=random.choice(range(z-radius,z+radius))
		while(getblock(xSpawn,ySpawn,zSpawn) != AIR and ySpawn-yOrig < radius*2):
			ySpawn+=1
		spawnentity(xSpawn, ySpawn, zSpawn, ENDERMAN, '{CustomName:Illusion,CustomNameVisible:1,Attributes:[{Name:generic.maxHealth,Base:.5},{Name:generic.attackDamage,Base:.5}],Silent:1}')
illusions(myX(),myY(),myZ(),10,5)