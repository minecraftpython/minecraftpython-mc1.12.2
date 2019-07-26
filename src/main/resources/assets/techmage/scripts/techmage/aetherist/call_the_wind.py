#name<=>Call the Wind
#texture<=>feather
#Very dangerous.  Need soft things to land in.  Look straight down to land.
def call_the_wind(interval=.05):
	yell('Fly!')
	while(True):
		y = .05 + (lookY() if lookY() > 0 else lookY()/2)
		propel(lookX()/15,y,lookZ()/15)
		spawnparticle(myX(),myY()-2,myZ(),10,CLOUD)
		if(lookY() < -.999):
			yell('Coming in for a landing.')
			break
		time.sleep(interval)
call_the_wind()