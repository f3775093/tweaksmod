package com.yourmod.tweaksmod.mixin;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import com.yourmod.tweaksmod.ModConfig;

@Mixin(BowItem.class)
public class BowMixin {

    /**
     * Forces bows to reach full charge instantly.
     * Adjust method signature to match Minecraft 1.21.11.
     */
    @ModifyVariable(
        method = "onStoppedUsing(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)V",
        at = @At("HEAD"),
        ordinal = 0,
        argsOnly = true
    )
    private int instantBowCharge(int remainingUseTicks, ItemStack stack, Level world, LivingEntity user) {
        if (ModConfig.instantBow) {
            // Max bow charge in ticks; Minecraft uses 72000 max for held items
            return 72000 - 1; 
        }
        return remainingUseTicks;
    }
}
