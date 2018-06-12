package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.Course;

/**
 * @author Seth Luis
 *
 */
public interface CourseDao {
	void saveCourse(Course course);

	void deleteSocialMedia(Course course);

	void updateSocialMedia(Course course);

	List<Course> findAllCourse();
}
