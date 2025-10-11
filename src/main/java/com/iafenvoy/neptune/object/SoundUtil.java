package com.iafenvoy.neptune.object;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SoundUtil {
    public static void playSound(Level world, Vec3 pos, ResourceLocation soundId, float volume, float pitch) {
        playSound(world, pos.x, pos.y, pos.z, soundId, volume, pitch);
    }

    public static void playSound(Level world, double x, double y, double z, ResourceLocation soundId, float volume, float pitch) {
        playSound(world, x, y, z, BuiltInRegistries.SOUND_EVENT.get(soundId), volume, pitch);
    }

    public static void playSound(Level world, Vec3 pos, SoundEvent soundEvent, float volume, float pitch) {
        playSound(world, pos.x, pos.y, pos.z, soundEvent, volume, pitch);
    }

    public static void playSound(Level world, double x, double y, double z, SoundEvent soundEvent, float volume, float pitch) {
        if (soundEvent == null) return;
        if (world.isClientSide())
            world.playLocalSound(x, y, z, soundEvent, SoundSource.NEUTRAL, volume, pitch, false);
        else
            world.playSound(null, VecUtil.createBlockPos(x, y, z), soundEvent, SoundSource.NEUTRAL, volume, pitch);
    }

    public static void playPlayerSound(Player player, ResourceLocation soundId, float volume, float pitch) {
        playPlayerSound(player, BuiltInRegistries.SOUND_EVENT.get(soundId), volume, pitch);
    }

    public static void playPlayerSound(Player player, SoundEvent soundId, float volume, float pitch) {
        playPlayerSound(player.getCommandSenderWorld(), player.getX(), player.getY(), player.getZ(), soundId, volume, pitch);
    }

    public static void playPlayerSound(Level world, double x, double y, double z, ResourceLocation soundId, float volume, float pitch) {
        playPlayerSound(world, x, y, z, BuiltInRegistries.SOUND_EVENT.get(soundId), volume, pitch);
    }

    public static void playPlayerSound(Level world, double x, double y, double z, SoundEvent soundEvent, float volume, float pitch) {
        if (soundEvent == null) return;
        if (world.isClientSide())
            world.playLocalSound(x, y, z, soundEvent, SoundSource.PLAYERS, volume, pitch, false);
        else
            world.playSound(null, VecUtil.createBlockPos(x, y, z), soundEvent, SoundSource.PLAYERS, volume, pitch);
    }

    public static void stopSound(Level world, ResourceLocation soundId) {
        if (world instanceof ServerLevel serverLevel) {
            ClientboundStopSoundPacket stopSoundPacket = new ClientboundStopSoundPacket(soundId, SoundSource.NEUTRAL);
            for (ServerPlayer serverPlayer : serverLevel.players())
                serverPlayer.connection.send(stopSoundPacket);
        }
    }
}
