package com.tuggers.aerialarsenal.entity;

import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.helper.TierTextureMapper;
import com.tuggers.aerialarsenal.init.ModEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class JavelinEntity extends AbstractArrow {
    public Tiers entityTier;
    private float clientSideRotation = 0;


    public JavelinEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    protected JavelinEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, Tiers tier) {
        super(pEntityType, pX, pY, pZ, pLevel);
        this.entityTier = tier;
    }

    public JavelinEntity(EntityType<JavelinEntity> entity, LivingEntity pShooter, Level pLevel) {
        super(entity, pShooter, pLevel);
    }

    public JavelinEntity(EntityType<JavelinEntity> entity, LivingEntity pShooter, Level pLevel, Tiers tier) {
        super(entity, pShooter, pLevel);
        this.entityTier = tier;
        this.setBaseDamage(4 + tier.getAttackDamageBonus());
    }

    public JavelinEntity(EntityType<JavelinEntity> entity, Level world, Tiers tier) {
        super(entity, world);
        this.entityTier = tier;
        this.setBaseDamage(4 + tier.getAttackDamageBonus());
    }

    public float getRotationAnimation(float partialTicks) {
        if(!this.inGround) {
            clientSideRotation = (this.tickCount + partialTicks) * 50;
        }
        return this.clientSideRotation;
    }

    @Override
    protected ItemStack getPickupItem() {
        String itemName = TierTextureMapper.getTextureName(entityTier) + "_javelin";
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

        if (this.pickup == Pickup.ALLOWED && !this.level().isClientSide) {
                JavelinEntity knifeEntity = switch (entityTier) {
                    case WOOD ->                new JavelinEntity(ModEntities.WOODEN_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case STONE ->               new JavelinEntity(ModEntities.STONE_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case IRON ->               new JavelinEntity(ModEntities.IRON_JAVELIN_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case GOLD ->               new JavelinEntity(ModEntities.GOLDEN_JAVELIN_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case DIAMOND ->               new JavelinEntity(ModEntities.DIAMOND_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
                    case NETHERITE ->               new JavelinEntity(ModEntities.NETHERITE_KNIFE_ENTITY.get(), this.getX(), this.getY(), this.getZ(), this.level(), entityTier);
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
