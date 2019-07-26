#name<=>Glide
#texture<=>feather
def breakFallWeb(downScanDistance):
	if(getblock(myX(),myY()-downScanDistance,myZ()) != AIR) or (getblock(myX()-1,myY()-downScanDistance,myZ()) != AIR) or (getblock(myX()+1 ,myY()-downScanDistance,myZ()) != AIR) or (getblock(myX(),myY()-downScanDistance,myZ()-1) != AIR) or (getblock(myX(),myY()-downScanDistance,myZ()+1) != AIR):
		landingCubeX=myX()-2
		landingCubeY=myY()-(downScanDistance-1)
		landingCubeZ=myZ()-2
		cube(landingCubeX,landingCubeY,landingCubeZ,COBWEB,4)
		time.sleep(.6)
		cube(landingCubeX,landingCubeY,landingCubeZ,AIR,4)
		return True
	return False
def Glide(duration=100,speedFactor=8,upSpeedConstant=.14):
	for n in range(duration):
		upSpeed = lookY() / speedFactor
		if(upSpeed < 0):
			upSpeed /= 2
		propel(lookX()/speedFactor,upSpeedConstant+upSpeed,lookZ()/speedFactor)
		time.sleep(.1)
	fallBroken=False
	while(not fallBroken):
		if(breakFallWeb(8)):
			fallBroken=True
		time.sleep(.03)
Glide()