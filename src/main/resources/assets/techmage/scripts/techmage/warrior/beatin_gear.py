#name<=>Beatin' Gear
#texture<=>iron_sword
#cooldown<=>10000
def armor(x,y,z,qualityLevel):
	spawnitem(x,y,z,qualityLevel+"_helmet")
	spawnitem(x,y,z,qualityLevel+"_leggings")
	spawnitem(x,y,z,qualityLevel+"_boots")
	spawnitem(x,y,z,qualityLevel+"_chestplate")

def beatin_gear(x,y,z,qualityLevel="iron"):
	spawnitem(x,y,z,qualityLevel+"_sword")
	armor(x,y,z,qualityLevel);
	spawnentity(x,y,z,HORSE)
	spawnitem(x,y,z,qualityLevel+"_horse_armor")
	spawnitem(x,y,z,SADDLE)
	yell('Time for a beatdown.')
beatin_gear(myX(), myY()-1, myZ())
