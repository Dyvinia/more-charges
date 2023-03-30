package net.dyvinia.morecharges.item;

import net.dyvinia.morecharges.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class AquaChargeItem extends Item {
    public AquaChargeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = AquaChargeItem.raycast(world, user, RaycastContext.FluidHandling.WATER);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }

        if (blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);

            if (!world.canPlayerModifyAt(user, blockPos)) {
                return TypedActionResult.fail(itemStack);
            }

            if (blockState.getBlock() instanceof FluidBlock) {
                if (blockState.get(FluidBlock.LEVEL) > 0) {
                    world.setBlockState(blockPos, Blocks.WATER.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);

                    SoundEvent soundEvent = ModSounds.AQUA_CHARGE;
                    user.playSound(soundEvent, 1.0f, 1.0f);

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    if (!user.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }
                    return TypedActionResult.success(itemStack, world.isClient());
                }
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}
