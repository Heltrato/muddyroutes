package com.crabbarition.muddyroutes.entity.client;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.entity.Shrimp;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShrimpModel extends AnimatedGeoModel<Shrimp> {
    @Override
    public ResourceLocation getModelResource(Shrimp entity) {
        return MuddyRoutes.geo("shrimp");
    }

    @Override
    public ResourceLocation getTextureResource(Shrimp entity) {
        return MuddyRoutes.texEntity("shrimp");
    }

    @Override
    public ResourceLocation getAnimationResource(Shrimp entity) {
        return MuddyRoutes.animEntity("shrimp");
    }
}
