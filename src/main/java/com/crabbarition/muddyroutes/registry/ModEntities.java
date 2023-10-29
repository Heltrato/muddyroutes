package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.ModLootTables;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> MOD_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MuddyRoutes.MODID);


    public static void addLoots(ModLootTables.AddEntityLoots pEntity){

    }

}
