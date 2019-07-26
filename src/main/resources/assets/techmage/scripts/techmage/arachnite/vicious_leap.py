#name<=>Vicious Leap
#texture<=>gold_boots
#cooldown<=>8888
#This one can hurt you if you jump too late after using the wand, jump twice and the second isn't to high above, or use it to descend far.
def vicious_leap(strength,duration=3):
	startY=myY()
	console('effect @p 8 '+str(int(duration))+' '+str(int(strength)))
	#only grant Strength if the jump was actually made; thank you Tyler!
	time.sleep(1.5)
	if(abs(myY()-startY) > 8):
		console('effect @p 5 '+str(int(duration))+' 1')
vicious_leap(12)