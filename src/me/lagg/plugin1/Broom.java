package me.lagg.plugin1;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_8_R1.EntityBat;
import net.minecraft.server.v1_8_R1.EntityEndermite;
import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.EntitySkeleton;
import net.minecraft.server.v1_8_R1.MathHelper;
import net.minecraft.server.v1_8_R1.World;

/**
 * @author Ddude88888
 *
 */
public class Broom {

	EntityInsentient entity;
	Class<? extends EntityInsentient> broomType;
	double speed; //horizontal speed
	double climb; //vertical speed
	double tank; //reduced knockback
	double power; //knockback
	
	public Broom(Class<? extends EntityInsentient> c, double speed, double climb, double tank, double power) {
		this.broomType = c;
		this.speed = speed;
		this.climb = climb;
		this.tank = tank;
		this.power = power;
		
	}
}
