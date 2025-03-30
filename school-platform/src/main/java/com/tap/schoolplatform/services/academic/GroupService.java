package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.utils.UserDTO;

public class GroupService {

    private Group group;

    public GroupService(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public void createStudent(User user) {
        Student student = (Student) user;
        student.setGroup(group);
    }

    public Student readStudent(String studentId) {
        for (Student student : group.getStudentList()) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student student, UserDTO userDTO) {
        if (userDTO.getStatus() != null) student.setStatus(userDTO.getStatus());
        if (userDTO.getGroup() != null) student.setGroup(userDTO.getGroup());
    }

    public void deleteStudent(Student student) {
        group.getStudentList().remove(student);
    }
}
