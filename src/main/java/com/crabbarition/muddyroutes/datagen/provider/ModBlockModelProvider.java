package com.crabbarition.muddyroutes.datagen.provider;

import com.crabbarition.muddyroutes.MuddyRoutes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModBlockModelProvider extends BlockModelProvider {


    public ModBlockModelProvider(DataGenerator pDatagen, ExistingFileHelper pFilehelper) {
        super(pDatagen, MuddyRoutes.MODID, pFilehelper);
    }


    public BlockModelBuilder basicLayered(RegistryObject<Block> block, ResourceLocation bottom, ResourceLocation top) {
        return withExistingParent(block.getId().getPath(), modLoc("block/util/cube_all_2_layer"))
                .texture("all", bottom)
                .texture("all2", top);
    }

    public BlockModelBuilder stairsBasicLayer(RegistryObject<? extends Block> block, ResourceLocation inner, ResourceLocation outer) {
        return stairsLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsLayer(RegistryObject<? extends Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getId().getPath(), modLoc("block/util/stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder stairsInnerBasicLayer(RegistryObject<? extends Block> block, ResourceLocation inner, ResourceLocation outer) {
        return stairsInnerLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsInnerLayer(RegistryObject<? extends Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getId().getPath() + "_inner", modLoc("block/util/inner_stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder stairsOuterBasicLayer(RegistryObject<? extends Block> block, ResourceLocation inner, ResourceLocation outer) {
        return stairsOuterLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsOuterLayer(RegistryObject<? extends Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getId().getPath() + "_outer", modLoc("block/util/outer_stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder tintedCross(String block, ResourceLocation texture) {
        return withExistingParent(block, mcLoc("block/tinted_cross"))
                .texture("cross", texture);
    }

    public BlockModelBuilder flowerPot(RegistryObject<FlowerPotBlock> plant) {
        return withExistingParent("potted_" + plant.getId().getPath(), mcLoc("block/flower_pot_cross"))
                .texture("plant", "block/" + ForgeRegistries.BLOCKS.getKey(plant.get().getContent()).getPath());
    }


}
