package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.enums.Shift;
import java.util.Objects;

public class GroupKey {
    private final Shift shift;
    private final int semester;

    public GroupKey(Shift shift, int semester) {
        this.shift = shift;
        this.semester = semester;
    }

    public Shift getShift() {
        return shift;
    }
    public int getSemester() {
        return semester;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GroupKey groupKey = (GroupKey) object;
        return semester == groupKey.semester && shift == groupKey.shift;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shift, semester);
    }
}
