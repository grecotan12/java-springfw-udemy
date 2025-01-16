package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			deleteInstructorDetail(appDAO);
		};
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
		int theId = 1;
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
