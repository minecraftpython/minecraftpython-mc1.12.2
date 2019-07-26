#name<=>Kill Me, I'm Here
#texture...I actually like the default one for this =)
#cooldown<=>20000
def kill_me_im_here(x,y,z):
	spawnitem(x,y,z,TNT,4)
	spawnitem(x,y,z,REDSTONE_TORCH,4)
	spawnitem(x,y,z,TRIPWIRE_HOOK,4)
	spawnitem(x,y,z,STRING,16)
	spawnitem(x,y,z,REDSTONE,16)
	spawnitem(x,y,z,LEVER,1)
	spawnitem(x,y,z,BUCKET,2)
	spawnitem(x,y,z,IRON_SHOVEL)
	spawnitem(x,y,z,PISTON)
	spawnitem(x,y,z,STICKY_PISTON)
	console('effect @p 3 10 8')	#digging haste #3 power 8 (+160% dig speed, the idea is he could dig in dirt/sand and make traps) duration 10
	console('effect @p 11 10 2') #protection #11 (20% damage taken reduction) x2 duration 10
	console('effect @p 18 10 2') #weakness #18 (15% damage dealt reduction) x2 10 sec
	yell('Kill Me, I am Here!!!: ' + str(x)+', ' + str(y) +', '+ str(z))
kill_me_im_here(myX(),myY(),myZ())
