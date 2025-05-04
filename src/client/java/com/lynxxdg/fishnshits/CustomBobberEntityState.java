package com.lynxxdg.fishnshits;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.FishingBobberEntityState;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class CustomBobberEntityState extends FishingBobberEntityState {
    public Vec3d pos = Vec3d.ZERO;
    public int color = Colors.BLACK;
}
