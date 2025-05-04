package com.lynxxdg.fishnshits.items;

import com.lynxxdg.fishnshits.Fishnshits;
import com.lynxxdg.fishnshits.entities.CustomBobberEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CustomRod extends FishingRodItem {
    public CustomRod(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.fishHook != null) {
            if (!world.isClient) {
                int i = user.fishHook.use(itemStack);
                itemStack.damage(i, user, LivingEntity.getSlotForHand(hand));
            }

            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE,
                    SoundCategory.NEUTRAL,
                    1.0F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_THROW,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (world instanceof ServerWorld serverWorld) {
                int lure = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, itemStack, user) * 20.0F);
                int luck = EnchantmentHelper.getFishingLuckBonus(serverWorld, itemStack, user);
                Fishnshits.LOGGER.info("lure: " + lure + " luck: " + luck);
                CustomBobberEntity bobber = new CustomBobberEntity(user, world, luck, lure, Colors.RED);
                bobber.setVelocity(bobber.getVelocity().multiply(1.3));
                ProjectileEntity.spawn(bobber, serverWorld, itemStack);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return ActionResult.SUCCESS;
    }
}
