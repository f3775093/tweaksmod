package com.example.tweaksmod.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowMixin {

    @Inject(method = "use(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/InteractionResultHolder;", 
            at = @At("HEAD"), cancellable = true)
    private void instantUse(ItemStack stack, Level world, Player player, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack arrowStack = player.getProjectile(stack);

        boolean creative = player.getAbilities().instabuild || arrowStack.isEmpty();
        if (!arrowStack.isEmpty() || creative) {
            if (!world.isClientSide) {
                int charge = BowItem.getPowerForTime(20); // max power
                BowItem bow = (BowItem) (Object) this;

                bow.releaseUsing(stack, world, player, charge);
            }

            // Consume arrow if not creative
            if (!creative) {
                arrowStack.shrink(1);
                if (arrowStack.isEmpty()) {
                    player.getInventory().removeItem(arrowStack);
                }
            }

            // Animation + hand swinging
            player.swing(InteractionHand.MAIN_HAND);
            cir.setReturnValue(InteractionResultHolder.success(stack));
        }
    }
}
