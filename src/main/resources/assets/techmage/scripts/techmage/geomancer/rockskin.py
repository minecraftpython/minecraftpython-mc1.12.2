#name<=>Rockskin
#texture<=>fireworks_charge
#cooldown<=>12000
def rockskin(x,y,z,duration=6):
	cube (x-2, y-3, z-2, BEDROCK, 4)
	cube (x-1, y-2, z-1, AIR, 2)
	setblock(x-1,y-1,z-1,TORCH)
	setblock(x-2, y, z-2, AIR)
	setblock(x+1, y, z+1, AIR)
	setblock(x-2, y, z+1, AIR)
	setblock(x+1, y, z-2, AIR)
	teleport(x,y-1,z)
	console('effect @p 11 '+str(int(duration))+' 4')	#(4*20)=80% damage resistance (#11) during the spell!
	time.sleep(duration)
	cube (x-2, y-3, z-2, AIR, 4)	#undo
rockskin(myX(),myY(),myZ())