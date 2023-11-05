package com.crabbarition.muddyroutes.datagen.provider;

import net.minecraft.data.loot.EntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.Supplier;

public abstract class ModEntityLootProvider extends EntityLoot {

    public void addTable(Supplier<? extends EntityType<?>> entity, LootTable.Builder table) {
        this.add(entity.get(), table);
    }

    public LootTable.Builder blankTable() {
        return LootTable.lootTable();
    }

    public LootTable.Builder singleDropTable(Supplier<Item> drop, float minCount, float maxCount) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(drop.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableSingleDropTable(Supplier<Item> raw, float minCount, float maxCount) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(raw.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)))
                                .apply(SmeltItemFunction.smelted()
                                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableDoubleDropTable(Supplier<Item> cookable, Supplier<Item> drop, float minCount1, float maxCount1, float minCount2, float maxCount2) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(cookable.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount1, maxCount1)))
                                .apply(SmeltItemFunction.smelted()
                                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(drop.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount2, maxCount2)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));
    }

    final public LootTable.Builder singleDropWithDropChanceAndDropSmeltFeature(Supplier<Item> drop1, float minCount1, float maxCount1, Supplier<Item> drop2, float minCount2, float maxCount2, float dropProbability, float boostProbability, Supplier<Item> drop3, float minCount3, float maxCount3) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(CONSTANT).add(LootItem.lootTableItem(drop1.get()).apply(noOfDrops(uniformRange(minCount1, maxCount1))).apply(absouluteMultiplier())))
                .withPool(LootPool.lootPool().setRolls(CONSTANT).add(LootItem.lootTableItem(drop2.get()).apply(noOfDrops(uniformRange(minCount2, maxCount2))).apply(absouluteMultiplier())).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(dropProbability, boostProbability)))
                .withPool(LootPool.lootPool().setRolls(CONSTANT).add(LootItem.lootTableItem(drop3.get()).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).apply(absouluteMultiplier())));
    }

    final public LootTable.Builder singleDropTableWithSmeltFeature(Supplier<Item> drop, float minCount, float maxCount) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(CONSTANT).add(LootItem.lootTableItem(drop.get()).apply(noOfDrops(uniformRange(minCount, maxCount))).apply(absouluteMultiplier())).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))).apply(absouluteMultiplier());
    }

    final ConstantValue CONSTANT = ConstantValue.exactly(1);

    protected LootItemConditionalFunction.Builder<?> noOfDrops(UniformGenerator range) {
        return SetItemCountFunction.setCount(range);
    }

    protected UniformGenerator uniformRange(float x, float y) {
        return UniformGenerator.between(x, y);
    }

    protected LootingEnchantFunction.Builder lootMultiplierFunction(UniformGenerator range) {
        return LootingEnchantFunction.lootingMultiplier(range);
    }

    protected LootingEnchantFunction.Builder absouluteMultiplier() {
        return lootMultiplierFunction(absoluteRange());
    }

    protected UniformGenerator absoluteRange() {
        return uniformRange(0.0F, 1.0F);
    }


}
