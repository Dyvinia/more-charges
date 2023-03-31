package net.dyvinia.morecharges.sound;

import net.dyvinia.morecharges.MoreCharges;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static final Identifier AQUA_CHARGE_ID = new Identifier(MoreCharges.MOD_ID, "aqua_charge");
    public static SoundEvent AQUA_CHARGE = new SoundEvent(AQUA_CHARGE_ID);

    public static final Identifier RESONATING_CHARGE_ID = new Identifier(MoreCharges.MOD_ID, "resonating_charge");
    public static SoundEvent RESONATING_CHARGE = new SoundEvent(RESONATING_CHARGE_ID);

    public static void registerModSounds() {
        Registry.register(Registry.SOUND_EVENT, AQUA_CHARGE_ID, AQUA_CHARGE);
        Registry.register(Registry.SOUND_EVENT, RESONATING_CHARGE_ID, RESONATING_CHARGE);
    }
}
