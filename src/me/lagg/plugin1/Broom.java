package me.lagg.plugin1;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_8_R1.EntityBat;
import net.minecraft.server.v1_8_R1.EntityEndermite;
import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.MathHelper;
import net.minecraft.server.v1_8_R1.World;

/**
 * @author Ddude88888
 *
 */
public class Broom extends EntityBat {

	public Broom(World world) {
		super(world);
	}
	
    public static Bat spawn(Location loc){
        World mcWorld = ((CraftWorld) loc.getWorld()).getHandle();
        final Broom customEnt = new Broom(mcWorld);
        customEnt.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) customEnt.getBukkitEntity()).setRemoveWhenFarAway(false); //Do we want to remove it when the NPC is far away? I won
        mcWorld.addEntity(customEnt, SpawnReason.CUSTOM);
        return (Bat) customEnt.getBukkitEntity();
    }
    
	
    @Override
    public void g(float sideMot, float forMot) {
        if(this.passenger != null && this.passenger instanceof EntityHuman) {
            this.lastYaw = this.yaw = this.passenger.yaw;
            this.pitch = this.passenger.pitch * 0.5F;
            this.setYawPitch(this.yaw, this.pitch);//Update the pitch and yaw
            this.aI = this.aG = this.yaw;
            sideMot = ((EntityLiving)this.passenger).aX * 0.5F;
            forMot = ((EntityLiving)this.passenger).aY;
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

            if (jump != null /*&& this.onGround*/) {    // Wouldn't want it jumping while on the ground would we?
                try {
                    if (jump.getBoolean(this.passenger)) {
                        double jumpHeight = 0.5D;//Here you can set the jumpHeight
                        this.motY = jumpHeight;    // Used all the time in NMS for entity jumping
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            //this.S = 1.0F;// The custom entity will now automatically climb up 1 high blocks
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
