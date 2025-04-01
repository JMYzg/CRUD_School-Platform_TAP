package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.tasks.Task;

public record GradeKey(Subject subject, int unit, Task task) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GradeKey gradeKey = (GradeKey) object;
        return subject == gradeKey.subject && task == gradeKey.task() && unit == gradeKey.unit;
    }
}