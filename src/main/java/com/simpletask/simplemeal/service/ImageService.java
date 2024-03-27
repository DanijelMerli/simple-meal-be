package com.simpletask.simplemeal.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public WeeklyMenu addImage(MultipartFile file,Integer idMenu) {
		Optional<WeeklyMenu> weekMenuOptional = weekRepo.findById(idMenu);
		if (!weekMenuOptional.isPresent()) 
			return null;
		WeeklyMenu weekMenue = weekMenuOptional.get();
		 try {
	            WeeklyMenu weekMenu = weekMenuOptional.get();
	            byte[] fileBytes = file.getBytes();
	            Image image = new Image();
	            image.setData(fileBytes);
	            image.setWeeklyMenu(weekMenu);
	            imageRepo.save(image);
	            weekMenu.setImage(image);
	            return weekRepo.save(weekMenu);
	        } catch (IOException e) {
	            return null;
	        }
	}
	
	
	public Image getImageByDate(Date date) {
		WeeklyMenu menu = weekRepo.findByStartDate(date).orElse(null);
		Image image = imageRepo.findByWeeklyMenu(menu).orElse(null);
		return image;
	}

}
