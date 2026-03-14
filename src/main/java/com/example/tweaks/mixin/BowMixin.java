package com.example.tweaksmod.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowMixin {

    /**
     * Inject at the start of BowItem.use() so the bow shoots instantly on right click.
     * This replicates the "alpha bow" mechanic: full power immediately, no charge.
     */
    @Inject(method = "use(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/InteractionResultHolder;", 
            at = @At("HEAD"), cancellable = true)
    private void instantUse(ItemStack stack, Level world, Player player, CallbackInfoReturnable<?> cir) {
        if (!world.isClientSide) {
            // Full-power arrow shot
            int maxUseTime = 20; // simulate full charge
            BowItem bow = (BowItem) (Object) this;
            bow.releaseUsing(stack, world, player, maxUseTime);
        }
        cir.setReturnValue(net.minecraft.world.InteractionResultHolder.success(stack));
    }
}
