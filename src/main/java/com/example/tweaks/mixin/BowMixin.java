package com.example.tweaks.mixin;

import com.example.tweaks.TweaksMod;
import net.minecraft.world.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BowItem.class)
public class BowMixin {

    @ModifyArg(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BowItem;getPullProgress(I)F"))
    private int instantBowCharge(int useTicks) {
        if (TweaksMod.instantBow) {
            return 72000;
        }
        return useTicks;
    }
}
