package com.iafenvoy.neptune.object;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public class ParticleUtil {
    public static void spawn3x3Particles(Level level, ParticleType<SimpleParticleType> particle, double x, double y, double z) {
        spawn3x3Particles(level, (ParticleOptions) particle, x, y, z);
    }

    public static void spawn3x3Particles(Level level, ParticleOptions particle, double x, double y, double z) {
        for (double i = -3; i <= 3; i += 3)
            for (double j = -3; j <= 3; j += 3)
                if (i != 0 || j != 0)
                    level.addParticle(particle, x, y + 1.0D, z, i, 0.0D, j);
    }

    public static void spawnCircleParticles(Level level, ParticleOptions particle, double x, double y, double z, double r, double angle, double num) {
        if (level instanceof ServerLevel _level)
            for (int index0 = 0; index0 < (int) num; index0++) {
                _level.sendParticles(particle, x + r * Math.cos(angle), y + 1.0D, z + r * Math.sin(angle), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                angle += 360.0D / num;
            }
    }
}
