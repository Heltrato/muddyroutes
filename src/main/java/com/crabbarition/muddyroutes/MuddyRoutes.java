package com.crabbarition.muddyroutes;

import com.crabbarition.muddyroutes.datagen.*;
import com.crabbarition.muddyroutes.registry.ModBlocks;
import com.crabbarition.muddyroutes.registry.ModEntities;
import com.crabbarition.muddyroutes.registry.ModItems;
import com.crabbarition.muddyroutes.registry.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MuddyRoutes.MODID)
public class MuddyRoutes {
    public static final String MODID = "muddyroutes";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MuddyRoutes() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::modDataGen);
        modEventBus.addListener(ModEntities::mobAttribute);
        modEventBus.addListener(ModEntities::render);
        modEventBus.addListener(ModEntities::addEntitySpawnPlacement);

        ModBlocks.MOD_BLOCKS.register(modEventBus);
        ModItems.MOD_ITEMS.register(modEventBus);
        ModEntities.MOD_ENTITIES.register(modEventBus);
        ModSounds.MOD_SOUNDS.register(modEventBus);
        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        ModBlocks.addPlantsPots();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    public final void modDataGen(GatherDataEvent pEvent) {
        DataGenerator generator = pEvent.getGenerator();

        generator.addProvider(pEvent.includeClient(), new ModBlockStates(generator, pEvent.getExistingFileHelper()));
        generator.addProvider(pEvent.includeClient(), new ModItemModels(generator, pEvent.getExistingFileHelper()));
        generator.addProvider(pEvent.includeClient(), new ModLanguages(generator));
        generator.addProvider(pEvent.includeServer(), new ModLootTables(generator));
        generator.addProvider(pEvent.includeServer(), new ModRecipes(generator));
    }

}
