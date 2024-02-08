package com.tuggers.aerialarsenal.render.model;

import com.tuggers.aerialarsenal.AerialArsenal;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation KNIFE_LAYER = new ModelLayerLocation(
            new ResourceLocation(AerialArsenal.MODID, "knife_layer"), "main");

    public static final ModelLayerLocation JAVELIN_LAYER = new ModelLayerLocation(
            new ResourceLocation(AerialArsenal.MODID, "javelin_layer"), "main");
}
