#name<=>The Price of Failure
#texture<=>rotten_flesh
#cooldown<=>10000
def smartspawn(x,y,z,entity,nbt):
	while(getblock(x,y,z) != AIR or getblock(x,y+1,z) != AIR):
		y+=1
	spawnentity(x,y,z,entity,nbt)

def the_price_of_failure(x,y,z,length=8, width=8, height=8, numberOfZombies=5, blockToEmergeFrom=DIRT, creature=ZOMBIE, giveEquipment=False,giveSpeed=True):
	import random
	possible_names = ['Arditti','Walker','Marcano','Aschenbach','Shaughnessy','Shiff','Wooten','Pinter','North','Adkins','Kilgore','Powers','Rowe']
	names = random.sample(possible_names,numberOfZombies)
	weapon_ids = ['275','272','258','267','286','283']

	possible_x = list()
	possible_y = list()
	possible_z = list()
	for n in range(x-width,x+width):
		for k in range(z-length,z+length):
			for m in range(y+height,y-height,-1):
				if(getblock(n,m,k) == blockToEmergeFrom):
					possible_x.append(n)
					possible_y.append(m)
					possible_z.append(k)
					break
	if(len(possible_x) < 1):
		yell('Your spell fizzles!  You require more '+blockToEmergeFrom+'.')
		return
	yell('These fools\' failure shall cost them twice.')
	zombieIndices = random.sample(range(len(possible_x)), numberOfZombies)
	for i in range(25):
		for index in zombieIndices:
			spawnparticle(possible_x[index], possible_y[index]+2, possible_z[index], 5, CLOUD)
		time.sleep(.2)
	else:
		nameIterator = iter(names)
		for index in zombieIndices:
			spawnparticle(possible_x[index], possible_y[index]+2, possible_z[index], 20, LAVA)
			smartspawn(possible_x[index], possible_y[index]+2, possible_z[index],creature,'{'+('Equipment:[{id:'+random.choice(weapon_ids)+'},{id:305},{id:304},{id:303},{id:302}],' if giveEquipment else '')+'CustomName:'+str(nameIterator.next())+',CustomNameVisible:1,'+('ActiveEffects:[{Id:1,Amplifier:1,Duration:999999}]}' if giveSpeed else '}'))
the_price_of_failure(myX(),myY(),myZ(),giveEquipment=True)
