/**
 * 
 */
package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import com.blackdeath.profesoresplatzi.model.SocialMedia;

/**
 * @author Seth Luis
 *
 */
public class SocialMediaDaoImp extends AbstractSession implements SocialMediaDao {

	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		getSession().persist(socialMedia);
	}

	@Override
	public void deleteSocialMediaById(Long idSocialMedia) {
		SocialMedia socialMedia = findById(idSocialMedia);

		if (socialMedia != null) {
			getSession().delete(socialMedia);
		}
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		getSession().update(socialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedia() {
		return getSession().createQuery("from SocialMedia", SocialMedia.class).list();
	}

	@Override
	public SocialMedia findById(Long idSocialMedia) {
		return getSession().get(SocialMedia.class, idSocialMedia);
	}

	@Override
	public SocialMedia findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
