package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import com.luv2code.cruddemo.entity.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createCourseAndStudents(appDAO);

			//findCourseAndStudents(appDAO);

			//findStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting student id: " + id);
		appDAO.deleteStudentById(id);

		System.out.println("DONE!");
	}


	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);

		// create more courses
		Course course1 = new Course("Test 1");
		Course course2 = new Course("Test 2");

		// add courses
		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("UPDATING student: "+ student);
		System.out.println("Associated coursese: " + student.getCourses());

		appDAO.update(student);

		System.out.println("DONE!");
	} 

	private void findStudentAndCourses(AppDAO appDAO) {
		int id = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);

		System.out.println("Loaded student: " + student);
		System.out.println("Courses are: " + student.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Loaded course: " + course);
		System.out.println("Students are: " + course.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course
		Course course = new Course("Course 1");

		// create the students
		Student student = new Student("John", "Doe", "johndoe@gmail.com");
		Student student2 = new Student("John", "Toe", "johntoe@gmail.com");

		// add students to the course
		course.addStudent(student);
		course.addStudent(student2);

		// save the course and associated students
		System.out.println("Saving the course: " + course);
		System.out.println("Associated students: " + course.getStudents());

		appDAO.save(course);

		System.out.println("Done!");
	}

	private void deleteCourseAndReview(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Delete course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(theId);
		// print the course
		System.out.println(course);
		// print the reviews
		System.out.println(course.getReviews());
		System.out.println("Done");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course course = new Course("PACMAN - GAME LEARN");
		// add some reviews
		course.addReview(new Review("NICE COURSE BRAH"));
		course.addReview(new Review("NICE PACMAN"));
		course.addReview(new Review("COOL COURSE BRAH"));
		// save the course .. and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());

		appDAO.save(course);
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("DONE!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 14;
		System.out.println("Updating course ID: " + theId);
		Course course = appDAO.findCourseById(theId);
		System.out.println("Updating course name: " + course);
		course.setTitle("javascript");
		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding instructor ID: " + theId);
		Instructor instructor = appDAO.findById(theId);

		System.out.println("Updating the instructor name");
		instructor.setLastName("Tester");
		appDAO.update(instructor);
		System.out.println("DONE!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 3;

		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");

	}

	// FetchType.LAZY
	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding instructor id : " + theId);

		Instructor instructor = appDAO.findById(theId);

		System.out.println("instructor: " + instructor);

		// find courses
		System.out.println("Finding courses by instructor id : " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// Add asscociated courses
		instructor.setCourses(courses);

		System.out.println("The associated courses are: " + instructor.getCourses());
	}

	// FetchType.EAGER
	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding instructor id : " + theId);

		Instructor instructor = appDAO.findById(theId);

		System.out.println("instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor =
				new Instructor("Susan", "nguyen", "lmao@abc.com");

		// create instructor detail
		InstructorDetail instructorDetail =
				new InstructorDetail("youtube.com", "video game");

		instructor.setInstructorDetail(instructorDetail);

		// create some coursees
		Course course1 = new Course("guitar");
		Course course2 = new Course("java");

		// add courses to instructor
		instructor.add(course1);
		instructor.add(course2);

		// save the instructor
		//
 		// NOTE: this will ALSO SAVE the courses
		// because of CascadeType.PERSIS
		System.out.println("Saving instructor " + instructor);
		System.out.println("The courses: " + instructor.getCourses());

		appDAO.save(instructor);

		System.out.println("DONE!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);
		appDAO.deleteInstructorDetailById(theId);
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
		int theId = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);
		// print the instructor detail
		System.out.println("Find the instructor detail " + instructorDetail);
		// print the associated instructor
		System.out.println("Instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);

		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor instructor = appDAO.findById(theId);
		System.out.println("Instructor: " + instructor);
		System.out.println("the associated detailed: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create instructor
//		Instructor instructor =
//				new Instructor("Tan", "nguyen", "test@abc.com");
//
//		// create instructor detail
//		InstructorDetail instructorDetail =
//				new InstructorDetail("youtube.com", "love to code");
		Instructor instructor =
				new Instructor("Thu", "nguyen", "test2@abc.com");

		// create instructor detail
		InstructorDetail instructorDetail =
				new InstructorDetail("youtube2.com", "love to shopping");

		// associate objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor
		// NOTE: this will ALSO save the detail object
		// because of cascade type.all
		System.out.println("Saving instructor" + instructor);
		appDAO.save(instructor);
		System.out.println("Done");
	}
}
