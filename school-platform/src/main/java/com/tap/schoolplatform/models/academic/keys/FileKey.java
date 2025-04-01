package com.tap.schoolplatform.models.academic.keys;

import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.users.Student;

public record FileKey(Group group, Student student) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FileKey fileKey = (FileKey) object;
        return group == fileKey.group && student == fileKey.student;
    }
}
