package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.Subject;

public record GradeKey(Subject subject, int unit) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GradeKey gradeKey = (GradeKey) object;
        return subject == gradeKey.subject && unit == gradeKey.unit;
    }
}