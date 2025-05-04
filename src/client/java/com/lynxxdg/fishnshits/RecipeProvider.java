package com.lynxxdg.fishnshits;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lynxxdg.fishnshits.items.ModItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.registry.tag.ItemTags;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                // Cooking
                offerSmelting(
                        List.of(ModItems.FILLET),
                        RecipeCategory.FOOD,
                        ModItems.COOKED_FILLET,
                        0.1f,
                        200,
                        "cooking_fillet"
                );

                // Crafting
                createShaped(RecipeCategory.FOOD, ModItems.SUSHI, 4)
                        .pattern("kfk")
                        .input('k', Items.DRIED_KELP)
                        .input('f', ModItems.FILLET)
                        .criterion(hasItem(ModItems.FILLET), conditionsFromItem(ModItems.FILLET))
                        .offerTo(exporter);

                // Tools
                createShaped(RecipeCategory.TOOLS, ModItems.STONE_KNIFE, 1)
                        .pattern("  i")
                        .pattern(" i ")
                        .pattern("s  ")
                        .input('i', ItemTags.STONE_TOOL_MATERIALS)
                        .input('s', Items.STICK)
                        .criterion(hasItem(Items.COBBLESTONE), conditionsFromItem(Items.COBBLESTONE))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.IRON_KNIFE, 1)
                        .pattern("  i")
                        .pattern(" i ")
                        .pattern("s  ")
                        .input('i', Items.IRON_INGOT)
                        .input('s', Items.STICK)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.GOLD_KNIFE, 1)
                        .pattern("  i")
                        .pattern(" i ")
                        .pattern("s  ")
                        .input('i', Items.GOLD_INGOT)
                        .input('s', Items.STICK)
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.DIAMOND_KNIFE, 1)
                        .pattern("  i")
                        .pattern(" i ")
                        .pattern("s  ")
                        .input('i', Items.DIAMOND)
                        .input('s', Items.STICK)
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "FishnshitsRecipeProvider";
    }
}