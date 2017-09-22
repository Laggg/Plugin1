package me.lagg.plugin1;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.EntitySkeleton;
import net.minecraft.server.v1_8_R1.MathHelper;
import net.minecraft.server.v1_8_R1.World;

/**
 * @author Ddude88888
 * this applies to all skeletons spawned by spawn egg or command, but not those from natural generation in the world
 *
 */
public class Broom1 extends EntitySkeleton {
	
	double speed;
	double climb;
	double tank;
	double power;
	
    public Broom1(World world){
        super(world);   
    }
    
    public Broom1(World world, double speed, double climb, double tank, double power) {
    	super(world);
    	this.speed = speed;
    	this.climb = climb;
    	this.tank = tank;
    	this.power = power;
    }
 
    @Override
    public void a(EntityLiving entityliving, float f){
    	for (int i = 0; i < 2; ++i){
            super.a(entityliving, f);
        }
    }
    
    public static Skeleton spawn(Location loc){
        World mcWorld = ((CraftWorld) loc.getWorld()).getHandle();
        final Broom1 customEnt = new Broom1(mcWorld);
        customEnt.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) customEnt.getBukkitEntity()).setRemoveWhenFarAway(false); //Do we want to remove it when the NPC is far away? I won
        mcWorld.addEntity(customEnt, SpawnReason.CUSTOM);
        return (Skeleton) customEnt.getBukkitEntity();
    }
    
    @Override
    public void g(float sideMot, float forMot) {
        if(this.passenger != null && this.passenger instanceof EntityHuman) {
            this.lastYaw = this.yaw = this.passenger.yaw;
            this.pitch = this.passenger.pitch * 0.5F;
            this.setYawPitch(this.yaw, this.pitch);//Update the pitch and yaw
            this.aI = this.aG = this.yaw;
            sideMot = ((EntityLiving)this.passenger).aX * 0.5F*(float)speed;
            forMot = ((EntityLiving)this.passenger).aY*(float)speed;
            if(forMot <= 0.0F) {
                forMot *= 0.25F;// Make backwards slower
            }

            Field jump = null; //Jumping
            try {
                jump = EntityLiving.class.getDeclaredField("aW");
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            } catch (SecurityException e1) {
                e1.printStackTrace();
            }
            jump.setAccessible(true);

            if (jump != null && this.onGround) {    // Wouldn't want it jumping while on the ground would we?
                try {
                    if (jump.getBoolean(this.passenger)) {
                        double jumpHeight = 0.5D;//Here you can set the jumpHeight
                        this.motY = jumpHeight;    // Used all the time in NMS for entity jumping
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            this.S = 1.0F;// The custom entity will now automatically climb up 1 high blocks
            this.aK = this.bH() * 0.1F;
            if(!this.world.isStatic) {
                this.j(0.35F);//Here is the speed the entity will walk.
                super.g(sideMot, forMot);
            }


            this.ay = this.az;//Some extra things
            double d0 = this.locX - this.lastX;
            double d1 = this.locZ - this.lastZ;
            float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
            if(f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.az += (f4 - this.az) * 0.4F;
            this.aA += this.az;
        } else {
            this.S = 0.5F;
            this.aK = 0.02F;
            super.g(sideMot, forMot);
        }


    }
    
}