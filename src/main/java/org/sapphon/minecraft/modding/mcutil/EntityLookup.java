package org.sapphon.minecraft.modding.mcutil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.*;
import net.minecraft.world.World;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class EntityLookup {

    private static Map<String, Class> entityNameToClassMap = new HashMap<String, Class>() {
        {
            put("pig", EntityPig.class);
            put("skeleton", EntitySkeleton.class);
            put("horse", EntityHorse.class);
            put("creeper", EntityCreeper.class);
            put("cow", EntityCow.class);
            put("chicken", EntityChicken.class);
            put("bat", EntityBat.class);
            put("arrow", EntityTippedArrow.class);
            put("boat", EntityBoat.class);
            put("endercrystal", EntityEnderCrystal.class);
            put("largefireball", EntityLargeFireball.class);
            put("smallfireball", EntitySmallFireball.class);
            put("witherskull", EntityWitherSkull.class);
            put("fireworkrocket", EntityFireworkRocket.class);
            put("snowball", EntitySnowball.class);
            put("egg", EntityEgg.class);
            put("xporb", EntityXPOrb.class);
            put("minecart_tnt", EntityMinecartTNT.class);
            put("blaze", EntityBlaze.class);
            put("wolf", EntityWolf.class);
            put("ghast", EntityGhast.class);
            put("spider", EntitySpider.class);
            put("witch", EntityWitch.class);
            put("iron_golem", EntityIronGolem.class);
            put("zombie", EntityZombie.class);
            put("squid", EntitySquid.class);
            put("silverfish", EntitySilverfish.class);
            put("slime", EntitySlime.class);
            put("witherboss", EntityWither.class);
            put("enderdragon", EntityDragon.class);
            put("ocelot", EntityOcelot.class);
            put("zombiepigman", EntityPigZombie.class);
            put("enderman", EntityEnderman.class);
            put("magmacube", EntityMagmaCube.class);
            put("sheep", EntitySheep.class);
            put("player", EntityPlayer.class);
        }
    };

    public static Class getPlayerClass() {
        return EntityPlayer.class;
    }

    private static Map<Class, String> classToEntityNameMap = new HashMap<Class, String>() {
        {
            for (String name : entityNameToClassMap.keySet()) {
                put(entityNameToClassMap.get(name), name);
            }
        }
    };

    public static Class getEntityClassByName(String name) {
        if (entityNameToClassMap.containsKey(name.toLowerCase())) {

            Class entityClass = entityNameToClassMap.get(name.toLowerCase());
            if (entityClass != null) {
                return entityClass;
            }
        }
        return EntityTNTPrimed.class;
    }

    public static Entity getEntityByName(String name, World worldserver) {
        // first, check our static list of entity names
        if (entityNameToClassMap.containsKey(name.toLowerCase())) {

            Class entityClass = entityNameToClassMap.get(name.toLowerCase());

            try {
                // TODO: THE BRITTLENESS IS UNBELIEVABLE
                Constructor constructor = entityClass
                        .getConstructor(World.class);
                Object entityToSpawn = constructor.newInstance(worldserver);
                if (entityToSpawn instanceof EntityXPOrb) {// TODO HAX: had to
                    // add some xpValue
                    // to an xpOrb or it
                    // defaults to zero!
                    EntityXPOrb orb = (EntityXPOrb) entityToSpawn;
                    orb.xpValue = 5;
                    entityToSpawn = orb;
                }

                return (Entity) entityToSpawn;

            } catch (Exception e) {
                JavaProblemHandler.printErrorMessageToDialogBox(e);
            }

        }
        return new EntityTNTPrimed(worldserver);
    }

    public static String getNameByEntity(Entity entity) {
        Class<? extends Entity> classOfEntity = entity.getClass();
        if (classToEntityNameMap.containsKey(classOfEntity)) {
            return classToEntityNameMap.get(entity);
        }
        return "primed_tnt";

    }
}
