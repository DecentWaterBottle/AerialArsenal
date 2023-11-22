package com.tuggers.aerialarsenal.init;

import com.tuggers.aerialarsenal.AerialArsenal;
import com.tuggers.aerialarsenal.item.KnifeItem;
import com.tuggers.aerialarsenal.item.ThrowableStoneItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AerialArsenal.MODID);

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THROWABLE_STONE = ITEMS.register("throwable_stone",
            () -> new ThrowableStoneItem(new Item.Properties()));

    public static final RegistryObject<Item> WOODEN_KNIFE = ITEMS.register("wooden_knife",
            () -> new KnifeItem(ModEntities.WOODEN_KNIFE_ENTITY, Tiers.WOOD, new Item.Properties().stacksTo(64), 2));

    public static final RegistryObject<Item> STONE_KNIFE = ITEMS.register("stone_knife",
            () -> new KnifeItem(ModEntities.STONE_KNIFE_ENTITY, Tiers.STONE, new Item.Properties().stacksTo(64), 2));

    public static final RegistryObject<Item> IRON_KNIFE = ITEMS.register("iron_knife",
            () -> new KnifeItem(ModEntities.IRON_KNIFE_ENTITY, Tiers.IRON, new Item.Properties(), 2));

    public static final RegistryObject<Item> GOLDEN_KNIFE = ITEMS.register("golden_knife",
            () -> new KnifeItem(ModEntities.GOLDEN_KNIFE_ENTITY, Tiers.GOLD, new Item.Properties(), 2));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
