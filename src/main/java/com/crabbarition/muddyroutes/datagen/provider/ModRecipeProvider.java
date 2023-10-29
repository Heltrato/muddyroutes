package com.crabbarition.muddyroutes.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public abstract class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    public ShapedRecipeBuilder slabRecipe(RegistryObject<SlabBlock> result, RegistryObject<Block> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<StairBlock> result, RegistryObject<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder wallRecipe(Supplier<WallBlock> result, RegistryObject<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', ingredient.get()).unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }



}
