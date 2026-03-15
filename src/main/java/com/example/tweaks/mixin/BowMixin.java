package com.example.tweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BowItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowMixin {

    @Inject(method = "use", at = @At("HEAD"))
    public void onUse(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<?> cir) {
        // This is a skeleton: does nothing special yet
        // Your logic for instant shot will go here later
    }
}
