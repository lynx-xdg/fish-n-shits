package com.lynxxdg.fishnshits.items;

import com.lynxxdg.fishnshits.Fishnshits;
import com.lynxxdg.fishnshits.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Fishnshits.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    // Knives
    public static final Item STONE_KNIFE = register("stone_knife", Item::new, new Item.Settings().sword(
            ToolMaterial.STONE,
            1.0f,
            1.0f
    ));
    public static final Item IRON_KNIFE = register("iron_knife", Item::new, new Item.Settings().sword(
            ToolMaterial.IRON,
            1.0f,
            1.0f
    ));
    public static final Item GOLD_KNIFE = register("gold_knife", Item::new, new Item.Settings().sword(
            ToolMaterial.GOLD,
            1.0f,
            1.0f
    ));
    public static final Item DIAMOND_KNIFE = register("diamond_knife", Item::new, new Item.Settings().sword(
            ToolMaterial.DIAMOND,
            1.0f,
            1.0f
    ));

    public static final Item CUSTOM_ROD = register("custom_rod", CustomRod::new, new Item.Settings());


    // Foodstuff
    public static final Item FILLET = register("fillet", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.3f)
            .build()));
    public static final Item COOKED_FILLET = register("cooked_fillet", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.5f)
            .build()));
    public static final Item SUSHI = register("sushi", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.4f)
            .build()));

    // Freshwater Fish
    public static final Item TROUT = register("trout", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build()));
    public static final Item BASS = register("bass", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build()));
    public static final Item CARP = register("carp", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build()));
    public static final Item BLUEGILL = register("bluegill", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build()));

    // Saltwater Fish
    public static final Item TUNA = register("tuna", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.5f)
            .build()));
    public static final Item MACKEREL = register("mackerel", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build()));
    public static final Item SNAPPER = register("snapper", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build()));
    public static final Item FLOUNDER = register("flounder", Item::new, new Item.Settings().food(new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .build()));

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Fishnshits.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.TROUT))
            .displayName(Text.translatable("itemGroup.fish-n-shits"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        // Register items to the custom item group.
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.STONE_KNIFE);
            itemGroup.add(ModItems.IRON_KNIFE);
            itemGroup.add(ModItems.GOLD_KNIFE);
            itemGroup.add(ModItems.DIAMOND_KNIFE);

            itemGroup.add(ModItems.CUSTOM_ROD);

            itemGroup.add(ModItems.FILLET);
            itemGroup.add(ModItems.COOKED_FILLET);
            itemGroup.add(ModItems.SUSHI);

            itemGroup.add(ModItems.TROUT);
            itemGroup.add(ModItems.BASS);
            itemGroup.add(ModItems.CARP);
            itemGroup.add(ModItems.BLUEGILL);

            itemGroup.add(ModItems.TUNA);
            itemGroup.add(ModItems.MACKEREL);
            itemGroup.add(ModItems.SNAPPER);
            itemGroup.add(ModItems.FLOUNDER);

            itemGroup.add(ModBlocks.TACKLEBOX.asItem());
        });
    }
}
