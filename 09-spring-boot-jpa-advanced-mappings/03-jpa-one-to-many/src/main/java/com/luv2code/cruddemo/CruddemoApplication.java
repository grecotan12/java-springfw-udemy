package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
			//createInstructor(appDAO);

			//findInstructor(appDAO);

			//deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);

			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourses(appDAO);

			//findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);

			// updateInstructor(appDAO);

			//updateCourse(appDAO);

			//deleteInstructor(appDAO);

			deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 14;

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
