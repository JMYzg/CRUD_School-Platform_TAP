package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.academic.Subject;
import com.tap.schoolplatform.models.academic.keys.FileKey;
import com.tap.schoolplatform.models.academic.keys.TaskKey;
import com.tap.schoolplatform.models.academic.tasks.Assignment;
import com.tap.schoolplatform.models.academic.tasks.Task;
import com.tap.schoolplatform.models.users.Student;

import java.io.File;

public class StudentService {

    private Student student;

    public StudentService(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public void uploadFile(Assignment assignment, File file) {
        FileKey key = new FileKey(student.getGroup(), student);
        assignment.submitFile(key, file);
    }


}
