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
		if (idSocialMedia <= 0) {
			return ResponseEntity.noContent().build();
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
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return ResponseEntity.noContent().build();
		}

		if (socialMediaService.findByName(socialMedia.getName()) != null) {
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return ResponseEntity.noContent().build();
		}

		socialMediaService.saveSocialMedia(socialMedia);

		SocialMedia socialMedia2 = socialMediaService.findByName(socialMedia.getName());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/v1/socialMedias/{id}")
				.buildAndExpand(socialMedia2.getIdSocialMedia()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		// return
		// ResponseEntity.created(uriComponentsBuilder.path("/v1/socialMedias/{id}")
		// .buildAndExpand(socialMedia2.getIdSocialMedia()).toUri()).build();
	}
}
