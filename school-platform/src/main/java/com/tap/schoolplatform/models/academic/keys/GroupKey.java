package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.enums.Shift;

public record GroupKey(Shift shift, int semester) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GroupKey groupKey = (GroupKey) object;
        return semester == groupKey.semester && shift == groupKey.shift;
    }
}