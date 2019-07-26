#name<=>Throwing Knife
#texture<=>feather
#cooldown<=>3500
def getSelectorString(prefix,x,y,z,radius=0):
	return '@'+prefix+'[x='+str(int(x))+',y='+str(int(y))+',z='+str(int(z))+(',r='+str(int(radius)) if radius != 0 else '')+']'
def getPlayerSelectorString(x,y,z,radius):
	return getSelectorString('p',x,y,z,radius)

def throwing_knife(knifingPlayer,spellRange=4):
	x=knifingPlayer.x+knifingPlayer.lookx*spellRange
	y=knifingPlayer.y-1+knifingPlayer.looky*spellRange
	z=knifingPlayer.z+knifingPlayer.lookz*spellRange
	selector = getPlayerSelectorString(x,y,z,2)
	console('effect ' + selector + ' 7')
	spawnparticle(x,y,z,20,CRIT)
	spawnparticle(x,y,z,20,RED_DUST)
throwing_knife(player)