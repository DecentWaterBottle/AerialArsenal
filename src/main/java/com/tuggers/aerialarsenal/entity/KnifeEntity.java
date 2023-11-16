package com.tuggers.aerialarsenal.entity;

import com.tuggers.aerialarsenal.init.ModEntities;
import com.tuggers.aerialarsenal.init.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

public class KnifeEntity extends AbstractArrow {
    public Tiers entityTier;
    private float clientSideRotation = 0;


    public KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    protected KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public KnifeEntity(EntityType<KnifeEntity> entity, LivingEntity pShooter, Level pLevel) {
        super(entity, pShooter, pLevel);
    }

    public KnifeEntity(EntityType<KnifeEntity> entity, LivingEntity pShooter, Level pLevel, Tiers tier) {
        super(entity, pShooter, pLevel);
        this.entityTier = tier;
        this.setBaseDamage(2 + tier.getAttackDamageBonus());
    }

    public KnifeEntity(EntityType<KnifeEntity> entity, Level world, Tiers tier) {
        super(entity, world);
        this.entityTier = tier;
        this.setBaseDamage(2 + tier.getAttackDamageBonus());
    }

    public float getRotationAnimation(float partialTicks) {
        if(!this.inGround) {
            clientSideRotation = (this.tickCount + partialTicks) * 50;
        }
        return this.clientSideRotation;
    }


    @Override
    protected ItemStack getPickupItem() {
        System.out.println("TIER: " + entityTier);
        switch(entityTier){
            case WOOD:
                return new ItemStack(ModItems.WOODEN_KNIFE.get());
            case STONE:
                return new ItemStack(ModItems.STONE_KNIFE.get());
        }

        return null;
    }

    @Override
    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public boolean isFoil() {
        return false;
    }
}
