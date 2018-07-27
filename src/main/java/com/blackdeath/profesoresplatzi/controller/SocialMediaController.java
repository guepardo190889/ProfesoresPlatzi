package com.blackdeath.profesoresplatzi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.blackdeath.profesoresplatzi.model.SocialMedia;
import com.blackdeath.profesoresplatzi.service.SocialMediaService;
import com.blackdeath.profesoresplatzi.util.CustomErrorType;

/**
 * @author Seth Luis
 *
 */
@Controller
@RequestMapping("/v1")
public class SocialMediaController {

	@Autowired
	private SocialMediaService socialMediaService;

	// GET
	@RequestMapping(value = "/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias() {
		List<SocialMedia> socialMedias = new ArrayList<>();
		socialMedias = socialMediaService.findAllSocialMedia();

		if (socialMedias.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
	}

	// GET
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> getSocialMediaById(@PathVariable("id") Long idSocialMedia) {
		if (idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("idSocialMedia es requerido"), HttpStatus.CONFLICT);
		}

		SocialMedia socialMedia = socialMediaService.findById(idSocialMedia);

		if (socialMedia == null) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<SocialMedia>(socialMedia, HttpStatus.OK);
	}

	// POST
	@RequestMapping(value = "/socialMedias", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createSocialMedia(@RequestBody SocialMedia socialMedia,
			UriComponentsBuilder uriComponentsBuilder) {
		if (socialMedia.getName() == null || socialMedia.getName().isEmpty()) {
			return new ResponseEntity(new CustomErrorType("name es requerido"), HttpStatus.CONFLICT);
		}

		if (socialMediaService.findByName(socialMedia.getName()) != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		socialMediaService.saveSocialMedia(socialMedia);

		SocialMedia socialMedia2 = socialMediaService.findByName(socialMedia.getName());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/v1/socialMedias/{id}")
				.buildAndExpand(socialMedia2.getIdSocialMedia()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// UPDATE
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<SocialMedia> updateSocialMedia(@PathVariable("id") Long idSocialMedia,
			@RequestBody SocialMedia socialMedia) {
		if (idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("idSocialMedia es requerido"), HttpStatus.CONFLICT);
		}

		SocialMedia currentSocialMedia = socialMediaService.findById(idSocialMedia);

		if (currentSocialMedia == null) {
			return ResponseEntity.noContent().build();
		}

		currentSocialMedia.setName(socialMedia.getName());
		currentSocialMedia.setIcon(socialMedia.getIcon());

		socialMediaService.updateSocialMedia(currentSocialMedia);

		return new ResponseEntity<SocialMedia>(currentSocialMedia, HttpStatus.OK);
	}

	// DELETE
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSocialMedia(@PathVariable("id") Long idSocialMedia) {
		if (idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("idSocialMedia es requerido"), HttpStatus.CONFLICT);
		}

		SocialMedia socialMedia = socialMediaService.findById(idSocialMedia);

		if (socialMedia == null) {
			return ResponseEntity.noContent().build();
		}

		socialMediaService.deleteSocialMediaById(idSocialMedia);

		return new ResponseEntity<SocialMedia>(HttpStatus.OK);
	}

}
