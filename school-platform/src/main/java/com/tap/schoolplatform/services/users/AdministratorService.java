package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.models.users.Student;
import com.tap.schoolplatform.models.users.Teacher;
import com.tap.schoolplatform.models.users.User;
import com.tap.schoolplatform.services.academic.DegreeService;
import com.tap.schoolplatform.utils.SharedData;
import com.tap.schoolplatform.utils.UserDTO;

public class AdministratorService {
    private final SharedData sharedData = SharedData.getInstance();

    // User management

    public void updateUser(Degree degree, User user, UserDTO userDTO) {

        if (userDTO.getPassword() != null) userDTO.setPassword(user.getPassword());
        if (userDTO.getRole() != null) userDTO.setRole(user.getRole());
        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if (userDTO.getBirthDate() != null) user.setBirthDate(userDTO.getBirthDate());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());
        if (userDTO.getAddress() != null) user.setAddress(userDTO.getAddress());
        if (userDTO.getGender() != null) user.setGender(userDTO.getGender());

        if (user instanceof Teacher teacher) {
            DegreeService degreeService = new DegreeService(degree);
            degreeService.updateTeacher(teacher, userDTO);
        }

        if (user instanceof Student student) {
            if (userDTO.getStatus() != null) student.setStatus(userDTO.getStatus());
        }

        sharedData.getUsers().add(user);
    }

    public void registerTeacher(Teacher User) {

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

    public void updateDegree(Degree degree, String newName) {
        for (Degree d : sharedData.getDegrees()) {
            if (d.equals(degree)) {
                degree.setName(newName);
                break;
            }
        }
    }

    public void deleteDegree(Degree degree) {
        sharedData.getDegrees().remove(degree);
    }
}
