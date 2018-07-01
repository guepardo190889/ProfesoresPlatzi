/**
 * 
 */
package com.blackdeath.profesoresplatzi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.blackdeath.profesoresplatzi.model.SocialMedia;
import com.blackdeath.profesoresplatzi.model.TeacherSocialMedia;

/**
 * @author Seth Luis
 *
 */
@Repository
@Transactional
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
		return getSession().createQuery("from SocialMedia where name = :name", SocialMedia.class)
				.setParameter("name", name).uniqueResult();
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		// la colección de social medias en un índice
		// la colección de teachers en otro índice
		List<Object[]> objects = getSession().createQuery(
				"from TeacherSocialMedia tsm join tsm.socialMedia sm where sm.idSocialMedia = :idSocialMedia and tsm.nickname = :nickname")
				.setParameter("idSocialMedia", idSocialMedia).setParameter("nickname", nickname).list();

		if (objects.size() > 0) {
			for (Object[] o : objects) {
				for (Object o2 : o) {
					if (o2 instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) o2;
					}
				}
			}
		}

		return null;
	}

}
