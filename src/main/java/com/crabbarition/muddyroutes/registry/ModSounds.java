package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> MOD_SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MuddyRoutes.MODID);

    public final static RegistryObject<SoundEvent> SHRIMP_HURT = create("shrimp.hurt");
    public final static RegistryObject<SoundEvent> SHRIMP_KILL = create("shrimp.kill");


    private static RegistryObject<SoundEvent> create(String name) {
        return MOD_SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(MuddyRoutes.MODID, name)));
    }

}
