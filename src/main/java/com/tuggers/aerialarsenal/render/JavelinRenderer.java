package com.tuggers.aerialarsenal.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.entity.JavelinEntity;
import com.tuggers.aerialarsenal.entity.KnifeEntity;
import com.tuggers.aerialarsenal.helper.TierTextureMapper;
import com.tuggers.aerialarsenal.render.model.JavelinModel;
import com.tuggers.aerialarsenal.render.model.KnifeModel;
import com.tuggers.aerialarsenal.render.model.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JavelinRenderer extends EntityRenderer<JavelinEntity> {

    private final JavelinModel model;

    public JavelinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new JavelinModel(pContext.bakeLayer(ModModelLayers.JAVELIN_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(JavelinEntity pEntity) {
        ResourceLocation texture = new ResourceLocation(AerialArsenal.MODID, "textures/item/" + TierTextureMapper.getTextureName(pEntity.entityTier) + "_javelin_3d.png");
        return texture;
    }

    public void render(JavelinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        float degrees = pEntity.getRotationAnimation(pPartialTicks);

        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot()) + 90.0F));


        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(pEntity)), false, pEntity.isFoil());
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render( pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);


    }
}
