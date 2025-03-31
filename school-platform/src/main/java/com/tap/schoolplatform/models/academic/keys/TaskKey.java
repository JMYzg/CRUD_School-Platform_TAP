package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.Subject;

public record TaskKey(Subject subject, int unit) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TaskKey taskKey = (TaskKey) object;
        return subject == taskKey.subject && unit == taskKey.unit;
    }
}
