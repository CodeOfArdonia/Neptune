package com.iafenvoy.neptune.power;

import com.iafenvoy.neptune.power.type.AbstractPower;
import com.iafenvoy.neptune.power.type.DummyPower;
import com.iafenvoy.neptune.util.Color4i;
import com.iafenvoy.neptune.util.RandomHelper;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.BooleanSupplier;

public class PowerCategory {
    private static final List<PowerCategory> CATEGORIES = new LinkedList<>();
    private final Identifier id;
    private final Color4i color;
    private final BooleanSupplier shouldDisplay;
    private final List<AbstractPower<?>> powers = new ArrayList<>();
    private final Map<Identifier, AbstractPower<?>> byId = new HashMap<>();

    PowerCategory(Identifier id, Color4i color, BooleanSupplier shouldDisplay) {
        this.id = id;
        this.color = color;
        this.shouldDisplay = shouldDisplay;
        CATEGORIES.add(this);
    }

    public Identifier getId() {
        return this.id;
    }

    public Color4i getColor() {
        return this.color;
    }

    public MutableText appendColor(MutableText text) {
        return text.fillStyle(Style.EMPTY.withColor(this.getColor().getIntValue()));
    }

    public void registerPower(AbstractPower<?> power) {
        this.powers.add(power);
        AbstractPower<?> p = this.byId.put(power.getId(), power);
        if (p != null)
            throw new IllegalArgumentException("Duplicated id " + p.getId() + " for power type " + this.id + "!");
    }

    public AbstractPower<?> getPowerById(Identifier id) {
        return this.powers.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(DummyPower.EMPTY);
    }

    public AbstractPower<?> randomOne() {
        return RandomHelper.randomOne(this.powers);
    }

    public static Optional<PowerCategory> byId(Identifier id) {
        return CATEGORIES.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public boolean shouldDisplay() {
        return this.shouldDisplay.getAsBoolean();
    }

    public static List<PowerCategory> values() {
        return CATEGORIES;
    }
}
