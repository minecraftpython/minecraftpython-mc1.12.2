#name<=>Shootin' Gear
#texture<=>arrow
#cooldown<=>10000
def armor(x,y,z,qualityLevel):
	spawnitem(x,y,z,qualityLevel+"_helmet")
	spawnitem(x,y,z,qualityLevel+"_leggings")
	spawnitem(x,y,z,qualityLevel+"_boots")
	spawnitem(x,y,z,qualityLevel+"_chestplate")

def shootin_gear(x,y,z,includesAmmo=True):
	spawnitem(x,y,z,BOW)
	spawnitem(x,y,z,ARROW,64)
	spawnitem(x,y,z,ARROW,64)
	armor(x,y,z,'leather')
	spawnitem(x,y,z,COMPASS)
	spawnitem(x,y,z,APPLE,2)
	spawnitem(x,y,z,CARROT,2)
	yell('I love me some shootin!')
shootin_gear(myX(),myY(),myZ())