package com.oksas.masseffects.items;

import com.oksas.masseffects.MassEffectsMod;
import com.oksas.masseffects.blocks.ModBlocks;
import com.oksas.masseffects.items.impl.MoltenLavaSpongeItem;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MassEffectsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM =
            ITEMS.register("silo", () -> new BlockItem(ModBlocks.SILO.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> LAVA_SPONGE_ITEM =
            ITEMS.register("lava_sponge", () -> new BlockItem(ModBlocks.LAVA_SPONGE.get(),
                    new Item.Properties()
                            .fireResistant()
                            .tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<BlockItem> MOLTEN_LAVA_SPONGE_ITEM =
            ITEMS.register("molten_lava_sponge", () -> new MoltenLavaSpongeItem(ModBlocks.MOLTEN_LAVA_SPONGE.get(),
                    new Item.Properties()
                            .craftRemainder(ModItems.LAVA_SPONGE_ITEM.get())
                            .stacksTo(1)
                            .fireResistant()
                            .tab(CreativeModeTab.TAB_MISC)));
}
