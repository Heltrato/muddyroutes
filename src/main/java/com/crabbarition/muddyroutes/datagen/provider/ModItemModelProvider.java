package com.crabbarition.muddyroutes.datagen.provider;

import com.crabbarition.muddyroutes.MuddyRoutes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator pDatagenerator, ExistingFileHelper pFileHelper) {
        super(pDatagenerator, MuddyRoutes.MODID, pFileHelper);
    }

    public String blockName(RegistryObject<? extends Block> block) {
        return block.getId().getPath();
    }

    public ItemModelBuilder blockItem(RegistryObject<? extends Block> block) {
        return blockItem(block, blockName(block));
    }

    public ItemModelBuilder wallBlockInventory(RegistryObject<? extends Block> block, RegistryObject<? extends  Block> block2) {
        return withExistingParent(blockName(block), mcLoc("block/wall_inventory")).texture("wall", modLoc("block/" + block2.getId().getPath()));
    }

    public ItemModelBuilder blockItem(RegistryObject<? extends Block> block, String model) {
        return withExistingParent(blockName(block), modLoc("block/" + model));
    }

    public ItemModelBuilder blockItemTexture(RegistryObject<? extends Block> block) {
        return blockItemTexture(block, blockName(block));
    }

    public ItemModelBuilder blockItemTexture(RegistryObject<? extends Block> block, String texture) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + texture));
    }

    public ItemModelBuilder basicItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    public ItemModelBuilder heldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    public ItemModelBuilder eggItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }


}
