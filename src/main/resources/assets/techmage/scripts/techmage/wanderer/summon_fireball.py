#name<=>Summon Fireball
#texture<=>fireball
#Student-inspired!
def summon_fireball(strength=1,direction='[0.0,0.0,0.0]'):
	yMod=1 #if we don't modify myY() it appears uncomfortably high relative to Steve
	if(lookY() < -.999): #Wanderer-specific upgrade to make Glide useful: can fire stronger, faster fireballs, but only straight down and at a minimum range of 10
		direction='[0.0,-10.0,0.0]'
		strength += 1
		yMod=10
	spawnentity(myX()+lookX()*1.5,myY()-yMod+lookY()*1.5,myZ()+lookZ()*1.5,LARGE_FIREBALL,'{ExplosionPower:'+str(strength)+',direction:'+direction+'}')#for some reason if you don't specify a direction, the entity is instantly removed!
summon_fireball(2)