package com.lynxxdg.fishnshits.entities;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.world.World;

public class CustomBobberEntity extends FishingBobberEntity {

    public CustomBobberEntity(EntityType<? extends FishingBobberEntity> type, World world, int luckBonus, int waitTimeReductionTicks) {
        super(type, world, luckBonus, waitTimeReductionTicks);
    }

    public CustomBobberEntity(EntityType<? extends FishingBobberEntity> entityType, World world) {
        super(entityType, world);
    }

    public CustomBobberEntity(PlayerEntity thrower, World world, int luckBonus, int waitTimeReductionTicks, int color) {
        super(thrower, world, luckBonus, waitTimeReductionTicks);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.CUSTOM_BOBBER;
    }
}
