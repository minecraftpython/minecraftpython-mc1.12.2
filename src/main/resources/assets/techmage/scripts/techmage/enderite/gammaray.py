#target_type<=>ray
#texture<=>flint_and_steel
#name<=>Gamma Ray
#cooldown<=>500
def gamma_ray(strength):
	startX=myX()
	startY=myY()
	startZ=myZ()
	stepX=(ray_x-startX)/100
	stepY=(ray_y-startY)/100
	stepZ=(ray_z-startZ)/100
	for n in range(110):
		x=startX+stepX*n
		y=startY+stepY*n
		z=startZ+stepZ*n
		spawnparticle(x+strength,y,z,strength,"portal")
		spawnparticle(x-strength,y,z,strength,"portal")
		spawnparticle(x,y+strength,z,strength,"portal")
		spawnparticle(x,y-strength,z,strength,"portal")
		spawnparticle(x,y,z+strength,strength,"portal")
		spawnparticle(x,y,z-strength,strength,"portal")
	time.sleep(1.5)
	for n in range(10,110):
		x=startX+stepX*n
		y=startY+stepY*n
		z=startZ+stepZ*n
		explosion(x,y,z,strength)
gamma_ray(1)