package com.crabbarition.muddyroutes.datagen;

import com.crabbarition.muddyroutes.datagen.provider.ModItemModelProvider;
import com.crabbarition.muddyroutes.registry.ModBlocks;
import com.crabbarition.muddyroutes.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class ModItemModels extends ModItemModelProvider {

    public ModItemModels(DataGenerator pDatagenerator, ExistingFileHelper pFileHelper) {
        super(pDatagenerator, pFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Muddy Routes ItemModels";
    }

    @Override
    protected void registerModels() {
        ModBlocks.createBlockItemModel(this);
        ModItems.createItemModels(this);

    }
}
