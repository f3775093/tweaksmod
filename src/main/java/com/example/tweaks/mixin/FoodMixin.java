package com.example.tweaks.mixin;

import com.example.tweaks.config.ModConfig;
import net.minecraft.class_1799;          // ItemStack
import net.minecraft.class_1309;          // LivingEntity / Player
import net.minecraft.class_1802;          // Item
import net.minecraft.class_1792;          // UseAction

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1802.class) // Item
public class FoodMixin {

    @Inject(method = "method_57826", at = @At("HEAD"), cancellable = true) // getMaxUseTime
    private void instantFood(class_1799 stack, class_1309 user, CallbackInfoReturnable<Integer> cir) {
        // Only if toggle enabled and this item is food
        if (ModConfig.instantEat && stack.method_7909() == net.minecraft.class_359.use) { // if EAT action
            cir.setReturnValue(1);
        }
    }
}
