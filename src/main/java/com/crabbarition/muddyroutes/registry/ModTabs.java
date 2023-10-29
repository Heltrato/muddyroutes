package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.datagen.ModLanguages;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModTabs {



    public static final CreativeModeTab muddy_routes_tabs = new CreativeModeTab("muddy_routes_tabs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.mossy_mud_bricks.get().asItem());
        }
    };

    public static void itemGroupLanguage(ModLanguages pLang){
        pLang.add(itemGroupBase("muddy_routes_tabs"), "Muddy Routes Tabs");
    }

    private static String itemGroupBase(String string){
        return "itemGroup." + string;
    }

}
