package com.simpletask.simplemeal.service;

import java.util.Date;

import com.simpletask.simplemeal.model.Image;

public interface IImageService {

	public void createOrUpdateImage(Image image,boolean update);
	public Image getImageByDate(Date date);
}
