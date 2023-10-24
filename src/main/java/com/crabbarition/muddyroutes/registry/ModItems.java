package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MuddyRoutes.MODID);


    private RegistryObject<Item> registerItem(String pName, Supplier<? extends Item> pItem) {
        return MOD_ITEMS.register(pName, pItem);
    }

}
