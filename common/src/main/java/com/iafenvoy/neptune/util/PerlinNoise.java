package com.iafenvoy.neptune.util;

import it.unimi.dsi.fastutil.doubles.Double2DoubleFunction;
import it.unimi.dsi.fastutil.doubles.DoubleDoublePair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// a simple Perlin Noise generator
public class PerlinNoise {
    private final double baseHeight;
    private final List<Noise2D> noiseArr;

    public PerlinNoise(long seed, double baseHeight, List<NoiseConfig> noiseConfigList) {
        this.baseHeight = baseHeight;
        this.noiseArr = new LinkedList<>();
        for (int i = 0; i < noiseConfigList.size(); i++) {
            NoiseConfig n = noiseConfigList.get(i);
            this.noiseArr.add(new Noise2D(n.diff, seed + i, n.loud));
        }
    }

    public double getHeight(int x, int y) {
        double sum = 0;
        for (Noise2D noise : this.noiseArr)
            sum += noise.getBuff(x, y);
        return this.baseHeight + sum;
    }

    public static class Noise2D {
        private final double diff;
        private final long seed;
        private final double loud;

        public Noise2D(double diff, long seed, double loud) {
            this.diff = diff;
            this.seed = seed;
            this.loud = loud;
        }

        private List<DoubleDoublePair> getNoisePosition(double x, double y) {
            double xMin, xMax, yMin, yMax;
            if (x % this.diff == 0) {
                xMin = x;
                xMax = x + this.diff;
            } else {
                xMin = x - x % this.diff;
                xMax = xMin + this.diff;
            }
            if (y % this.diff == 0) {
                yMin = y;
                yMax = y + this.diff;
            } else {
                yMin = y - y % this.diff;
                yMax = yMin + this.diff;
            }
            // 左上 右上 左下 右下
            return List.of(DoubleDoublePair.of(xMin, yMax), DoubleDoublePair.of(xMax, yMax), DoubleDoublePair.of(xMin, yMin), DoubleDoublePair.of(xMax, yMin));
        }

        private double getCoreNoise(double x, double y) {
            List<DoubleDoublePair> p = this.getNoisePosition(x, y);
            for (DoubleDoublePair pair : p)
                if (pair.firstDouble() == x && pair.secondDouble() == y)
                    return (new Random(hashCode("%s-%s-%s".formatted(x, y, this.seed))).nextDouble() * 2 - 1) * this.loud;
            return 0;
        }

        private double getCoreNoise(DoubleDoublePair pair) {
            return this.getCoreNoise(pair.firstDouble(), pair.secondDouble());
        }

        /**
         * 获取噪音实际值
         *
         * @param x {Number}
         * @param y {Number}
         */
        public double getBuff(int x, int y) {
            Double2DoubleFunction s = xi -> 3 * xi * xi - 2 * xi * xi * xi; // 平滑函数
            List<DoubleDoublePair> p = this.getNoisePosition(x, y);
            double xMin = p.get(0).firstDouble();
            double yMin = p.get(3).secondDouble();
            double qRight = s.applyAsDouble((x - xMin) / this.diff);
            double qLeft = 1 - qRight;
            double qTop = s.applyAsDouble((y - yMin) / this.diff);
            double qDown = 1 - qTop;
            double n1 = this.getCoreNoise(p.get(0));
            double n2 = this.getCoreNoise(p.get(1));
            double n3 = this.getCoreNoise(p.get(2));
            double n4 = this.getCoreNoise(p.get(3));
            return n1 * (qLeft * qTop) + n2 * (qRight * qTop) + n3 * (qLeft * qDown) + n4 * (qRight * qDown);
        }


        public static long hashCode(String str) {
            long hash = 0;
            long prime = 1000003; // 取一个大一点的质数作为模数
            for (int i = 0; i < str.length(); i++)
                hash = ((hash << 5) - hash + str.charAt(i)) % prime;
            return hash;
        }
    }

    public record NoiseConfig(double diff, double loud) {
        public static NoiseConfig of(double diff, double loud) {
            return new NoiseConfig(diff, loud);
        }
    }
}
