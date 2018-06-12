package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.SocialMedia;

/**
 * @author Seth Luis
 *
 */
public interface SocialMediaDao {
	void saveSocialMedia(SocialMedia socialMedia);

	void deleteSocialMedia(SocialMedia socialMedia);

	void updateSocialMedia(SocialMedia socialMedia);

	List<SocialMedia> findAllSocialMedia();
}
