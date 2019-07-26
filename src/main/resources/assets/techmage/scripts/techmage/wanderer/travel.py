#name<=>Travel
#target_type<=>ray
#texture<=>saddle
cube(ray_x-1,ray_y+1,ray_z-1,AIR,2)
teleport(ray_x,ray_y+1,ray_z)
time.sleep(.3)
cube(myX()-1,myY()-2,myZ()-1,AIR,2)