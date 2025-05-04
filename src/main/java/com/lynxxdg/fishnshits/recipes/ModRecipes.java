package com.lynxxdg.fishnshits.recipes;

import com.lynxxdg.fishnshits.Fishnshits;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<ToolUseRecipe> TOOL_USE_RECIPE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Fishnshits.MOD_ID, "tool_use"),
            new ToolUseRecipe.Serializer());

    public static void initialize() {}
}
