package net.re.sof;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.re.sof.effects.BlizzardEffect;
import net.re.sof.effects.FlameEffect;
import net.re.sof.effects.potion.FreezingEffect;

@Mod.EventBusSubscriber(modid = SecretsOfForging.MODID,
        value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        FreezingEffect.init();
        FlameEffect.init();
        BlizzardEffect.init();
    }
}
