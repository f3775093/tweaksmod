package com.example.tweaks.mixin;

import com.example.tweaks.config.ModConfig;

import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BowItem.class)
public class BowMixin {

    @ModifyVariable(
        method = "onStoppedUsing",
        at = @At("HEAD"),
        ordinal = 0,
        argsOnly = true
    )
    private int instantBowCharge(int remainingUseTicks, ItemStack stack, World world, LivingEntity user) {

        if (ModConfig.instantBow) {
            return 72000 - 20; // simulate 20 ticks of full draw
        }

        return remainingUseTicks;
    }

}
