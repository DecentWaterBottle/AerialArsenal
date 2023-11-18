package com.tuggers.aerialarsenal.entity;

import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.helper.TierTextureMapper;
import com.tuggers.aerialarsenal.init.ModEntities;
import com.tuggers.aerialarsenal.init.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumMap;
import java.util.Map;

public class KnifeEntity extends AbstractArrow {
    public Tiers entityTier;
    private float clientSideRotation = 0;


    public KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    protected KnifeEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, Tiers tier) {
        super(pEntityType, pX, pY, pZ, pLevel);
        this.entityTier = tier;
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
        String itemName = TierTextureMapper.getTextureName(entityTier) + "_knife";
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(AerialArsenal.MODID, itemName));
        if (item != null) {
            return new ItemStack(item);
        }
        return null;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
//        ItemEntity droppedItem = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.STONE_KNIFE.get()));
//        this.level().addFreshEntity(droppedItem);
//        LivingEntity shooter = this.getOwner() instanceof LivingEntity ? (LivingEntity) this.getOwner() : null;

        super.onHitEntity(pResult);

        if (this.pickup == AbstractArrow.Pickup.ALLOWED && !this.level().isClientSide) {
                KnifeEntity knifeEntity = switch (entityTier) {
                    case WOOD ->                new KnifeEntity(ModEntities.WOODEN_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case STONE ->               new KnifeEntity(ModEntities.STONE_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case IRON ->               new KnifeEntity(ModEntities.IRON_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    default ->                  new KnifeEntity(ModEntities.STONE_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                };
                knifeEntity.pickup = Pickup.ALLOWED;

                Vec3 motion = knifeEntity.getDeltaMovement();
                double angle = Math.atan2(-motion.x, motion.z);
                double degrees = Math.toDegrees(angle);

                knifeEntity.setYRot((float)degrees + 90.0F);
                this.level().addFreshEntity(knifeEntity);
                this.discard();
            }
    }


    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public boolean isFoil() {
        return false;
    }
}
