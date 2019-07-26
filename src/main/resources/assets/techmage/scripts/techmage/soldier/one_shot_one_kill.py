#name<=>One Shot, One Kill
#texture<=>bow_pulling_2
#cooldown<=>10000
def getMotionString(mx,my,mz):
	return 'Motion:['+str(mx)+','+str(my)+','+str(mz)+']'

def one_shot_one_kill(x,y,z,trajectory_flatness=5):
	yell('Neutralizing hostile!')
	spawnentity(x+lookX()*2,y+lookY()*2,z+lookZ()*2,ARROW,'{player:true,damage:50,'+getMotionString(lookX()*trajectory_flatness,lookY()*trajectory_flatness,lookZ()*trajectory_flatness)+'}')
one_shot_one_kill(myX(),myY(),myZ())