/**
 * 
 */
package com.blackdeath.profesoresplatzi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackdeath.profesoresplatzi.dao.SocialMediaDao;
import com.blackdeath.profesoresplatzi.model.SocialMedia;
import com.blackdeath.profesoresplatzi.model.TeacherSocialMedia;

/**
 * @author Seth Luis
 *
 */
@Service("socialMediService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private SocialMediaDao socialMediaDao;
	
	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		socialMediaDao.saveSocialMedia(socialMedia);
	}

	@Override
	public void deleteSocialMediaById(Long idSocialMedia) {
		socialMediaDao.deleteSocialMediaById(idSocialMedia);
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		socialMediaDao.updateSocialMedia(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedia() {
		return socialMediaDao.findAllSocialMedia();
	}

	@Override
	public SocialMedia findById(Long idSocialMedia) {
		return socialMediaDao.findById(idSocialMedia);
	}

	@Override
	public SocialMedia findByName(String name) {
		return socialMediaDao.findByName(name);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		return socialMediaDao.findSocialMediaByIdAndName(idSocialMedia, nickname);
	}

}
