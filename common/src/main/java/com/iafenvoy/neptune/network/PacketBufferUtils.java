package com.iafenvoy.neptune.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;

/**
 * Utilities for interacting with PacketBuffer.
 *
 * @author cpw
 * @since 1.0.0
 */
public class PacketBufferUtils {
    public static PacketByteBuf create() {
        return new PacketByteBuf(Unpooled.buffer());
    }
}
