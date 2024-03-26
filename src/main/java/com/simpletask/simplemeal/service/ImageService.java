package com.simpletask.simplemeal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.model.Image;
import com.simpletask.simplemeal.model.WeeklyMenu;
import com.simpletask.simplemeal.repository.ImageRepository;
import com.simpletask.simplemeal.repository.WeeklyMenuRepository;

@Service
public class ImageService implements IImageService {
	
	@Autowired
	ImageRepository imageRepo;
	
	@Autowired
	WeeklyMenuRepository  weekRepo;
	
	public void createOrUpdateImage(Image image,boolean update) {
	    WeeklyMenu weeklyMenu = image.getWeeklyMenu();
	    Image existingImage= imageRepo.findByWeeklyMenu(weeklyMenu).orElse(null);
	    if (existingImage!=null && !update) {
	        throw new IllegalStateException("A menu can have only one image.");
	    }
	
	    imageRepo.save(image);
	}
	
	public Image getImageByDate(Date date) {
		WeeklyMenu menu = weekRepo.findByStartDate(date).orElse(null);
		Image image = imageRepo.findByWeeklyMenu(menu).orElse(null);
		return image;
	}

}
