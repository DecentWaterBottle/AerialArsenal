package com.tuggers.aerialarsenal.entity;

import com.tuggers.aerialarsenal.init.ModEntities;
import com.tuggers.aerialarsenal.init.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class KnifeEntity extends AbstractArrow {
    public Tiers entityTier;
    private float clientSideRotation = 0;


    public KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        System.out.println("NON TIERED CONSTRUCTOR");
        System.out.println("TIER: " + entityTier);

    }

    @Override
    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }

    protected KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public KnifeEntity(LivingEntity pShooter, Level pLevel) {
        super(ModEntities.KNIFE_ENTITY.get(), pShooter, pLevel);
    }

    public KnifeEntity(LivingEntity pShooter, Level pLevel, Tiers tier) {
        super(ModEntities.KNIFE_ENTITY.get(), pShooter, pLevel);
        this.entityTier = tier;
        System.out.println("TIERED CONSTRUCTOR RUN");
        System.out.println("TIER: " + entityTier);
    }

    public KnifeEntity(Level world, Tiers wood) {
        super(ModEntities.KNIFE_ENTITY.get(), world);
        this.entityTier = wood;
    }

    public float getRotationAnimation(float partialTicks) {
        if(!this.inGround) {
            clientSideRotation = (this.tickCount + partialTicks) * 50;
        }
        return this.clientSideRotation;
    }



    @Override
    protected ItemStack getPickupItem() {
        System.out.println("ENTITY TIER: " + entityTier);
        switch(entityTier){
            case WOOD:
                return new ItemStack(ModItems.WOODEN_KNIFE.get());
        }

        return null;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public boolean isFoil() {
        return false;
    }
}
