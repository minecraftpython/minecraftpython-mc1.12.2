#target_type<=>ray
#name<=>Snowballarrage
import random

def getMotionString(mx,my,mz):
    return 'Motion:['+str(mx)+','+str(my)+','+str(mz)+']'

def snowballarrage(x,y,z,numSnowballs=30):
    for n in range(numSnowballs):
        startX = x + random.randrange(-20,20)
        startY = y + random.randrange(5,15) 
        startZ = z + random.randrange(-20,20)
        spawnentity(startX,startY,startZ,SNOWBALL,'{'+getMotionString((x-startX)*.1, (y-startY)*.1, (z-startZ)*.1)+'}')
        time.sleep(random.random()/8)
snowballarrage(ray_x, ray_y, ray_z)