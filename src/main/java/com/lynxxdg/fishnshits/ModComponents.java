package com.lynxxdg.fishnshits;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    protected static void initialize() {
    }

    public static final ComponentType<Integer> BOBBER_COLOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Fishnshits.MOD_ID, "bobber_color"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
}
