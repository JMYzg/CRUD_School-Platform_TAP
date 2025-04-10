package com.tap.schoolplatform.controllers.studentControllers.studentDataViewController;

import com.tap.schoolplatform.auth.LoginService;
import com.tap.schoolplatform.controllers.ViewController;
import com.tap.schoolplatform.models.users.Student;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StudentDataViewController extends ViewController {
    public ImageView studentPhoto;
    public Label lastName;
    public Label name;
    public Label id;
    public Label degree;
    public Label semester;
    public Label group;
    public Label shift;
    public Label gender;
    public Label telephone;
    public Label email;
    public Label street;
    public Label colony;
    public Label pc;
    public Label city;
    public Label state;
    public Label country;

    Student currentStudent = (Student) LoginService.getCurrentUser();

    public void initialize() {
        studentPhoto.setImage(currentStudent.getProfilePicture());
        lastName.setText(currentStudent.getLastName());
        name.setText(currentStudent.getName());
        id.setText(currentStudent.getID());
        degree.setText(currentStudent.getDegree().toString());
        semester.setText(currentStudent.getGroup().getSemester().toString());
        group.setText(currentStudent.getGroup().toString());
        shift.setText(currentStudent.getGroup().getShift().toString());
        gender.setText(currentStudent.getGender().toString());
        telephone.setText(currentStudent.getPhone());
        email.setText(currentStudent.getEmail());
        street.setText(currentStudent.getStreet());
        colony.setText(currentStudent.getColony());
        pc.setText(Integer.toString(currentStudent.getPostalCode()));
        city.setText(currentStudent.getCity());
        state.setText(currentStudent.getState());
        country.setText(currentStudent.getCountry());

    }
}
