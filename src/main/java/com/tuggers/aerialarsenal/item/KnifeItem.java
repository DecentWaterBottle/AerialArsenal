package com.tuggers.aerialarsenal.item;

import com.tuggers.aerialarsenal.entity.ThrowableStoneEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;

public class KnifeItem extends TieredItem {

    public final double damage;

    public KnifeItem(Tier pTier, Properties pProperties, double baseAttack) {
        super(pTier, pProperties);
        damage = baseAttack + getTier().getAttackDamageBonus();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.5F);

        if (!pLevel.isClientSide) {
            ThrowableStoneEntity throwableStone = new ThrowableStoneEntity(pPlayer, pLevel);
            throwableStone.setItem(itemStack);
            throwableStone.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0f, 1.8F, 1.0F);
            pLevel.addFreshEntity(throwableStone);

            if (!pPlayer.isCreative()) {
                itemStack.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide);
    }
}
