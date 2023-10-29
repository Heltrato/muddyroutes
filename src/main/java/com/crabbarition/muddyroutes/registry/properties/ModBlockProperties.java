package com.crabbarition.muddyroutes.registry.properties;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModBlockProperties {

    public static BlockBehaviour.Properties brick_properties() {
        return stone_properties(MaterialColor.COLOR_MAGENTA, 1.0F, 3.0F, true);
    }

    public static BlockBehaviour.Properties stone_properties(MaterialColor color, float hardness, float resistance) {
        return stone_properties(Material.STONE, color, hardness, resistance, false);
    }

    public static BlockBehaviour.Properties stone_properties(MaterialColor color, float hardness, float resistance, boolean tool) {
        return stone_properties(Material.STONE, color, hardness, resistance, tool);
    }

    public static BlockBehaviour.Properties stone_properties(Material material, MaterialColor color, float hardness, float resistance, boolean tool) {
        BlockBehaviour.Properties props = basicProps(material, color, SoundType.STONE, hardness, resistance);
        if (tool)
            props.requiresCorrectToolForDrops();
        return props;
    }

    public static BlockBehaviour.Properties basicProps(Material material, MaterialColor color, SoundType sound, float strength) {
        return basicProps(material, color, sound, strength, strength);
    }

    public static BlockBehaviour.Properties basicProps(Material material, MaterialColor color, SoundType sound, float hardness, float resistance) {
        return BlockBehaviour.Properties.of(material, color)
                .strength(hardness, resistance)
                .sound(sound);
    }

    public static BlockBehaviour.Properties plantProps(MaterialColor color, boolean isGlass) {
        return BlockBehaviour.Properties.of(Material.PLANT, color)
                .strength(0.0F)
                .sound(isGlass ? SoundType.GLASS : SoundType.GRASS)
                .noCollission();
    }

}
