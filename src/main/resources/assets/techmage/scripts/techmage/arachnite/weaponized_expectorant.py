#name<=>Weaponized Expectorant
#texture<=>dye_powder_green
#cooldown<=>8888
def weaponized_expectorant(): #AKA acid spit
	spawnitem(myX(),myY(),myZ(),GOLDEN_SWORD,nbtData='{display:{Name:Ossified Secretion},ench:[{id:18,lvl:4},{id:16,lvl:1},{id:20,lvl:1}]}')
	yell('HORK...')
weaponized_expectorant()