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

    public static final RegistryObject<EntityType<ThrowableStoneEntity>> THROWABLE_STONE_ENTITY = ENTITY_TYPES.register(
            "throwable_stone_projectile", () -> EntityType.Builder.<ThrowableStoneEntity>of(ThrowableStoneEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("throwable_stone_projectile")
    );


    public static final RegistryObject<EntityType<KnifeEntity>> KNIFE_ENTITY = ENTITY_TYPES.register(
            "knife_projectile",
            () -> EntityType.Builder.<KnifeEntity>of((type, world) -> new KnifeEntity(world, Tiers.WOOD), MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("knife_projectile")
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
