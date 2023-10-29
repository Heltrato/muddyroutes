package com.crabbarition.muddyroutes.datagen.provider;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ModBlockLootProvider extends BlockLoot {

    public <T extends Block> void dropSelf(Supplier<T> block) {
        dropSelf(block.get());
    }

    public void dropTable(Supplier<Block> block, Function<Block, LootTable.Builder> table) {
        add(block.get(), table.apply(block.get()));
    }

    public void dropSlab(Supplier<SlabBlock> block) {
        super.add(block.get(), BlockLoot::createSlabItemTable);
    }


    public void dropPot(RegistryObject<FlowerPotBlock> flowerpot) {
        this.add(flowerpot.get(), (pot) -> createPotFlowerItemTable(((FlowerPotBlock) pot).getContent()));
    }

}
