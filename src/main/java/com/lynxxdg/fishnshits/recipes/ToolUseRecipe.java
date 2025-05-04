package com.lynxxdg.fishnshits.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import java.util.List;

public record ToolUseRecipe(Ingredient item, Ingredient tool, ItemStack output) implements CraftingRecipe {
    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean hasTool = false;
        boolean hasItem = false;
        for (int i = 0; i < input.size(); i++) {
            if (item.test(input.getStackInSlot(i))) {
                if (hasItem)
                    return false;
                hasItem = true;
                if (hasTool)
                    return true;
            }
            if (tool.test(input.getStackInSlot(i))) {
                if (hasTool)
                    return false;
                hasTool = true;
                if (hasItem)
                    return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forShapeless(List.of(this.item, this.tool));
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public DefaultedList<ItemStack> getRecipeRemainders(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainders = CraftingRecipe.super.getRecipeRemainders(input);
        for (int i = 0; i < input.size(); i++) {
            if (tool.test(input.getStackInSlot(i))) {
                int damage = input.getStackInSlot(i).getDamage();
                ItemStack new_knife = new ItemStack(input.getStackInSlot(i).getItem());
                new_knife.setDamage(damage + 1);
                remainders.set(i, new_knife);
            }
        }
        return remainders;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return CraftingRecipe.super.getDisplays();
    }

    @Override
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return ModRecipes.TOOL_USE_RECIPE_SERIALIZER;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }

    public static class Serializer implements RecipeSerializer<ToolUseRecipe> {
        public static final MapCodec<ToolUseRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("item").forGetter(ToolUseRecipe::item),
                Ingredient.CODEC.fieldOf("tool").forGetter(ToolUseRecipe::tool),
                ItemStack.CODEC.fieldOf("result").forGetter(ToolUseRecipe::output)
        ).apply(inst, ToolUseRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ToolUseRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ToolUseRecipe::item,
                        Ingredient.PACKET_CODEC, ToolUseRecipe::tool,
                        ItemStack.PACKET_CODEC, ToolUseRecipe::output,
                        ToolUseRecipe::new);

        @Override
        public MapCodec<ToolUseRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ToolUseRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }

}
