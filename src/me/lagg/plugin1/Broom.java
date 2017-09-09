package me.lagg.plugin1;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R1.EntityEndermite;
import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.MathHelper;
import net.minecraft.server.v1_8_R1.World;

public class Broom extends EntityEndermite {

	public Broom(World world) {
		super(world);
	}
	
	 @Override
	 public void g(float sideMot, float forMot) {
		 System.out.println("STUFF'S HAPPENING");
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
