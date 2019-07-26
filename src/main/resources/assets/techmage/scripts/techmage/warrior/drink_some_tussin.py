#name<=>Drink Some 'Tussin
#texture<=>potion_bottle_drinkable
#cooldown<=>12000
def drink_some_tussin(duration=9,power=1):
	yell('Puttin\' some \'Tussin on it..and then comin\' for YOU!')
	console('effect @p 2 '+str(int(duration/3)))
	console('effect @p 10 '+str(int(duration))+' '+str(int(power)))
	console('effect @p 22 '+str(int(duration/3)))
drink_some_tussin()