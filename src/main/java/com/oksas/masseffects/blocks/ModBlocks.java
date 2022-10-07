package com.oksas.masseffects.blocks;

import com.oksas.masseffects.MassEffectsMod;
import com.oksas.masseffects.blocks.impl.LavaSponge;
import com.oksas.masseffects.blocks.impl.MoltenLavaSponge;
import com.oksas.masseffects.blocks.impl.Silo;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MassEffectsMod.MODID);

    public static final RegistryObject<Block> SILO =
            BLOCKS.register("silo",
                    () -> new Silo(BlockBehaviour.Properties
                            .of(Material.HEAVY_METAL)
                            .strength(10f)
                            .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LAVA_SPONGE =
            BLOCKS.register("lava_sponge",
                    () -> new LavaSponge(BlockBehaviour.Properties
                            .of(Material.STONE)));

    public static final RegistryObject<Block> MOLTEN_LAVA_SPONGE =
            BLOCKS.register("molten_lava_sponge",
                    () -> new MoltenLavaSponge(BlockBehaviour.Properties
                            .of(Material.STONE)));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
