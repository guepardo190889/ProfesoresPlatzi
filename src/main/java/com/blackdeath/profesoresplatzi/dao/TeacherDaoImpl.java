package com.blackdeath.profesoresplatzi.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.blackdeath.profesoresplatzi.model.Teacher;
import com.blackdeath.profesoresplatzi.model.TeacherSocialMedia;

/**
 * @author Seth Luis
 *
 */
@Repository
@Transactional
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {

	public TeacherDaoImpl() {
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		getSession().persist(teacher);
	}

	@Override
	public void deleteTeacherById(Long idTeacher) {
		Teacher teacher = (Teacher) findById(idTeacher);

		if (teacher != null) {
			Iterator<TeacherSocialMedia> i = teacher.getTeacherSocialMedias().iterator();
			
			while(i.hasNext()) {
				TeacherSocialMedia teacherSocialMedia = i.next();
				i.remove();
				getSession().delete(teacherSocialMedia);
			}
			
			getSession().delete(teacher);
		}
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		getSession().update(teacher);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		return getSession().createQuery("from Teacher", Teacher.class).list();
	}

	@Override
	public Teacher findById(Long idTeacher) {
		return getSession().get(Teacher.class, idTeacher);
	}

	@Override
	public Teacher findByName(String name) {
		return (Teacher) getSession().createQuery("from Teacher where name = :name").setParameter("name", name)
				.uniqueResult();
	}

}
