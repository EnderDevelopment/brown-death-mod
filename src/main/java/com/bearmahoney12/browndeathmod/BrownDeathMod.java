package com.bearmahoney12.browndeathmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public
class BrownDeathMod implements ModInitializer {
    public static final String MOD_ID = "browndeathmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Brown Death Mod");

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();
            if (block == Blocks.BROWN_WOOL || block == Blocks.BROWN_CONCRETE || block == Blocks.BROWN_TERRACOTTA || block == Blocks.BROWN_STAINED_GLASS) {
                player.damage(DamageSource.MAGIC, Float.MAX_VALUE);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            Block block = state.getBlock();
            if (block == Blocks.BROWN_WOOL || block == Blocks.BROWN_CONCRETE || block == Blocks.BROWN_TERRACOTTA || block == Blocks.BROWN_STAINED_GLASS) {
                player.damage(DamageSource.MAGIC, Float.MAX_VALUE);
                return false;
            }
            return true;
        });
    }
}
