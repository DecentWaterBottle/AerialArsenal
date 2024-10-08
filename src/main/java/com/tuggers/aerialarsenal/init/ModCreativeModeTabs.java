package com.tuggers.aerialarsenal.init;

import com.tuggers.aerialarsenal.AerialArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AerialArsenal.MODID);

    public static final RegistryObject<CreativeModeTab> AERIAL_ARSENAL_TAB = CREATIVE_MODE_TABS.register("aerial_arsenal_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIAMOND_KNIFE.get()))
                    .title(Component.translatable("creativetab.aerial_arsenal_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                        pOutput.accept(ModItems.THROWABLE_STONE.get());
                        pOutput.accept(ModItems.WOODEN_KNIFE.get());
                        pOutput.accept(ModItems.STONE_KNIFE.get());
                        pOutput.accept(ModItems.IRON_KNIFE.get());
                        pOutput.accept(ModItems.GOLDEN_KNIFE.get());
                        pOutput.accept(ModItems.DIAMOND_KNIFE.get());
                        pOutput.accept(ModItems.NETHERITE_KNIFE.get());
                        pOutput.accept(ModItems.JAVELIN_SHAFT.get());
                        pOutput.accept(ModItems.WOODEN_JAVELIN.get());
                        pOutput.accept(ModItems.STONE_JAVELIN.get());
                        pOutput.accept(ModItems.IRON_JAVELIN.get());
                        pOutput.accept(ModItems.GOLDEN_JAVELIN.get());
                        pOutput.accept(ModItems.DIAMOND_JAVELIN.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
