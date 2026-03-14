package com.example.tweaks.mixin;

import com.example.tweaks.TweaksMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void instantEat(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (TweaksMod.instantEat && stack.getUseAction() == UseAction.EAT) {
            cir.setReturnValue(1);
        }
    }
}
