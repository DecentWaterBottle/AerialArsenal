package com.tuggers.aerialarsenal.init;

import com.tuggers.aerialarsenal.AerialArsenal;
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

    public static final RegistryObject<EntityType<ThrowableStoneEntity>> THROWABLE_STONE_ENTITY = ENTITY_TYPES.register(
            "throwable_stone_projectile", () -> EntityType.Builder.<ThrowableStoneEntity>of(ThrowableStoneEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("throwable_stone_projectile")
    );

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

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
