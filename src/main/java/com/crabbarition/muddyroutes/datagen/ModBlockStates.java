package com.crabbarition.muddyroutes.datagen;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.provider.ModBlockStateProvider;
import com.crabbarition.muddyroutes.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class ModBlockStates extends ModBlockStateProvider {

    public ModBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MuddyRoutes.MODID, exFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Muddy Routes Block States and Models";
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.createBlockModel(this);
    }
}
