package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.blackdeath.profesoresplatzi.model.Course;

/**
 * @author Seth Luis
 *
 */
@Repository
@Transactional
public class CourseDaoImpl extends AbstractSession implements CourseDao {

	@Override
	public void saveCourse(Course course) {
		getSession().persist(course);
	}

	@Override
	public void deleteCourseById(Long idCourse) {
		Course course = findById(idCourse);

		if (course != null) {
			getSession().delete(course);
		}
	}

	@Override
	public void updateCourse(Course course) {
		getSession().update(course);
	}

	@Override
	public List<Course> findAllCourse() {
		return getSession().createQuery("from Course", Course.class).list();
	}

	@Override
	public Course findById(Long idCourse) {
		return getSession().get(Course.class, idCourse);
	}

	@Override
	public Course findByName(String name) {
		return getSession().createQuery("from Course where name = :name", Course.class).setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public List<Course> findByIdTeacher(Long idTeacher) {
		return getSession().createQuery("from Course c join c.teacher t where t.idTeacher = :idTeacher", Course.class)
				.setParameter("idTeacher", idTeacher).list();
	}

}
