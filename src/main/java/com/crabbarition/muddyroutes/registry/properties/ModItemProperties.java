package com.crabbarition.muddyroutes.registry.properties;

import com.crabbarition.muddyroutes.registry.ModTabs;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ModItemProperties {


    public static Item.Properties basic_properties() {
        return new Item.Properties().tab(ModTabs.muddy_routes_tabs);
    }

    public static Item.Properties item_properties() {
        return basic_properties();
    }

    public static Item.Properties food_properties(FoodProperties food) {
        return basic_properties().food(food);
    }

}
