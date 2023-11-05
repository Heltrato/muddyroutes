package com.crabbarition.muddyroutes.registry;

import com.crabbarition.muddyroutes.MuddyRoutes;
import com.crabbarition.muddyroutes.block.AirPlantBlock;
import com.crabbarition.muddyroutes.datagen.ModBlockStates;
import com.crabbarition.muddyroutes.datagen.ModItemModels;
import com.crabbarition.muddyroutes.datagen.ModLanguages;
import com.crabbarition.muddyroutes.datagen.ModLootTables;
import com.crabbarition.muddyroutes.registry.properties.ModBlockProperties;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ModBlocks {

    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MuddyRoutes.MODID);

    public final static RegistryObject<Block> mossy_mud_bricks = register("mossy_mud_bricks", ModBlockProperties.mud_brick(MaterialColor.COLOR_MAGENTA, 1.5f, 3f, true));
    public final static RegistryObject<StairBlock> mossy_mud_stairs = register("mossy_mud_stairs", makeStairs(mossy_mud_bricks));
    public final static RegistryObject<SlabBlock> mossy_mud_slabs = register("mossy_mud_slabs", makeSlab(ModBlockProperties.mud_brick(MaterialColor.COLOR_MAGENTA, 1.5f, 3f, true)));
    public final static RegistryObject<WallBlock> mossy_mud_walls = register("mossy_mud_walls", makeWall(mossy_mud_bricks));
    public final static RegistryObject<RotatedPillarBlock> mud_mosaic = register("mud_mosaic", () -> new RotatedPillarBlock(ModBlockProperties.mud_brick(MaterialColor.COLOR_CYAN, 1.5f, 3f, true)));

    public final static RegistryObject<Block> air_plant = register("air_plant", () -> new AirPlantBlock());

    public final static RegistryObject<FlowerPotBlock> potted_air_plant = registerFlowerPot(air_plant);

    public static void addLanguage(ModLanguages pLang) {
        pLang.addLang(mossy_mud_bricks, "Mossy Mud Bricks");
        pLang.addLang(mossy_mud_stairs, "Mossy Mud Stairs");
        pLang.addLang(mossy_mud_slabs, "Mossy Mud Slabs");
        pLang.addLang(mossy_mud_walls, "Mossy Mud Walls");

        pLang.addLang(mud_mosaic, "Mud Mosaic");
        pLang.addLang(air_plant, "Air Plant");
        pLang.addLang(potted_air_plant, "Potted Air Plant");

    }

    public static void createBlockModel(ModBlockStates pState) {
        pState.basicBlock(mossy_mud_bricks);
        pState.stairsBlock(mossy_mud_stairs, "mossy_mud_bricks");
        pState.slabBlock(mossy_mud_slabs, mossy_mud_bricks);
        pState.wallBlock(mossy_mud_walls, "mossy_mud_bricks");
        pState.axisLogBlock(mud_mosaic);
        pState.crossBlock(air_plant, "cutout");
        pState.pottedPlantBlock(potted_air_plant);
    }

    public static void createBlockItemModel(ModItemModels pModel) {
        pModel.blockItem(mossy_mud_bricks);
        pModel.blockItem(mossy_mud_stairs);
        pModel.blockItem(mossy_mud_slabs);
        pModel.wallBlockInventory(mossy_mud_walls, mossy_mud_bricks);

        pModel.blockItem(mud_mosaic);
        pModel.blockItemTexture(air_plant);

    }

    public static void createBlockLootTable(ModLootTables.AddBlockLoots pBlocks) {
        pBlocks.dropSelf(mossy_mud_bricks);
        pBlocks.dropSelf(mossy_mud_stairs);
        pBlocks.dropSelf(mossy_mud_slabs);
        pBlocks.dropSelf(mossy_mud_walls);
        pBlocks.dropSelf(mud_mosaic);
        pBlocks.dropSelf(air_plant);
        pBlocks.dropPot(potted_air_plant);

    }

    public static void addPlantsPots() {
        FlowerPotBlock block = (FlowerPotBlock) Blocks.FLOWER_POT;
        block.addPlant(air_plant.getId(), potted_air_plant);
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> block) {
        return MOD_BLOCKS.register(name, block);
    }


    private static RegistryObject<FlowerPotBlock> registerFlowerPot(RegistryObject<? extends Block> plant) {
        return registerNoItem("potted_" + plant.getId().getPath(), () ->
                new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, Block.Properties.of(Material.DECORATION).strength(0.0F)));
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemFuel(final RegistryObject<T> block, int burnTime) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(ModTabs.muddy_routes_tabs)) {
            @Override
            public int getBurnTime(ItemStack itemStack, RecipeType<?> type) {
                return burnTime;
            }
        };
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = MOD_BLOCKS.register(name, block);
        ModItems.MOD_ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> block) {
        return register(name, block, 0);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> block, int burnTime) {
        return registerBlock(name, block, item -> registerBlockItemFuel(item, burnTime));
    }

    private static Supplier<StairBlock> makeStairs(RegistryObject<? extends Block> block) {
        return () -> new StairBlock(() -> block.get().defaultBlockState(), Properties.copy(block.get()));
    }

    private static Supplier<WallBlock> makeWall(RegistryObject<? extends Block> block) {
        return () -> new WallBlock(Properties.copy(block.get()));
    }

    private static Supplier<SlabBlock> makeSlab(Properties props) {
        return () -> new SlabBlock(props);
    }


    private static RegistryObject<Block> register(String name, Properties props) {
        return register(name, props, 0);
    }

    private static RegistryObject<Block> register(String name, Properties props, int burn) {
        return register(name, () -> new Block(props), burn);
    }


}
