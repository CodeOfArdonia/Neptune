package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerDataHolder;
import net.minecraft.util.Identifier;

public non-sealed abstract class DummyPower extends AbstractPower<DummyPower> {
    public static final DummyPower EMPTY = new DummyPower(Identifier.tryParse(""), null) {
        @Override
        protected boolean applyInternal(PowerDataHolder holder) {
            return true;
        }
    };

    public DummyPower(Identifier id, PowerCategory category) {
        super(id, category);
    }

    @Override
    protected PowerType getType() {
        return PowerType.DUMMY;
    }

    @Override
    protected DummyPower get() {
        return this;
    }
}
