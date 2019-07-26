#name<=>Vereor Nox
#texture<=>clock
def vereor_nox(x,y,z,length=8, width=8, height=8, redstoneAlso=True, fireAlso=False):
	#Darkness, I fear myself
	yell('Vereor Nox!')
	for i in range(x-width,x+width):
		for j in range(y-height, y+height):
			for k in range(z-height, z+height):
				if(getblock(i,j,k)==TORCH or getblock(i,j,k)==GLOWSTONE or getblock(i,j,k)==BEACON or (redstoneAlso==True and (getblock(i,j,k) == REDSTONE_TORCH or getblock(i,j,k) == REDSTONE_LAMP)) or (fireAlso==True and getblock(i,j,k) == FIRE)):
					setblock(i,j,k,AIR)
	console('time set 16000')
vereor_nox(myX(),myY(),myZ())