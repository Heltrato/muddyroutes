package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.ModItemModels;
import com.crabbarition.muddyroutes.datagen.ModLanguages;
import com.crabbarition.muddyroutes.registry.properties.ModItemProperties;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MuddyRoutes.MODID);


    public static void createItemModels(ModItemModels pItemModels) {

    }

    public static void createItemLanguage(ModLanguages pLang){

    }

    private static RegistryObject<Item> registerItem(String pName, Supplier<? extends Item> pItem) {
        return MOD_ITEMS.register(pName, pItem);
    }

    private static RegistryObject<Item> registerItem(String name) {
        return registerItem(name, () -> new Item(ModItemProperties.item_properties()));
    }

    private static RegistryObject<Item> registerItem(String name, FoodProperties props) {
        return registerItem(name, () -> new Item(ModItemProperties.food_properties(props)));
    }

    public static RegistryObject<Item> registerEgg(String name, Supplier<? extends EntityType<? extends Mob>> entity, int back, int front) {
        return registerItem(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entity, back, front, new Item.Properties()));
    }


}
