#name<=>Special Dispensation
#cooldown<=>10000
#texture<=>gold_ingot
import random
def teleport_yscan(x,y,z,forceTheIssue=True,scanDistance=10):
	yOrig=y
	while(getblock(x,y,z) != AIR or (forceTheIssue == False and y-yOrig>scanDistance)):
		y+=1
	teleport(x,y,z)

def make_random_thing_string(slot,number=1,idMin=256,idMax=432):
	randId = random.randint(idMin,idMax)
	return '{id:'+str(randId)+',Count:'+str(int(number))+',Slot:'+str(int(slot))+'}'

def special_dispensation(x,y,z,numItems=5,numBlocks=5,duration=8):
	yell('I know I must have brought SOMETHING useful...')
	nbtData = '{Items:['
	slot=0
	for n in range(numItems):
		if(slot != 0):
			nbtData += ','
		nbtData += make_random_thing_string(slot)
		slot += 1

	for m in range(numBlocks):
		if(slot != 0):
			nbtData += ','
		nbtData += make_random_thing_string(slot,1,0,197)
		slot += 1

	nbtData += ']}'
	setblock(x,y,z,CHEST,tileEntityNbtData=nbtData)

distance=8
x=myX()+lookX()*distance
y=myY()+lookY()*distance
z=myZ()+lookZ()*distance
teleport_yscan(x,y,z)
special_dispensation(x+1,y,z)
