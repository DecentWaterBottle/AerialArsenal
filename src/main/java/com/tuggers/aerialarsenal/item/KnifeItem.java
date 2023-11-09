package com.tuggers.aerialarsenal.item;

import com.tuggers.aerialarsenal.entity.KnifeEntity;
import com.tuggers.aerialarsenal.entity.ThrowableStoneEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class KnifeItem extends TieredItem {

    public final double damage;

    public KnifeItem(Tier pTier, Properties pProperties, double baseAttack) {
        super(pTier, pProperties);
        damage = baseAttack + getTier().getAttackDamageBonus();
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 64;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.5F);

        if (!pLevel.isClientSide) {
            KnifeEntity knife = new KnifeEntity(pPlayer, pLevel, (Tiers) getTier());
            knife.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0f, 1.7F, 0.5F);
            pLevel.addFreshEntity(knife);

            if (!pPlayer.isCreative()) {
                itemStack.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide);
    }
}
