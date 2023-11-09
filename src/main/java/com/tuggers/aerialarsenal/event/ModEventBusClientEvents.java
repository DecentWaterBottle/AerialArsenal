package com.tuggers.aerialarsenal.event;

import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.render.model.KnifeModel;
import com.tuggers.aerialarsenal.render.model.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AerialArsenal.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusClientEvents {


    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.KNIFE_LAYER, KnifeModel::createBodyLayer);

    }
}
