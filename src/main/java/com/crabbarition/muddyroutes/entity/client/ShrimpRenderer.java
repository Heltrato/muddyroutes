package com.crabbarition.muddyroutes.entity.client;

import com.crabbarition.muddyroutes.entity.Shrimp;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShrimpRenderer extends GeoEntityRenderer<Shrimp> {
    public ShrimpRenderer(EntityRendererProvider.Context renderManage) {
        super(renderManage, new ShrimpModel());
    }

    @Override
    public RenderType getRenderType(Shrimp animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

}
