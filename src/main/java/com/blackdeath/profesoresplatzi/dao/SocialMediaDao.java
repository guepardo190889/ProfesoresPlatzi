package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.SocialMedia;

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
}
