package com.oksas.masseffects.entities;

import com.oksas.masseffects.MassEffectsMod;
import com.oksas.masseffects.blocks.ModBlocks;
import com.oksas.masseffects.entities.impl.SiloBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MassEffectsMod.MODID);

    public static final RegistryObject<BlockEntityType<SiloBlockEntity>> SILO_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("silo_block_entity",
                    () -> BlockEntityType.Builder.of(SiloBlockEntity::new, ModBlocks.SILO.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
