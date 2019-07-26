#name<=>Gas Bomb
#texture<=>slimeball
#target_type<=>projectile
#cooldown<=>10000
def spawnParticlesInCubicArea(x,y,z,size,particle,numPerSquare=5,squaresBetween=0):
	for n in range(x-size,x+size,1+squaresBetween):
		for m in range(y-size,y+size,1+squaresBetween):
			for k in range(z-size,z+size,1+squaresBetween):
				spawnparticle(n,m,k,numPerSquare,particle)

def getSelectorString(prefix,x,y,z,radius):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+',r='+str(int(radius))+']'
def getAllSelectorString(x,y,z,radius):
	return getSelectorString('a',x,y,z,radius)
def gas_bomb(x,y,z,radius=6):
	selector=getAllSelectorString(x,y,z,radius)
	console('effect '+selector+' 15 2')
	console('effect '+selector+' 19 6')
	console('effect '+selector+' 9 4')
	spawnParticlesInCubicArea(x,y,z,radius,SLIME,3)
	spawnParticlesInCubicArea(x,y,z,radius,SMOKE,3)
gas_bomb(impact_x,impact_y,impact_z)