package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.enums.Gender;
import com.tap.schoolplatform.models.enums.Status;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.shared.Address;
import com.tap.schoolplatform.models.shared.BirthDate;
import com.tap.schoolplatform.models.users.*;
import com.tap.schoolplatform.services.Service;
import com.tap.schoolplatform.services.academic.*;
import com.tap.schoolplatform.utils.dtos.UserDTO;
import com.tap.schoolplatform.utils.dtos.academic.GroupDTO;
import com.tap.schoolplatform.utils.dtos.academic.SubjectDTO;

import java.util.UUID;

public class AdministratorService extends Service {

    private Degree degree;

    public AdministratorService(Degree degree) {
        this.degree = degree;
    }

    public AdministratorService() {}

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    // User management
    public void createUser(User user, UserDTO userDTO) { // pending
        if (user instanceof Teacher teacher) {
            if (teacher.getRole() == null) teacher.setRole(UserRole.TEACHER);
            DegreeService degreeService = new DegreeService(degree);
            degreeService.createTeacher(teacher, userDTO);
        }
        if (user instanceof Student student) {
            if (student.getRole() == null) student.setRole(UserRole.STUDENT);
            student.setStatus(Status.ACTIVE);
            GroupService groupService = new GroupService(userDTO.getGroup());
            groupService.createStudent(student);
        }

        UserRole role = user.getRole();
        if (sharedData.getUsers(role).contains(user)) {
            sharedData.getUsers(role).removeIf(u -> u.getUUID().equals(user.getUUID()));
        }
        sharedData.getUsers(role).add(user);
    }

    public void createStudent(Group group, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender) {
        Student student = new Student(name, lastName, birthDate, email, phone, address, gender);
        GroupService groupService = new GroupService(group);
        groupService.createStudent(student);
    }

    public void createTeacher (Degree degree, String name, String lastName, BirthDate birthDate, String email, String phone, Address address, Gender gender, String license, String specialization) {
        Teacher teacher = new Teacher(name, lastName, birthDate, email, phone, address, gender);
        DegreeService degreeService = new DegreeService(degree);
        degreeService.createTeacher(teacher, license, specialization);
    }

    public User readUser(UserRole role, UUID ID) {
        for (User user : sharedData.getUsers(role)) {
            if (user.getUUID().equals(ID)) {
                return user;
            }
        }
        return null;
    }

    public User readUser(UserRole role, String ID) { // Wait, GroupService does this already
//        for (User user : sharedData.getUsers(role)) {
//            if (role.equals(UserRole.STUDENT) && user instanceof Student student) {
//                if (student.getID().equals(ID)) {
//                    return user;
//                }
//            } else if (role.equals(UserRole.TEACHER) && user instanceof Teacher teacher) {
//                if (teacher.getLicense().equals(ID)) {
//                    return user;
//                }
//            }
//        }
//        return null;
        if (role == UserRole.TEACHER) {
            for (Teacher teacher : sharedData.getTeachers()) {
                if (teacher.getLicense().equals(ID)) {
                    return teacher;
                }
            }
        } else {
            for (Student student : sharedData.getStudents()) {
                if (student.getID().equals(ID)) {
                    return student;
                }
            }
        }
        return null;
    }

    public void updateUser(User user, UserDTO userDTO) { // can I do it better?
        if (userDTO.getPassword() != null) user.setPassword(userDTO.getPassword().trim());
        if (userDTO.getRole() != null) user.setRole(userDTO.getRole());
        if (userDTO.getName() != null) user.setName(userDTO.getName().trim());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName().trim());
        if (userDTO.getBirthDate() != null) user.setBirthDate(userDTO.getBirthDate());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail().trim());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone().trim());
        if (userDTO.getAddress() != null) user.setAddress(userDTO.getAddress());
        if (userDTO.getGender() != null) user.setGender(userDTO.getGender());

        if (user instanceof Teacher teacher) {
            DegreeService degreeService = new DegreeService(degree);
            degreeService.updateTeacher(teacher, userDTO);
        }

        if (user instanceof Student student) {
            GroupService groupService = new GroupService(userDTO.getGroup());
            groupService.updateStudent(student, userDTO);
        }

        UserRole role = user.getRole();
//        if (sharedData.getUsers(role).contains(user)) {
//            sharedData.getUsers(role).removeIf(u -> u.getUUID().equals(user.getUUID()));
//        }
//        sharedData.getUsers(role).add(user);
    }

    public void deleteUser(User user) {
        if (user instanceof Teacher teacher) {
            DegreeService degreeService = new DegreeService(teacher.getDegree());
            degreeService.deleteTeacher(teacher);
        }

        if (user instanceof Student student) {
            GroupService groupService = new GroupService(student.getGroup());
            groupService.deleteStudent(student);
        }
        sharedData.getUsers(user.getRole()).removeIf(u -> u.getUUID().equals(user.getUUID()));
    }

    // Degrees management
    public void createDegree(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Degree name cannot be null or blank");
        }
        if (readDegree(name) != null) {
            throw new IllegalArgumentException("Degree already exists"); // create custom exception: DuplicateEntryException
        }
        Degree degree = new Degree(name.trim());
        sharedData.getDegrees().add(degree);
    }

    public Degree readDegree(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Degree name cannot be null or blank");
        }
        for (Degree degree : sharedData.getDegrees()) {
            if (degree.getName().equals(name.trim())) {
                return degree;
            }
        }
        return null;
    }

    public void updateDegree(String name) {
        for (Degree degree : sharedData.getDegrees()) {
            if (degree == this.degree) {
                this.degree.setName(name.trim());
                break;
            }
        }
    }

    public void deleteDegree() {
        sharedData.getDegrees().remove(degree);
    }

    // Group management
    public void createGroup(int semester, Shift shift) {
        DegreeService degreeService = new DegreeService(degree);
        degreeService.createGroup(semester, shift);
    }

    public Group readGroup(int semester, String ID) {
        for (Group group : degree.getGroupList(semester)) {
            if (group.getID().equals(ID)) {
                return group;
            }
        }
        return null;
    }

    public void updateGroup(Group group, GroupDTO groupDTO) {
        DegreeService degreeService = new DegreeService(group.getDegree());
        degreeService.updateGroup(group, groupDTO);
    }

    public void deleteGroup(Group group) {
        DegreeService degreeService = new DegreeService(degree);
        degreeService.deleteGroup(group);
    }

    // Subject management
    public void createSubject(int semester, String name, String description) {
        DegreeService degreeService = new DegreeService(degree);
        degreeService.createSubject(semester, name, description);
    }

    public Subject readSubject(int semester, String name) {
        for (Subject subject : degree.getSubjectList(semester)) {
            if (subject.getName().equals(name)) {
                return subject;
            }
        }
        return null;
    }

    public void updateSubject(Subject subject, SubjectDTO subjectDTO) {
        DegreeService degreeService = new DegreeService(subject.getDegree());
        degreeService.updateSubject(subject, subjectDTO);
    }

    public void deleteSubject(int semester, String name) {
        Subject subject = readSubject(semester, name);
        DegreeService degreeService = new DegreeService(degree);
        degreeService.deleteSubject(subject);
    }

    public void assignSubject(Teacher teacher, Subject subject) {
        teacher.assignSubject(subject);
    }
}
