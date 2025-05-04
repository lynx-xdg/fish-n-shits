package com.lynxxdg.fishnshits.entities;

import com.lynxxdg.fishnshits.Fishnshits;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static EntityType<CustomBobberEntity> CUSTOM_BOBBER = register("custom_bobber", EntityType.Builder.<CustomBobberEntity>create(CustomBobberEntity::new, SpawnGroup.MISC)
            .dropsNothing()
            .disableSaving()
            .dimensions(0.25F, 0.25F)
            .maxTrackingRange(4)
            .trackingTickInterval(5));

    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> entityKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Fishnshits.MOD_ID, name));
        return Registry.register(Registries.ENTITY_TYPE, entityKey, type.build(entityKey));
    }

    public static void initialize() {

    }
}
