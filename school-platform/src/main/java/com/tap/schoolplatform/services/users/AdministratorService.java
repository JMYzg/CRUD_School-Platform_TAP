package com.tap.schoolplatform.services.users;

import com.tap.schoolplatform.models.academic.Degree;
import com.tap.schoolplatform.utils.SharedData;

public class AdministratorService {
    private final SharedData sharedData = SharedData.getInstance();

    // Degrees management
    public void createDegree(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Degree name cannot be null or empty");
        }
        if (readDegree(name) != null) {
            throw new IllegalArgumentException("Degree already exists"); // create custom exception: DuplicateEntryException
        }
        Degree degree = new Degree(name);
        sharedData.getDegrees().add(degree);
    }

    public Degree readDegree(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Degree name cannot be null or empty");
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
