package me.lagg.plugin1;

import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.EntitySkeleton;
import net.minecraft.server.v1_8_R1.World;

public class CustomEntitySkeleton extends EntitySkeleton {
	 
    public CustomEntitySkeleton(World world){
        super(world);
    }
 
    @Override
    public void a(EntityLiving entityliving, float f){
        System.out.println("SOMETHING'S HAPPENING, WOOOOOO!");
    	for (int i = 0; i < 2; ++i){
            super.a(entityliving, f);
        }
    }
    
}