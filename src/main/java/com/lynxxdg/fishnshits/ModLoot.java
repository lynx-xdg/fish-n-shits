package com.lynxxdg.fishnshits;

import com.lynxxdg.fishnshits.items.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.BiomeKeys;

public class ModLoot {
    // CONDITIONS
    public static LootCondition.Builder RIVERS_AND_LAKES;
    public static LootCondition.Builder SWAMPS;
    public static LootCondition.Builder SANDY;
    public static LootCondition.Builder OCEAN;
    public static LootCondition.Builder CORAL;
    public static LootCondition.Builder COLD;
    public static LootCondition.Builder WARM;

    public static void register() {

        LootTableEvents.REPLACE.register((id, tableBuilder, source, lookup) -> {
            OCEAN = LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(
                    lookup.getEntryOrThrow(BiomeKeys.WARM_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.LUKEWARM_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.DEEP_LUKEWARM_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.DEEP_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.COLD_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.DEEP_COLD_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.DEEP_FROZEN_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.STONY_SHORE),
                    lookup.getEntryOrThrow(BiomeKeys.BEACH)
            )));
            RIVERS_AND_LAKES = LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(
                    lookup.getEntryOrThrow(BiomeKeys.RIVER),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_RIVER),
                    lookup.getEntryOrThrow(BiomeKeys.SWAMP),
                    lookup.getEntryOrThrow(BiomeKeys.MANGROVE_SWAMP),
                    lookup.getEntryOrThrow(BiomeKeys.PLAINS),
                    lookup.getEntryOrThrow(BiomeKeys.SUNFLOWER_PLAINS),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_PLAINS),
                    lookup.getEntryOrThrow(BiomeKeys.ICE_SPIKES),
                    lookup.getEntryOrThrow(BiomeKeys.BADLANDS),
                    lookup.getEntryOrThrow(BiomeKeys.WOODED_BADLANDS),
                    lookup.getEntryOrThrow(BiomeKeys.ERODED_BADLANDS),
                    lookup.getEntryOrThrow(BiomeKeys.BAMBOO_JUNGLE),
                    lookup.getEntryOrThrow(BiomeKeys.SPARSE_JUNGLE),
                    lookup.getEntryOrThrow(BiomeKeys.JUNGLE),
                    lookup.getEntryOrThrow(BiomeKeys.FOREST),
                    lookup.getEntryOrThrow(BiomeKeys.FLOWER_FOREST),
                    lookup.getEntryOrThrow(BiomeKeys.BIRCH_FOREST),
                    lookup.getEntryOrThrow(BiomeKeys.DARK_FOREST),
                    lookup.getEntryOrThrow(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
                    lookup.getEntryOrThrow(BiomeKeys.OLD_GROWTH_PINE_TAIGA),
                    lookup.getEntryOrThrow(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                    lookup.getEntryOrThrow(BiomeKeys.TAIGA),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_TAIGA),
                    lookup.getEntryOrThrow(BiomeKeys.SAVANNA),
                    lookup.getEntryOrThrow(BiomeKeys.SAVANNA_PLATEAU),
                    lookup.getEntryOrThrow(BiomeKeys.WINDSWEPT_HILLS),
                    lookup.getEntryOrThrow(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS),
                    lookup.getEntryOrThrow(BiomeKeys.WINDSWEPT_FOREST),
                    lookup.getEntryOrThrow(BiomeKeys.WINDSWEPT_SAVANNA),
                    lookup.getEntryOrThrow(BiomeKeys.MEADOW),
                    lookup.getEntryOrThrow(BiomeKeys.CHERRY_GROVE),
                    lookup.getEntryOrThrow(BiomeKeys.GROVE),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_SLOPES),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_PEAKS),
                    lookup.getEntryOrThrow(BiomeKeys.JAGGED_PEAKS),
                    lookup.getEntryOrThrow(BiomeKeys.MUSHROOM_FIELDS)
            )));
            SWAMPS = LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(
                    lookup.getEntryOrThrow(BiomeKeys.SWAMP),
                    lookup.getEntryOrThrow(BiomeKeys.MANGROVE_SWAMP)
            )));
            SANDY = LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(
                    lookup.getEntryOrThrow(BiomeKeys.BEACH),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_BEACH),
                    lookup.getEntryOrThrow(BiomeKeys.DESERT),
                    lookup.getEntryOrThrow(BiomeKeys.BADLANDS),
                    lookup.getEntryOrThrow(BiomeKeys.ERODED_BADLANDS),
                    lookup.getEntryOrThrow(BiomeKeys.WOODED_BADLANDS)
            )));
            CORAL = LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(
                    lookup.getEntryOrThrow(BiomeKeys.WARM_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.LUKEWARM_OCEAN)
            )));
            COLD = LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(
                    lookup.getEntryOrThrow(BiomeKeys.COLD_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.DEEP_COLD_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.DEEP_FROZEN_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_RIVER),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_OCEAN),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_PLAINS),
                    lookup.getEntryOrThrow(BiomeKeys.ICE_SPIKES),
                    lookup.getEntryOrThrow(BiomeKeys.GROVE),
                    lookup.getEntryOrThrow(BiomeKeys.FROZEN_PEAKS),
                    lookup.getEntryOrThrow(BiomeKeys.JAGGED_PEAKS),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_SLOPES),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_TAIGA),
                    lookup.getEntryOrThrow(BiomeKeys.SNOWY_BEACH)
            )));
            WARM = COLD.invert();

            if (source.isBuiltin() && LootTables.FISHING_FISH_GAMEPLAY.equals(id)) {
                LootPool.Builder tb = LootPool.builder()
                        // BLUEGILL
                        .with(ItemEntry.builder(ModItems.BLUEGILL)
                                .conditionally(RIVERS_AND_LAKES)
                                .weight(30)
                        )
                        // CARP
                        .with(ItemEntry.builder(ModItems.CARP)
                                .conditionally(RIVERS_AND_LAKES.or(SWAMPS))
                                .weight(25)
                        )
                        // BASS
                        .with(ItemEntry.builder(ModItems.BASS)
                                .conditionally(RIVERS_AND_LAKES)
                                .weight(15)
                        )
                        // TROUT
                        .with(ItemEntry.builder(ModItems.TROUT)
                                .conditionally(COLD.and(RIVERS_AND_LAKES))
                                .weight(15)
                        )
                        // FLOUNDER
                        .with(ItemEntry.builder(ModItems.FLOUNDER)
                                .conditionally(OCEAN.or(SANDY))
                                .weight(10)
                        )
                        // MACKEREL
                        .with(ItemEntry.builder(ModItems.MACKEREL)
                                .conditionally(OCEAN)
                                .weight(25)
                        )
                        // SNAPPER
                        .with(ItemEntry.builder(ModItems.SNAPPER)
                                .conditionally(WARM.and(CORAL.or(OCEAN)))
                                .weight(5)
                        )
                        // TUNA
                        .with(ItemEntry.builder(ModItems.TUNA)
                                .conditionally(OCEAN)
                                .weight(3)
                        )
                        // COD
                        .with(ItemEntry.builder(Items.COD)
                                .conditionally(OCEAN)
                                .weight(30)
                        )
                        // SALMON
                        .with(ItemEntry.builder(Items.SALMON)
                                .conditionally(RIVERS_AND_LAKES.or(COLD.and(OCEAN)))
                                .weight(20)
                        )
                        // TROPICAL FISH
                        .with(ItemEntry.builder(Items.TROPICAL_FISH)
                                .conditionally(WARM.and(CORAL))
                                .weight(5)
                        )
                        // PUFFERFISH
                        .with(ItemEntry.builder(Items.PUFFERFISH)
                                .conditionally(WARM.and(CORAL))
                                .weight(5))
                        .rolls(ConstantLootNumberProvider.create(1.0f));

                return LootTable.builder().pool(tb).build();
            }
            return null;
        });
    }
}
