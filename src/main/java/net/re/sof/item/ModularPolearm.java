package net.re.sof.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.tetra.ConfigHandler;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.schematic.RepairSchematic;

import javax.annotation.Nullable;

public class ModularPolearm extends ItemModularHandheld {

    public final static String headKey = "polearm/head";
    public final static String handleKey = "polearm/handle";

    public final static String bindingKey = "polearm/binding";

    public static final String identifier = "modular_polearm";

    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(1, -3, -11, 21);
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(-14, 0);

    @ObjectHolder(
            registryName = "item",
            value = "tetra:modular_polearm"
    )
    public static ModularPolearm instance;

    public ModularPolearm() {
        super(new Item.Properties().stacksTo(1));

        entityHitDamage = 1;

        majorModuleKeys = new String[]{headKey, handleKey};
        minorModuleKeys = new String[]{bindingKey};

        requiredModules = new String[]{handleKey, headKey};

        updateConfig(ConfigHandler.honeSingleBase.get(), ConfigHandler.honeSingleIntegrityMultiplier.get());


        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
    }


    public void commonInit(PacketHandler packetHandler) {
        DataManager.instance.synergyData.onReload(() -> synergies = DataManager.instance.synergyData.getOrdered("polearm/"));
    }

    public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
    }

    public String getModelCacheKey(ItemStack itemStack, LivingEntity entity) {
        if (isThrowing(itemStack, entity)) {
            return super.getModelCacheKey(itemStack, entity) + ":throwing";

        }

        return super.getModelCacheKey(itemStack, entity);
    }

    @OnlyIn(Dist.CLIENT)
    public String getTransformVariant(ItemStack itemStack, @Nullable LivingEntity entity) {
        return isThrowing(itemStack, entity) ? "throwing" : null;
    }

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMajorGuiOffsets(ItemStack itemStack) {
        return majorOffsets;
    }

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets(ItemStack itemStack) {
        return minorOffsets;
    }


    private ItemStack setupPolearm(String head, String handle) {
        ItemStack itemStack = new ItemStack(this);
        IModularItem.putModuleInSlot(itemStack, headKey, "polearm/spearhead", "polearm/spearhead_material", "spearhead/" + head);
        IModularItem.putModuleInSlot(itemStack, handleKey, "polearm/basic_handle", "polearm/basic_handle_material", "basic_handle/" + handle);
        IModularItem.updateIdentifier(itemStack);

        return itemStack;
    }
}
