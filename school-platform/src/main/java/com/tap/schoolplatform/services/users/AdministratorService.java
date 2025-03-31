package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.*;
import com.tap.schoolplatform.models.academic.enums.Shift;
import com.tap.schoolplatform.models.academic.keys.GroupKey;
import com.tap.schoolplatform.models.enums.UserRole;
import com.tap.schoolplatform.models.users.*;
import com.tap.schoolplatform.services.Service;
import com.tap.schoolplatform.services.academic.*;
import com.tap.schoolplatform.utils.*;

import java.util.UUID;

public class AdministratorService extends Service {

    private Degree degree;

    public AdministratorService(Degree degree) {
        this.degree = degree;
    }

    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    // User management
    public void createUser(User user, UserDTO userDTO) {
        if (user instanceof Teacher teacher) {
            DegreeService degreeService = new DegreeService(userDTO.getDegree());
            degreeService.createTeacher(teacher, userDTO.getLicense(), userDTO.getSpecialization());
        }

        if (user instanceof Student student) {
            GroupService groupService = new GroupService(userDTO.getGroup());
            groupService.createStudent(student);
        }

        UserRole role = user.getRole();
        if (sharedData.getUsers(role).contains(user)) {
            sharedData.getUsers(role).removeIf(u -> u.getId().equals(user.getId()));
        }
        sharedData.getUsers(role).add(user);
    }

    public User readUser(UserRole role, UUID id) {
        for (User user : sharedData.getUsers(role)) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user, UserDTO userDTO) { // can I do it better?
        if (userDTO.getPassword() != null) user.setPassword(userDTO.getPassword());
        if (userDTO.getRole() != null) user.setRole(userDTO.getRole());
        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if (userDTO.getBirthDate() != null) user.setBirthDate(userDTO.getBirthDate());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());
        if (userDTO.getAddress() != null) user.setAddress(userDTO.getAddress());
        if (userDTO.getGender() != null) user.setGender(userDTO.getGender());

        if (user instanceof Teacher teacher) {
            DegreeService degreeService = new DegreeService(teacher.getDegree());
            degreeService.updateTeacher(teacher, userDTO);
        }

        if (user instanceof Student student) {
            GroupService groupService = new GroupService(student.getGroup());
            groupService.updateStudent(student, userDTO);
        }

        UserRole role = user.getRole();
        if (sharedData.getUsers(role).contains(user)) {
            sharedData.getUsers(role).removeIf(u -> u.getId().equals(user.getId()));
        }
        sharedData.getUsers(role).add(user);
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
        sharedData.getUsers(user.getRole()).removeIf(u -> u.getId().equals(user.getId()));
    }

    // Degrees management
    public void createDegree(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Degree name cannot be null or blank");
        }
        if (readDegree(name) != null) {
            throw new IllegalArgumentException("Degree already exists"); // create custom exception: DuplicateEntryException
        }
        Degree degree = new Degree(name);
        sharedData.getDegrees().add(degree);
    }

    public Degree readDegree(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Degree name cannot be null or blank");
        }
        for (Degree degree : sharedData.getDegrees()) {
            if (degree.getName().equals(name)) {
                return degree;
            }
        }
        return null;
    }

    public void updateDegree(String newName) {
        for (Degree d : sharedData.getDegrees()) {
            if (d.equals(degree)) {
                degree.setName(newName);
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

    public Group readGroup(GroupKey key, String id) {
        for (Group group : degree.getGroupList(key)) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public void updateGroup(Group group, GroupDTO groupDTO) {
        DegreeService degreeService = new DegreeService(group.getDegree());
        degreeService.updateGroup(group, groupDTO);
    }

    public void deleteGroup(GroupKey key, String id) {
        Group group = readGroup(key, id);
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
        teacher.getAssignedSubjectSet().add(subject);
    }
}
