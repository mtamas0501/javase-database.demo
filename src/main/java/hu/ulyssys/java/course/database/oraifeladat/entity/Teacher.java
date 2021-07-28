package hu.ulyssys.java.course.database.oraifeladat.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "teacher")
public class Teacher extends AbstractPerson implements Serializable {

    @Column(name = "courses_number")
    private int coursesNumber;

    public int getCoursesNumber() {
        return coursesNumber;
    }

    public void setCoursesNumber(int coursesNumber) {
        this.coursesNumber = coursesNumber;
    }
}