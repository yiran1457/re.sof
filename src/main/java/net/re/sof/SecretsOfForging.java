package net.re.sof;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.re.sof.effects.BlizzardEffect;
import net.re.sof.effects.FlameEffect;
import net.re.sof.effects.potion.FreezingEffect;
import net.re.sof.effects.potion.PotionEffects;
import net.re.sof.item.ModularPolearm;
import se.mickelus.tetra.TetraMod;

@Mod(SecretsOfForging.MODID)
public class SecretsOfForging {
    public static final String MODID = "secrets_of_forging";
    public IEventBus EVENTBUS;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TetraMod.MOD_ID);
    public SecretsOfForging() {

        EVENTBUS = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(EVENTBUS);
        ITEMS.register(ModularPolearm.identifier, ModularPolearm::new);

        // Potion Effects
        PotionEffects.register(EVENTBUS);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Freezing
        MinecraftForge.EVENT_BUS.register(new FreezingEffect());
        // Infernal
        MinecraftForge.EVENT_BUS.register(new FlameEffect());
        // Blizzard
        MinecraftForge.EVENT_BUS.register(new BlizzardEffect());

    }
}
