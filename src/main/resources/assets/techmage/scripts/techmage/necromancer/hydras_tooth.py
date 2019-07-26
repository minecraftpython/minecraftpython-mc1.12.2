#name<=>Hydra's Tooth
#texture<=>seeds_melon
#cooldown<=>10000
#...even the hydra only had so many mouths, OK?
#target_type<=>projectile
def smartspawn(x,y,z,entity,nbt):
	while(getblock(x,y,z) != AIR or getblock(x,y+1,z) != AIR):
		y+=1
	spawnentity(x,y,z,entity,nbt)

def hydras_tooth(x,y,z):
	import random
	for i in range(25):
		spawnparticle(x, y+random.randrange(1,3), z, 5, LAVA)
		time.sleep(.2)
	smartspawn(x,y,z,SKELETON,'{Equipment:[{id:261,tag:{ench:[{id:51,lvl:1},{id:49,lvl:2}]}},{},{},{},{}],CustomName:Hydra\'s Reaping,CustomNameVisible:1,Attributes:[{Name:generic.movementSpeed,Base:0.0000001}],SkeletonType:1}')
	
hydras_tooth(impact_x,impact_y,impact_z)