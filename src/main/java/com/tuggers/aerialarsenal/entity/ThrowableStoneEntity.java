package com.tuggers.aerialarsenal.entity;

import com.tuggers.aerialarsenal.init.ModEntities;
import com.tuggers.aerialarsenal.init.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrowableStoneEntity extends ThrowableItemProjectile {

    public ThrowableStoneEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowableStoneEntity(Level pLevel) {
        super(ModEntities.THROWABLE_STONE_ENTITY.get(), pLevel);
    }

//    private ParticleOptions getParticle() {
//        ItemStack itemStack = this.getItemRaw();
//        return (ParticleOptions) new ItemParticleOption(ParticleTypes.ITEM, itemStack);
//    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.CAMPFIRE_COSY_SMOKE : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }
    public ThrowableStoneEntity(LivingEntity pShooter, Level pLevel) {
        super(ModEntities.THROWABLE_STONE_ENTITY.get(), pShooter, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2F);

    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWABLE_STONE.get();
    }
}
