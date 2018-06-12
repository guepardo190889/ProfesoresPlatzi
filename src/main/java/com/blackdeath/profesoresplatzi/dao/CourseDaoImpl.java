package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.Course;

/**
 * @author Seth Luis
 *
 */
public class CourseDaoImpl extends AbstractSession implements CourseDao {

	@Override
	public void saveCourse(Course course) {
		getSession().persist(course);
	}

	@Override
	public void deleteSocialMedia(Course course) {

	}

	@Override
	public void updateSocialMedia(Course course) {
		getSession().update(course);
	}

	@Override
	public List<Course> findAllCourse() {
		return getSession().createQuery("from Course", Course.class).list();
	}

}
