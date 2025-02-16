package com.iafenvoy.neptune.power.type;

import com.iafenvoy.neptune.power.PowerCategory;
import com.iafenvoy.neptune.power.PowerDataHolder;

public non-sealed abstract class DummyPower extends AbstractPower<DummyPower> {
    public static final DummyPower EMPTY = new DummyPower("", null) {
        @Override
        protected boolean applyInternal(PowerDataHolder holder) {
            return true;
        }
    };

    public DummyPower(String id, PowerCategory category) {
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
