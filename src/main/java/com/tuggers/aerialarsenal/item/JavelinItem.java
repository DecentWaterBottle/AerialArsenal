package com.tuggers.aerialarsenal.item;

import com.tuggers.aerialarsenal.entity.JavelinEntity;
import com.tuggers.aerialarsenal.entity.KnifeEntity;
import com.tuggers.aerialarsenal.entity.ThrowableStoneEntity;
import com.tuggers.aerialarsenal.init.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class JavelinItem extends TieredItem {

    public final double damage;
    private Supplier<EntityType<JavelinEntity>> entityType;

    public JavelinItem(Supplier<EntityType<JavelinEntity>> entityType, Tier pTier, Properties pProperties, double baseAttack) {
        super(pTier, pProperties);
        damage = baseAttack + getTier().getAttackDamageBonus();
        this.entityType = entityType;
    }

    public JavelinItem(Tier pTier, Properties pProperties, double baseAttack) {
        super(pTier, pProperties);
        damage = baseAttack + getTier().getAttackDamageBonus();
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 8;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.5F);

        if (!pLevel.isClientSide) {
            JavelinEntity javelin = new JavelinEntity(entityType.get(), pPlayer, pLevel, (Tiers) getTier());
            javelin.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0f, 1.7F, 0.5F);
            pLevel.addFreshEntity(javelin);

            if (!pPlayer.isCreative()) {
                itemStack.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide);
    }
}
