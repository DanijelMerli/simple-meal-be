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
	
	public WeeklyMenu addImage(MultipartFile file,Integer idMenu) throws IOException{
		WeeklyMenu menu = weekRepo.findById(idMenu).orElse(null);
		byte[] fileBytes = file.getBytes();
		Optional<Image> imageOpt = imageRepo.findByWeeklyMenu(menu);
		Image image = imageOpt.orElseGet(Image::new);
		image.setData(fileBytes);
		image.setWeeklyMenu(menu);
		imageRepo.saveAndFlush(image);
		return menu;
}
	
	
	public Image getImageByDate(Date date) {
		WeeklyMenu menu = weekRepo.findByStartDate(date).orElse(null);
		Image image = imageRepo.findByWeeklyMenu(menu).orElse(null);
		return image;
	}

	public Image getImageById(int id) {
		WeeklyMenu menu = weekRepo.findById(id).orElse(null);
		return imageRepo.findByWeeklyMenu(menu).orElse(null);
	}

}
