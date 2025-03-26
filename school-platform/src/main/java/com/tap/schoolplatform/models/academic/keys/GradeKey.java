package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.Subject;

import java.util.Objects;

public class GradeKey {
    private final Subject subject;
    private final int unit;

    public GradeKey(Subject subject, int unit) {
        this.subject = subject;
        this.unit = unit;
    }

    public Subject getSubject() {
        return subject;
    }
    public int getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GradeKey gradeKey = (GradeKey) object;
        return subject == gradeKey.subject && unit == gradeKey.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, unit);
    }
}
