package com.crabbarition.muddyroutes.datagen;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.datagen.provider.ModRecipeProvider;
import com.crabbarition.muddyroutes.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

public class ModRecipes extends ModRecipeProvider {

    public ModRecipes(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.slabRecipe(ModBlocks.mossy_mud_slabs, ModBlocks.mossy_mud_bricks).save(pFinishedRecipeConsumer, loc("mossy_mud_slabs"));
        this.stairsRecipe(ModBlocks.mossy_mud_stairs, ModBlocks.mossy_mud_bricks).save(pFinishedRecipeConsumer, loc("mossy_mud_stairs"));
        this.wallRecipe(ModBlocks.mossy_mud_walls, ModBlocks.mossy_mud_bricks).save(pFinishedRecipeConsumer, loc("mossy_mud_walls"));
        ShapelessRecipeBuilder.shapeless(Items.ORANGE_DYE).requires(ModBlocks.air_plant.get()).unlockedBy("has_air_plant", has(ModBlocks.air_plant.get())).save(pFinishedRecipeConsumer, loc("orange_dye"));
        ShapedRecipeBuilder.shaped(ModBlocks.mud_mosaic.get(), 1)
                .pattern("##")
                .define('#', ModBlocks.mossy_mud_slabs.get()).unlockedBy("has_mossy_mud_slabs", has(ModBlocks.mossy_mud_slabs.get()))
                .save(pFinishedRecipeConsumer, "mud_mosaic");
        stonecutterResultFromBase(pFinishedRecipeConsumer, ModBlocks.mud_mosaic.get(), ModBlocks.mossy_mud_bricks.get(), 1);
    }


    private ResourceLocation loc(String name) {
        return new ResourceLocation(MuddyRoutes.MODID, name);
    }

}
