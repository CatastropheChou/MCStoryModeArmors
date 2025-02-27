package timefall.mcsa.init;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;
import timefall.mcsa.collections.ArmorCollection;
import timefall.mcsa.configs.McsaConfig;
import timefall.mcsa.items.armor.ArmorSetItem;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class LootInit {

    public static final Collection<Identifier> VILLAGER_ARMORER_LOOT_TABLE = Collections.singletonList(
            LootTables.VILLAGE_ARMORER_CHEST);

    public static final Collection<Identifier> STRONGHOLD_LOOT_TABLES = Set.of(LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST, LootTables.STRONGHOLD_LIBRARY_CHEST);

    public static final Collection<Identifier> TEMPLE_LOOT_TABLES = Set.of(LootTables.JUNGLE_TEMPLE_CHEST,
            LootTables.DESERT_PYRAMID_CHEST);

    public static final Collection<Identifier> ARMORER_GIFT_LOOT_TABLE = Collections.singletonList(
            LootTables.HERO_OF_THE_VILLAGE_ARMORER_GIFT_GAMEPLAY);

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> {
        LootPool.Builder builder = LootPool.builder();
            if (VILLAGER_ARMORER_LOOT_TABLE.contains(id)) {
                addArmorSet(builder, ArmorsInit.ELLEGAARD_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.GABRIEL_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.IVOR_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.LUKAS_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.MAGNUS_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.OLIVIA_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.PETRA_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.SOREN_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.TIM_ARMOR, McsaConfig.config.getCharacterArmourSpawnRate());
            } else if (STRONGHOLD_LOOT_TABLES.contains(id)) {
                addArmorSet(builder, ArmorsInit.DRAGONSBANE, McsaConfig.config.getStrongholdArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.ENDER_DEFENDER, McsaConfig.config.getStrongholdArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.GOLDEN_GOLIATH_ARMOR, McsaConfig.config.getStrongholdArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.GOLDEN_GOLIATH_CIRCUITRY_ARMOR, McsaConfig.config.getStrongholdArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.REDSTONE_RIOT, McsaConfig.config.getStrongholdArmourSpawnRate());
            } else if (TEMPLE_LOOT_TABLES.contains(id)) {
                addArmorSet(builder, ArmorsInit.SHIELD_OF_INFINITY, McsaConfig.config.getTempleArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.STAR_SHIELD, McsaConfig.config.getTempleArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.SWORDBREAKER, McsaConfig.config.getTempleArmourSpawnRate());
            } else if (ARMORER_GIFT_LOOT_TABLE.contains(id)) {
                addArmorSet(builder, ArmorsInit.ADAMANTIUM_ARMOR, McsaConfig.config.getHovArmourSpawnRate());
                addArmorSet(builder, ArmorsInit.CHAMPION_PETRA_ARMOR, McsaConfig.config.getHovArmourSpawnRate());
            }
            context.addPool(builder.build());
        });
    }

    public static void addArmorSet(LootPool.Builder poolBuilder, ArmorCollection<ArmorSetItem> armorCollection, float p) {
        armorCollection.getArmor().forEach(armorSetItem -> {
            poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
            poolBuilder.with(ItemEntry.builder(armorSetItem));
        });
    }
}