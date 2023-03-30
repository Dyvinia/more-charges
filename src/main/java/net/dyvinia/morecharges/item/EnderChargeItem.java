package net.dyvinia.morecharges.item;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EnderChargeItem extends Item {
    public EnderChargeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            double d = user.getX();
            double e = user.getY();
            double f = user.getZ();
            for (int i = 0; i < 24; ++i) {
                double g = user.getX() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                double h = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(24) - 8), world.getBottomY(), world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1);
                double j = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }
                if (!user.teleport(g, h, j, true)) continue;
                SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0f, 1.0f);
                user.playSound(soundEvent, 1.0f, 1.0f);
                break;
            }
            if (user instanceof PlayerEntity) {
                user.getItemCooldownManager().set(this, 50);
                user.damage(DamageSource.FALL, 5.0f);
                user.onLanding();
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
