package com.tuggers.aerialarsenal.init;

import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.entity.JavelinEntity;
import com.tuggers.aerialarsenal.entity.KnifeEntity;
import com.tuggers.aerialarsenal.entity.ThrowableStoneEntity;
import com.tuggers.aerialarsenal.item.ThrowableStoneItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AerialArsenal.MODID);

    private static RegistryObject<EntityType<KnifeEntity>> registerKnifeEntity(String name, Tiers tier) {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.<KnifeEntity>of((type, world) -> new KnifeEntity(type, world, tier), MobCategory.MISC)
                        .sized(0.5f, 0.5f)
                        .build(name)
        );
    }

    private static RegistryObject<EntityType<JavelinEntity>> registerJavelinEntity(String name, Tiers tier) {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.<JavelinEntity>of((type, world) -> new JavelinEntity(type, world, tier), MobCategory.MISC)
                        .sized(0.5f, 0.5f)
                        .build(name)
        );
    }

    public static final RegistryObject<EntityType<ThrowableStoneEntity>> THROWABLE_STONE_ENTITY = ENTITY_TYPES.register(
            "throwable_stone_projectile", () -> EntityType.Builder.<ThrowableStoneEntity>of(ThrowableStoneEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("throwable_stone_projectile")
    );

    //region Knife Entities

    public static final RegistryObject<EntityType<KnifeEntity>> WOODEN_KNIFE_ENTITY = registerKnifeEntity(
            "wooden_knife_projectile", Tiers.WOOD
    );

    public static final RegistryObject<EntityType<KnifeEntity>> STONE_KNIFE_ENTITY = registerKnifeEntity(
            "stone_knife_projectile", Tiers.STONE
    );

    public static final RegistryObject<EntityType<KnifeEntity>> IRON_KNIFE_ENTITY = registerKnifeEntity(
            "iron_knife_projectile", Tiers.IRON
    );

    public static final RegistryObject<EntityType<KnifeEntity>> GOLDEN_KNIFE_ENTITY = registerKnifeEntity(
            "golden_knife_projectile", Tiers.GOLD
    );

    public static final RegistryObject<EntityType<KnifeEntity>> DIAMOND_KNIFE_ENTITY = registerKnifeEntity(
            "diamond_knife_projectile", Tiers.DIAMOND
    );

    public static final RegistryObject<EntityType<KnifeEntity>> NETHERITE_KNIFE_ENTITY = registerKnifeEntity(
            "netherite_knife_projectile", Tiers.NETHERITE
    );
    //endregion

    //region Javelin Entities
    public static final RegistryObject<EntityType<JavelinEntity>> WOODEN_JAVELIN_ENTITY = registerJavelinEntity(
            "wooden_javelin_projectile", Tiers.WOOD
    );

    public static final RegistryObject<EntityType<JavelinEntity>> STONE_JAVELIN_ENTITY = registerJavelinEntity(
            "stone_javelin_projectile", Tiers.STONE
    );

    public static final RegistryObject<EntityType<JavelinEntity>> IRON_JAVELIN_ENTITY = registerJavelinEntity(
            "iron_javelin_projectile", Tiers.IRON
    );

    public static final RegistryObject<EntityType<JavelinEntity>> GOLDEN_JAVELIN_ENTITY = registerJavelinEntity(
            "golden_javelin_projectile", Tiers.GOLD
    );

    public static final RegistryObject<EntityType<JavelinEntity>> DIAMOND_JAVELIN_ENTITY = registerJavelinEntity(
            "diamond_javelin_projectile", Tiers.DIAMOND
    );

    //endregion

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
