package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> MOD_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MuddyRoutes.MODID);
}
