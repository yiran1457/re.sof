package net.re.sof.mixins;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import se.mickelus.tetra.items.modular.ThrownModularItemEntity;

@Mixin(value = ThrownModularItemEntity.class)
public interface ThrownModularItemEntityInvoker {
    @Accessor("thrownStack")
    ItemStack getStack();
}