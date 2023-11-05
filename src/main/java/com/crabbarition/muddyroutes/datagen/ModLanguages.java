package com.crabbarition.muddyroutes.datagen;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.registry.ModBlocks;
import com.crabbarition.muddyroutes.registry.ModEntities;
import com.crabbarition.muddyroutes.registry.ModItems;
import com.crabbarition.muddyroutes.registry.ModTabs;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class ModLanguages extends LanguageProvider {


    public ModLanguages(DataGenerator gen) {
        super(gen, MuddyRoutes.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ModBlocks.addLanguage(this);
        ModItems.createItemLanguage(this);
        ModTabs.itemGroupLanguage(this);
        ModEntities.addName(this);
    }

    public void addLang(Supplier<? extends Block> pBlock, String pName) {
        this.add(pBlock.get(), pName);
    }

    public void addEntity(Supplier<? extends EntityType<?>> pBlock, String pName) {
        this.add(pBlock.get(), pName);
    }


}
