package net.dyvinia.morecharges.item;

import net.dyvinia.morecharges.MoreCharges;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item ENDER_CHARGE = registerItem("ender_charge",
            new EnderChargeItem(new FabricItemSettings().group(ModItemGroup.MORECHARGES)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MoreCharges.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MoreCharges.LOGGER.info("Registering Mod Items for MoreCharges");
    }

}
