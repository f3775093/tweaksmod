package com.example.tweaks.mixin;

import com.example.tweaks.config.ModConfig;

import net.minecraft.class_1309;       // LivingEntity
import net.minecraft.class_1799;       // ItemStack
import net.minecraft.class_2248;       // BowItem
import net.minecraft.class_1792;       // Item
import net.minecraft.class_3764;       // Level
import net.minecraft.class_2487;       // Player
import net.minecraft.class_3625;       // InteractionHand
import net.minecraft.class_1806;       // InteractionResultHolder
import net.minecraft.class_4005;       // ArrowItem

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2248.class) // BowItem
public class BowMixin {

    @Inject(method = "method_7904", at = @At("HEAD"), cancellable = true) // use()
    private void instantBowUse(class_1799 stack, class_3764 world, class_2487 player, CallbackInfoReturnable<class_1806> cir) {

        if (!ModConfig.instantBow) return;

        // Only run on server side
        if (!world.field_1687) {

            // get best arrow stack
            class_1799 arrowStack = player.method_5436(stack);

            boolean creative = player.field_7514.method_5457() || arrowStack.method_7909() == null;

            if (!creative && !(arrowStack.method_7909() instanceof net.minecraft.class_358)) {
                // Not arrow? pass
                return;
            }

            // Fire full power arrow
            int power = 20;
            ((net.minecraft.class_2248)(Object)this).method_7888(stack, world, player, power);

            // consume arrow if not creative
            if (!creative) {
                arrowStack.method_8091(arrowStack.field_7724 - 1);
            }
        }

        // simulate success and cancel normal
        cir.setReturnValue(new net.minecraft.class_1806(net.minecraft.class_1806.class_7948.field_35753, stack));
    }
}
