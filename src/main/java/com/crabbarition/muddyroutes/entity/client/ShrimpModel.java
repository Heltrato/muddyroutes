package com.crabbarition.muddyroutes.entity.client;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.entity.Shrimp;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShrimpModel extends AnimatedGeoModel<Shrimp> {
    @Override
    public ResourceLocation getModelResource(Shrimp entity) {
        return new ResourceLocation(MuddyRoutes.MODID, "geo/shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Shrimp entity) {
        return new ResourceLocation(MuddyRoutes.MODID, "textures/entity/shrimp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Shrimp entity) {
        return new ResourceLocation(MuddyRoutes.MODID, "animations/animation.shrimp.json");
    }
}
