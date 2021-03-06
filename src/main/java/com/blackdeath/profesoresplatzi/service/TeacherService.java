/**
 * 
 */
package com.blackdeath.profesoresplatzi.service;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.Teacher;

/**
 * @author Seth Luis
 *
 */
public interface TeacherService {
	void saveTeacher(Teacher teacher);

	void deleteTeacherById(Long idTeacher);

	void updateTeacher(Teacher teacher);

	List<Teacher> findAllTeachers();

	Teacher findById(Long idTeacher);

	Teacher findByName(String name);
}
