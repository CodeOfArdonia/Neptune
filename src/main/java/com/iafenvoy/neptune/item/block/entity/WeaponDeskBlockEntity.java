package com.iafenvoy.neptune.item.block.entity;

import com.iafenvoy.neptune.registry.NeptuneBlockEntities;
import com.iafenvoy.neptune.screen.handler.WeaponDeskScreenHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WeaponDeskBlockEntity extends BlockEntity implements MenuProvider {
    public WeaponDeskBlockEntity(BlockPos pos, BlockState state) {
        super(NeptuneBlockEntities.WEAPON_DESK.get(), pos, state);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("gui.neptune.weapon_desk.title");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, @NotNull Inventory playerInventory, @NotNull Player player) {
        return new WeaponDeskScreenHandler(syncId, playerInventory, new ContainerLevelAccess() {
            @Override
            public <T> @NotNull Optional<T> evaluate(@NotNull BiFunction<Level, BlockPos, T> getter) {
                return Optional.of(getter.apply(WeaponDeskBlockEntity.this.level, WeaponDeskBlockEntity.this.worldPosition));
            }
        });
    }
}
