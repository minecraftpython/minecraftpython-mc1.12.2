#name<=>Earth's Prison
#texture<=>minecart_normal
#target_type<=>ray
#cooldown<=>30000
def getSelectorString(prefix,x,y,z,radius):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'
def getPlayerSelectorString(x,y,z,radius=3):
	return getSelectorString('p',x,y,z,radius)
def getTeleportString(x,y,z):
	return str(x)+' '+str(y)+' '+str(z)

def earths_prison(x,y,z,prisonX=4,prisonY=4,prisonZ=4,duration=6):
	#This spell contains rather exploitable behavior.  See if you can find it!
	cube(prisonX-4,prisonY-4,prisonZ-4,BEDROCK,8)
	cube(prisonX-3,prisonY-3,prisonZ-3,AIR,6)
	command='tp '+ getPlayerSelectorString(x,y,z) +' ' + getTeleportString(prisonX,prisonY,prisonZ)
	print(command)
	console(command)
	safeY=y
	while(getblock(x,safeY,z) != AIR):
		safeY+=1
	time.sleep(duration)
	return_command='tp '+getPlayerSelectorString(prisonX,prisonY,prisonZ,10) +' ' + getTeleportString(x,y,z)
	print(return_command)
	console(return_command)
earths_prison(ray_x,ray_y,ray_z)