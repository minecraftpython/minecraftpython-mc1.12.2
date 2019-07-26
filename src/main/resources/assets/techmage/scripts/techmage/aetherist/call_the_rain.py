#name<=>Call the Rain
#texture<=>stick
def call_the_rain(x=0,y=0,z=0):
	for i in range(6):
		for j in range(6):
			for k in range(6):
				spawnparticle(x+i-3,y+j-3,z+k-3,5,SPLASH)
	console('toggledownfall')
call_the_rain(myX(),myY(),myZ())