/**
 * 
 */
package com.blackdeath.profesoresplatzi.service;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.Course;

/**
 * @author Seth Luis
 *
 */
public interface CourseService {
	void saveCourse(Course course);

	void deleteCourseById(Long idCourse);

	void updateCourse(Course course);

	List<Course> findAllCourse();

	Course findById(Long idCourse);

	Course findByName(String name);

	List<Course> findByIdTeacher(Long idTeacher);
}
