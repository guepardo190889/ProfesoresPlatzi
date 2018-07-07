package com.blackdeath.profesoresplatzi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
