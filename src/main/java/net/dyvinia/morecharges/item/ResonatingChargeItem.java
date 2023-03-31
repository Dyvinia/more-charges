package net.dyvinia.morecharges.item;

import net.dyvinia.morecharges.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ResonatingChargeItem extends Item {
    public ResonatingChargeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        Vec3d pos = user.getPos();
        Box box = Box.from(pos).expand(32.0);
        List<LivingEntity> hearingEntities = world.getNonSpectatingEntities(LivingEntity.class, box);

        boolean raidersFound = false;
        for (LivingEntity entity : hearingEntities) {
            if (isRaiderEntity(pos, entity)) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 30));
                raidersFound = true;
            }
        }

        if (raidersFound) {
            user.getItemCooldownManager().set(this, 40);
            if (!world.isClient()) {
                world.playSound(user, pos.getX(), pos.getY(), pos.getZ(),
                        ModSounds.RESONATING_CHARGE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            user.playSound(ModSounds.RESONATING_CHARGE, 1.0f, 1.0f);

            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }

        user.getItemCooldownManager().set(this, 10);
        return TypedActionResult.pass(itemStack);
    }

    private static boolean isRaiderEntity(Vec3d pos, LivingEntity entity) {
        return entity.isAlive() && !entity.isRemoved() && pos.isInRange(entity.getPos(), 32.0) && entity.getType().isIn(EntityTypeTags.RAIDERS);
    }
}
