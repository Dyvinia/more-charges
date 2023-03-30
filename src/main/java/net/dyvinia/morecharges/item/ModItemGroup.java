package net.dyvinia.morecharges.item;

import net.dyvinia.morecharges.MoreCharges;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup MORECHARGES = FabricItemGroupBuilder.build(new Identifier(MoreCharges.MOD_ID, "morecharges"),
            () -> new ItemStack(ModItems.ENDER_CHARGE));
}
