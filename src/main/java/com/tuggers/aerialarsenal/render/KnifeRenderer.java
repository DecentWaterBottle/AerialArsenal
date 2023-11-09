package com.tuggers.aerialarsenal.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.entity.KnifeEntity;
import com.tuggers.aerialarsenal.helper.TierTextureMapper;
import com.tuggers.aerialarsenal.render.model.KnifeModel;
import com.tuggers.aerialarsenal.render.model.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class KnifeRenderer extends EntityRenderer<KnifeEntity> {

    private final KnifeModel model;

    public KnifeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new KnifeModel(pContext.bakeLayer(ModModelLayers.KNIFE_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(KnifeEntity pEntity) {
        ResourceLocation texture = new ResourceLocation(AerialArsenal.MODID, "textures/item/" + TierTextureMapper.getTextureName(pEntity.entityTier) + "_knife_3d.png");
        return texture;
    }

    public void render(KnifeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        float degrees = pEntity.getRotationAnimation(pPartialTicks);

        pMatrixStack.pushPose();
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) + 90));
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) + degrees));

//        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));
//        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot()) + 90.0F));

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(pEntity)), false, pEntity.isFoil());
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
        super.render( pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);


    }
}
