package hu.ulyssys.java.course.database.oraifeladat;
import hu.ulyssys.java.course.database.oraifeladat.entity.Student;
import hu.ulyssys.java.course.database.oraifeladat.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
public class HibernateMainForStudentAndTeacher {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFirstName("Tamás");
        student.setLastName("Márta");
        student.setFriendsNumber(5);
        insertStudent(student);

        Teacher teacher = new Teacher();
        teacher.setFirstName("Tamás");
        teacher.setLastName("Márta");
        teacher.setCoursesNumber(5);
        insertTeacher(teacher);

        Long id = insertTeacher(teacher);

        findAllStudent().forEach( person -> {
            System.out.println(person.getId()+". hallgató "+person.getFirstName()+" "+person.getLastName());
        });
        findAllTeacher().forEach( teacher1 -> {
            System.out.println(teacher1.getId()+". tanár "+teacher1.getFirstName()+" "+teacher1.getLastName());
        });
        findByIdStudent(id);
        findByIdTeacher(id);
    }

    private static List<Student> findAllStudent() {
        Session session = DataBaseSessionProvider.getInstance().getSessionObj();
        Query<Student> query = session.createQuery("select n from Student n",Student.class);
        return query.getResultList();
    }

    private static List<Teacher> findAllTeacher() {
        Session session = DataBaseSessionProvider.getInstance().getSessionObj();
        Query<Teacher> query = session.createQuery("select n from Teacher n",Teacher.class);
        return query.getResultList();
    }


    private static Student findByIdStudent(Long id) {
        Session session = DataBaseSessionProvider.getInstance().getSessionObj();
        return session.find(Student.class, id);
    }

    private static Teacher findByIdTeacher(Long id) {
        Session session = DataBaseSessionProvider.getInstance().getSessionObj();
        return session.find(Teacher.class, id);
    }

    private static Long insertTeacher(Teacher teacher) {
        Session session = DataBaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        return teacher.getId();
    }

    private static Long insertStudent(Student student) {
        Session session = DataBaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student.getId();
    }


}
