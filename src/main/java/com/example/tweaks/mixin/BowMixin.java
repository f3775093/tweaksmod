package com.example.tweaks.mixin;

import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.UseAction;

import com.example.tweaks.config.ModConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowMixin {

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void instantBow(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir) {

        if (ModConfig.instantBow) {
            cir.setReturnValue(1);
        }

    }
}
