package com.crabbarition.muddyroutes.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public class ModFoods {

    public static final FoodProperties SHRIMP = createFood(2, 0.1F);
    public static final FoodProperties COOKED_SHRIMP = createFood(6, 0.8F);


    private static FoodProperties createFood(int hunger, float saturation) {
        return createFood(hunger, saturation, false, null, 0);
    }

    private static FoodProperties createFood(int hunger, float saturation, boolean alwaysEdible, Supplier<MobEffectInstance> effect, float duration) {
        FoodProperties.Builder food = new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation);

        if (alwaysEdible)
            food.alwaysEat();
        if (effect != null)
            food.effect(effect, duration);

        return food.build();
    }

}
