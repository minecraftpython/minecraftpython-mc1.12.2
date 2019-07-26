#name<=>Ironheart
#texture<=>iron_ingot
#A memory teleporter.  Look down to place an iron block up to 5 blocks beneath you.  Look up to randomly teleport to a point above an iron block that is itself within 25 blocks of you currently.
def teleport_yscan(x,y,z,forceTheIssue=True,scanDistance=10):
	yOrig=y
	while(getblock(x,y,z) != AIR or (forceTheIssue == False and y-yOrig>scanDistance)):
		y+=1
	teleport(x,y,z)


def ironheart(x,y,z,radius):
	import random
	if(lookY() > 0):
		possible_x = list()
		possible_y = list()
		possible_z = list()
		for n in range(x-radius,x+radius):
			for k in range(z-radius,z+radius):
				for m in range(y+radius,y-radius,-1):
					if(getblock(n,m,k) == IRON_BLOCK):
						possible_x.append(n)
						possible_y.append(m)
						possible_z.append(k)
						break
		if(len(possible_x) > 0):
				randomIndex = random.randint(0,len(possible_x))
				if(randomIndex >= len(possible_x)):
					randomIndex = len(possible_x) - 1
				yell('Ironheart!')
				teleport_yscan(possible_x[randomIndex],possible_y[randomIndex],possible_z[randomIndex])
	else:
		setblock(x,y+lookY()*5,z,IRON_BLOCK)

ironheart(myX(),myY(),myZ(),30)