package com.crabbarition.muddyroutes.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class ModBlockStateProvider extends BlockStateProvider {

    final ModBlockModelProvider blockModels;

    public ModBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);

        this.blockModels = new ModBlockModelProvider(gen, exFileHelper) {
            @Override
            protected void registerModels() {

            }

            @Override
            public @NotNull String getName() {
                return ModBlockStateProvider.this.getName();
            }
        };

    }

    @Override
    public ModBlockModelProvider models() {
        return this.blockModels;
    }

    protected ResourceLocation tLoc(String name) {
        return modLoc("block/" + name);
    }

    protected String blockName(RegistryObject<? extends Block> block) {
        return block.getId().getPath();
    }

    public void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void columnBlock(RegistryObject<Block> block) {
        String basename = blockName(block);
        simpleBlock(block.get(), models().cubeColumn(basename, tLoc(basename), tLoc(basename + "_top")));
    }

    public void sidedBlock(RegistryObject<Block> block, String top, String bottom, String north, String south, String east, String west) {
        simpleBlock(block.get(), models().cube(blockName(block), tLoc(bottom), tLoc(top), tLoc(north), tLoc(south), tLoc(east), tLoc(west)).texture("particle", tLoc(north)));
    }

    public void basicBlockRotated(Supplier<Block> block) {
        simpleBlock(block.get(), model -> ConfiguredModel.allYRotations(model, 0, false));
    }

    public void basicBlockLayered(RegistryObject<Block> block, String bottom, String top) {
        simpleBlock(block.get(), models().basicLayered(block, tLoc(bottom), tLoc(top)));
    }

    public void axisLogBlock(RegistryObject<RotatedPillarBlock> block) {
        axisBlock(block.get(), tLoc(block.getId().getPath()));
    }


    public void strippedLogBlock(Supplier<RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLoc("stripped_" + name));
    }

    public void strippedWoodBlock(RegistryObject<RotatedPillarBlock> block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLoc("stripped_" + name + "_log_side"), tLoc("stripped_" + name + "_log_side"));
        axisBlock(block.get(), model, model);
    }

    public void woodBlock(RegistryObject<RotatedPillarBlock> block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLoc(name + "_side"), tLoc(name + "_side"));
        axisBlock(block.get(), model, model);
    }

    public void stairsBlock(RegistryObject<StairBlock> block, String name) {
        stairsBlock(block.get(), tLoc(name));
    }

    public void wallBlock(RegistryObject<WallBlock> block, String name) {

        super.wallBlock(block.get(), tLoc(name));
    }


    public void stairsBlockLayered(RegistryObject<StairBlock> block, String inner, String outer) {
        ModelFile stairs = models().stairsBasicLayer(block, tLoc(inner), tLoc(outer));
        ModelFile stairsInner = models().stairsInnerBasicLayer(block, tLoc(inner), tLoc(outer));
        ModelFile stairsOuter = models().stairsOuterBasicLayer(block, tLoc(inner), tLoc(outer));
        stairsBlock(block.get(), stairs, stairsInner, stairsOuter);
    }

    public void slabBlock(RegistryObject<SlabBlock> block, RegistryObject<Block> doubleBlock) {
        slabBlock(block.get(), tLoc(blockName(doubleBlock)), tLoc(blockName(doubleBlock)));
    }

    public void crossBlock(RegistryObject<? extends Block> block, String type) {
        crossBlock(block, models().cross(blockName(block), tLoc(blockName(block))).renderType(type));
    }

    public void crossBlockTinted(RegistryObject<Block> block) {
        crossBlock(block, models().tintedCross(blockName(block), tLoc(blockName(block))));
    }

    public void pottedPlantBlock(RegistryObject<FlowerPotBlock> block) {
        simpleBlock(block.get(), models().flowerPot(block).renderType("cutout"));
    }

    public void orientableBlockLit(RegistryObject<Block> block) {
        ModelFile off = models().orientable(blockName(block), tLoc(blockName(block) + "_side"), tLoc(blockName(block) + "_front"), tLoc(blockName(block) + "_top"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLoc(blockName(block) + "_side"), tLoc(blockName(block) + "_front_lit"), tLoc(blockName(block) + "_top"));
        orientableBlock(block, off, on);
    }

    public void orientableBlockBasicLit(RegistryObject<Block> block) {
        ModelFile off = models().orientable(blockName(block), tLoc(blockName(block) + "_side"), tLoc(blockName(block) + "_front"), tLoc(blockName(block) + "_side"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLoc(blockName(block) + "_side"), tLoc(blockName(block) + "_front_lit"), tLoc(blockName(block) + "_side"));
        orientableBlock(block, off, on);
    }


    private void orientableBlock(RegistryObject<Block> block, ModelFile off, ModelFile on) {
        getVariantBuilder(block.get()).forAllStates(state -> {
            ModelFile model = state.getValue(BlockStateProperties.LIT) ? on : off;

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                    .build();
        });
    }

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(model)
                        .build());
    }

    private void grassBlock(Supplier<Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
    }


}
