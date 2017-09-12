package me.lagg.plugin1;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.EntityType;

import net.minecraft.server.v1_8_R1.BiomeBase;
import net.minecraft.server.v1_8_R1.BiomeMeta;
import net.minecraft.server.v1_8_R1.EntityBat;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntitySkeleton;
import net.minecraft.server.v1_8_R1.EntityTypes;

/**
 * @author Ddude88888
 *
 */
public enum CustomEntityType {
	 
    SKELETON("Skeleton", 51, EntityType.SKELETON, EntitySkeleton.class, CustomEntitySkeleton.class),
    BROOM("Skeleton", 51, EntityType.SKELETON, EntitySkeleton.class, Broom.class);
    
    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;
 
    private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }
 
    public String getName(){
        return this.name;
    }
 
    public int getID(){
        return this.id;
    }
 
    public EntityType getEntityType(){
        return this.entityType;
    }
 
    public Class<? extends EntityInsentient> getNMSClass(){
        return this.nmsClass;
    }
 
    public Class<? extends EntityInsentient> getCustomClass(){
        return this.customClass;
    }
 
}