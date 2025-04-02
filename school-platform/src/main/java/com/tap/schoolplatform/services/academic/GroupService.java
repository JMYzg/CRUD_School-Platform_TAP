package com.tap.schoolplatform.services.academic;

import com.tap.schoolplatform.models.academic.Group;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.utils.dtos.UserDTO;

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

    public void createStudent(Student student) {
        student.setGroup(group);
    }

    public Student readStudent(String ID) {
        for (Student student : group.getStudentList()) {
            if (student.getID().equals(ID)) {
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
        group.removeStudent(student);
    }
}
