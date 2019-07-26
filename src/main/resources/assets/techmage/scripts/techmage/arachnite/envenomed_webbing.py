#name<=>Envenomed Webbing
#texture<=>string
#cooldown<=>8888
def envenomed_webbing(x,y,z,radius=2,power=1,duration=4,fatal=False):
	effectid='19' #poison
	if fatal:
		effectid='20'	#wither
	cube(x-radius,y-radius,z-radius,WEB,radius*2)
	for second_long_tick in range(duration):
		console('effect @a['+str(int(x))+','+str(int(y))+','+str(int(z))+','+str(int(radius))+'] ' + effectid + ' 1 ' + str(int(power)))
		time.sleep(1)
	console('effect @a['+str(int(x))+','+str(int(y))+','+str(int(z))+','+str(int(radius))+'] ' + effectid + ' 1 ' + str(int(power)))
	cube(x-radius,y-radius,z-radius,AIR,radius*2)
envenomed_webbing(player.x+player.lookx*10,player.y+player.looky*10,player.z+player.lookz*10)