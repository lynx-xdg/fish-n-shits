package com.lynxxdg.fishnshits;

import com.lynxxdg.fishnshits.entities.ModEntities;
import com.lynxxdg.fishnshits.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FishnshitsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(ModEntities.CUSTOM_BOBBER, CustomBobberEntityRenderer::new);
		HandledScreens.register(ModScreenHandlers.TACKLEBOX_SCREEN_HANDLER, TackleboxScreen::new);
	}
}