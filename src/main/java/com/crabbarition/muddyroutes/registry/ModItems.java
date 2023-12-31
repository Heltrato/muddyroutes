package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.ModItemModels;
import com.crabbarition.muddyroutes.datagen.ModLanguages;
import com.crabbarition.muddyroutes.registry.properties.ModItemProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MuddyRoutes.MODID);

    public static final RegistryObject<Item> SHRIMP_SPAWN_EGG = registerEgg("shrimp_spawn_egg", ModEntities.SHRIMP, 0x6A2514, 0xFF10FF);

    public static final RegistryObject<Item> SHRIMP = registerItem("raw_shrimp", ModFoods.SHRIMP);
    public static final RegistryObject<Item> COOKED_SHRIMP = registerItem("cooked_shrimp", ModFoods.COOKED_SHRIMP);

    public static final RegistryObject<Item> SHRIMP_BUCKET = registerItem("shrimp_bucket", () -> new MobBucketItem(ModEntities.SHRIMP.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(ModTabs.muddy_routes_tabs)));


    public static void createItemModels(ModItemModels pItemModels) {
        pItemModels.eggItem(SHRIMP_SPAWN_EGG);
        pItemModels.basicItem(SHRIMP);
        pItemModels.basicItem(COOKED_SHRIMP);
        pItemModels.basicItem(SHRIMP_BUCKET);

    }

    public static void createItemLanguage(ModLanguages pLang) {
        pLang.add(SHRIMP_SPAWN_EGG.get(), "Shrimp Spawn Egg");
        pLang.add(SHRIMP.get(), "Raw Shrimp");
        pLang.add(COOKED_SHRIMP.get(), "Cooked Shrimp");
        pLang.add(SHRIMP_BUCKET.get(), "Bucket of Shrimp");

    }

    private static RegistryObject<Item> registerItem(String pName, Supplier<? extends Item> pItem) {
        return MOD_ITEMS.register(pName, pItem);
    }

    private static RegistryObject<Item> registerItem(String name) {
        return registerItem(name, () -> new Item(ModItemProperties.item_properties().tab(ModTabs.muddy_routes_tabs)));
    }

    private static RegistryObject<Item> registerItem(String name, FoodProperties props) {
        return registerItem(name, () -> new Item(ModItemProperties.food_properties(props)));
    }

    public static RegistryObject<Item> registerEgg(String name, Supplier<? extends EntityType<? extends Mob>> entity, int back, int front) {
        return registerItem(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entity, back, front, new Item.Properties().tab(ModTabs.muddy_routes_tabs)));
    }


}
