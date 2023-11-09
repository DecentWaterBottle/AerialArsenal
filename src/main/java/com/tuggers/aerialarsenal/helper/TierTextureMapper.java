package com.tuggers.aerialarsenal.helper;

import net.minecraft.world.item.Tiers;

import java.util.HashMap;
import java.util.Map;

public class TierTextureMapper {


    private static final Map<Tiers, String> tierToTextureMap = new HashMap<>();

    static {
        tierToTextureMap.put(Tiers.WOOD, "wooden");
        tierToTextureMap.put(Tiers.STONE, "stone");
        tierToTextureMap.put(Tiers.IRON, "iron");
        tierToTextureMap.put(Tiers.GOLD, "golden");
        tierToTextureMap.put(Tiers.DIAMOND, "diamond");
        tierToTextureMap.put(Tiers.NETHERITE, "netherite");
    }

    public static String getTextureName(Tiers tier) {
        return tierToTextureMap.get(tier);
    }
}
