package com.example.tweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.UseAction;

import com.example.tweaks.config.ModConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class FoodMixin {

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void instantFood(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir) {

        UseAction action = stack.getUseAction();

        if (action == UseAction.EAT && ModConfig.instantEat) {
            cir.setReturnValue(1);
        }

    }
}
