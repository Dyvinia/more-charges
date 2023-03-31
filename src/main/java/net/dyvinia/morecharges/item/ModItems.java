package net.dyvinia.morecharges.item;

import net.dyvinia.morecharges.MoreCharges;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final ItemGroup MORECHARGES = FabricItemGroupBuilder.build(new Identifier(MoreCharges.MOD_ID, "morecharges"),
            () -> new ItemStack(ModItems.ENDER_CHARGE));

    public static final Item ENDER_CHARGE = new EnderChargeItem(new FabricItemSettings().group(MORECHARGES));
    public static final Item AQUA_CHARGE = new AquaChargeItem(new FabricItemSettings().group(MORECHARGES));
    public static final Item RESONATING_CHARGE = new ResonatingChargeItem(new FabricItemSettings().group(MORECHARGES));

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MoreCharges.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MoreCharges.LOGGER.info("Registering Mod Items for MoreCharges");

        registerItem("ender_charge", ENDER_CHARGE);
        registerItem("aqua_charge", AQUA_CHARGE);
        registerItem("resonating_charge", RESONATING_CHARGE);
    }

}
