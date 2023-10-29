package com.crabbarition.muddyroutes.datagen;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.provider.ModBlockLootProvider;
import com.crabbarition.muddyroutes.datagen.provider.ModEntityLootProvider;
import com.crabbarition.muddyroutes.registry.ModBlocks;
import com.crabbarition.muddyroutes.registry.ModEntities;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTables extends LootTableProvider {

    public ModLootTables(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    public String getName() {
        return "Muddy Routes Loot Tables";
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(AddBlockLoots::new, LootContextParamSets.BLOCK), Pair.of(AddEntityLoots::new, LootContextParamSets.ENTITY));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationresults) {
    }

    public static class AddBlockLoots extends ModBlockLootProvider {
        @Override
        protected void addTables() {
            ModBlocks.createBlockLootTable(this);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            //TODO Add Block Registry
            return ModBlocks.MOD_BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    public static class AddEntityLoots extends ModEntityLootProvider {
        @Override
        protected void addTables() {
            ModEntities.addLoots(this);
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ForgeRegistries.ENTITY_TYPES.getValues().stream()
                    .filter(entity -> MuddyRoutes.MODID.equals(ForgeRegistries.ENTITY_TYPES.getKey(entity).getNamespace()))
                    .collect(Collectors.toList());
        }
    }




}
