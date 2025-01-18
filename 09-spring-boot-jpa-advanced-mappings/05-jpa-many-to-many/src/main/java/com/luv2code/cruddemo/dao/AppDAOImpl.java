package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    EntityManager em;

    @Autowired
    public AppDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        return em.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // retrieve the instructor
        Instructor instructor = em.find(Instructor.class, id);

        // get the courses
        List<Course> courses = instructor.getCourses();

        // break association of all courses for the instructor
        for (Course course : courses) {
            course.setInstructor(null);
        }

        // delete the instructor
        em.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return em.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = em.find(InstructorDetail.class, id);

        // remove the associated object reference
        // break bi-directional link
        //
        instructorDetail.getInstructor().setInstructorDetail(null);

        em.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // create query
        TypedQuery<Course> query = em.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        // execute and return
        return query.getResultList();
    }

    @Override
    // WORK WITH LAZY BUT WORK LIKE EAGER
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = em.createQuery(
                "select i from Instructor i" +
                        " JOIN FETCH i.courses" +
                        " JOIN FETCH i.instructorDetail" +
                        " WHERE i.id = :data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        em.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        em.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return em.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = em.find(Course.class, id);
        em.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        // create query
        TypedQuery<Course> query = em.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id = :data", Course.class
        );
        query.setParameter("data", id);
        // execute query
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        // create query
        TypedQuery<Course> query = em.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.students "
                + "where c.id = :data", Course.class
        );
        query.setParameter("data", id);

        // execute query
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        // create query
        TypedQuery<Student> query = em.createQuery(
                "select s from Student s "
                + "JOIN FETCH s.courses "
                + "where s.id = :data", Student.class
        );
        query.setParameter("data", id);

        // execute query
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        
        // retrieve student
        Student student = em.find(Student.class, id);
        // delete the student
        em.remove(student);
    }
}
