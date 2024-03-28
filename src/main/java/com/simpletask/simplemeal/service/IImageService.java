package com.simpletask.simplemeal.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.simpletask.simplemeal.model.Image;
import com.simpletask.simplemeal.model.WeeklyMenu;

public interface IImageService {

	public WeeklyMenu addImage(MultipartFile file,Integer idMenu) throws IOException;
	public Image getImageByDate(Date date);
	Image getImageById(int id);
}
