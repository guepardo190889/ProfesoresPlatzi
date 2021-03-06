package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.SocialMedia;
import com.blackdeath.profesoresplatzi.model.TeacherSocialMedia;

/**
 * @author Seth Luis
 *
 */
public interface SocialMediaDao {
	void saveSocialMedia(SocialMedia socialMedia);

	void deleteSocialMediaById(Long idSocialMedia);

	void updateSocialMedia(SocialMedia socialMedia);

	List<SocialMedia> findAllSocialMedia();

	SocialMedia findById(Long idSocialMedia);

	SocialMedia findByName(String name);

	TeacherSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname);
}
