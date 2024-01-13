package com.tuggers.aerialarsenal.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;

public class JavelinItem extends TieredItem {
    public JavelinItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 8;
    }

}
