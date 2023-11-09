package com.tuggers.aerialarsenal.entity;

import com.tuggers.aerialarsenal.init.ModEntities;
import com.tuggers.aerialarsenal.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ThrowableStoneEntity extends ThrowableItemProjectile {

    public ThrowableStoneEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowableStoneEntity(Level pLevel) {
        super(ModEntities.THROWABLE_STONE_ENTITY.get(), pLevel);
    }


    public ThrowableStoneEntity(LivingEntity pShooter, Level pLevel) {
        super(ModEntities.THROWABLE_STONE_ENTITY.get(), pShooter, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWABLE_STONE.get();
    }
}
