package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.ModLanguages;
import com.crabbarition.muddyroutes.datagen.ModLootTables;
import com.crabbarition.muddyroutes.entity.Shrimp;
import com.crabbarition.muddyroutes.entity.client.ShrimpRenderer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> MOD_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MuddyRoutes.MODID);
    final static int TRACKING_RANGE = 80;
    final static int UPDATE_INTERVAL = 3;
    final static boolean DOES_RECEIVE_UPDATES = true;

    public static final RegistryObject<EntityType<Shrimp>> SHRIMP = register("shrimp", Shrimp::new, MobCategory.WATER_CREATURE, 0.8f, 0.5f, false);


    public static void addName(ModLanguages arg) {
        arg.addEntity(SHRIMP, "Shrimp");
    }


    public static void addLoots(ModLootTables.AddEntityLoots pEntity) {
        pEntity.addTable(SHRIMP, pEntity.singleDropTableWithSmeltFeature(() -> Items.SALMON, 0, 1));
    }

    public static void addEntitySpawnPlacement(SpawnPlacementRegisterEvent placementRegisterEvent) {
        var ground = SpawnPlacements.Type.ON_GROUND;
        var water = SpawnPlacements.Type.IN_WATER;
        var lava = SpawnPlacements.Type.IN_LAVA;

        placementRegisterEvent.register(SHRIMP.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Shrimp::checkShrimpSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }


    public static void mobAttribute(EntityAttributeCreationEvent pEvent) {
        attributes(pEvent, SHRIMP, Shrimp.shrimp());
    }


    @OnlyIn(Dist.CLIENT)
    public static void render(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SHRIMP.get(), ShrimpRenderer::new);

    }


    private static <E extends Entity> RegistryObject<EntityType<E>> register(String id, EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height) {
        return register(id, factory, classification, width, height, false);
    }


    private static <E extends Entity> RegistryObject<EntityType<E>> register(String id, EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, boolean fireproof) {
        return build(id, makeBuilder(factory, classification, width, height), fireproof);
    }


    public static <E extends Entity> RegistryObject<EntityType<E>> build(String id, EntityType.Builder<E> builder, boolean fireproof) {
        if (fireproof) {
            builder.fireImmune();
        }
        RegistryObject<EntityType<E>> ret = MOD_ENTITIES.register(id, () -> builder.build(id.toString()));
        return ret;
    }


    public static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height) {
        return makeBuilder(factory, classification, width, height, TRACKING_RANGE, UPDATE_INTERVAL);
    }

    public static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> factory, MobCategory classification, float width, float height, int range, int interval) {
        return EntityType.Builder.of(factory, classification).
                sized(width, height).
                setTrackingRange(range).
                setUpdateInterval(interval).
                setShouldReceiveVelocityUpdates(true);
    }

    //Attribute Maker
    public static <E extends LivingEntity> void attributes(EntityAttributeCreationEvent event, Supplier<EntityType<E>> entitytype, AttributeSupplier.Builder builder) {
        var entity = entitytype.get();
        var build = builder.build();
        event.put(entity, build);
    }


}
