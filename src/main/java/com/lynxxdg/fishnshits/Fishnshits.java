package com.lynxxdg.fishnshits;

import com.lynxxdg.fishnshits.blocks.entity.ModBlockEntities;
import com.lynxxdg.fishnshits.blocks.ModBlocks;
import com.lynxxdg.fishnshits.entities.ModEntities;
import com.lynxxdg.fishnshits.items.ModItems;
import com.lynxxdg.fishnshits.recipes.ModRecipes;
import com.lynxxdg.fishnshits.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fishnshits implements ModInitializer {
	public static final String MOD_ID = "fish-n-shits";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlockEntities.initialize();
		ModScreenHandlers.initialize();
		ModBlocks.initialize();
		ModItems.initialize();
		ModRecipes.initialize();
		ModLoot.register();
		ModComponents.initialize();
		ModEntities.initialize();
	}
}