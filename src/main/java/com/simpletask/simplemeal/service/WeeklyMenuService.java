package com.simpletask.simplemeal.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpletask.simplemeal.dto.DailyMenuAdminDTO;
import com.simpletask.simplemeal.dto.DailyMenuDTO;
import com.simpletask.simplemeal.dto.WeeklyMenuAdminDTO;
import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.model.DailyMenu;
import com.simpletask.simplemeal.model.Extra;
import com.simpletask.simplemeal.model.FitMeal;
import com.simpletask.simplemeal.model.Image;
import com.simpletask.simplemeal.model.RegularMeal;
import com.simpletask.simplemeal.model.WeeklyMenu;
import com.simpletask.simplemeal.repository.DailyMenuRepository;
import com.simpletask.simplemeal.repository.ExtraRepository;
import com.simpletask.simplemeal.repository.FitMealRepository;
import com.simpletask.simplemeal.repository.ImageRepository;
import com.simpletask.simplemeal.repository.RegularMealRepository;
import com.simpletask.simplemeal.repository.WeeklyMenuRepository;

import jakarta.validation.Valid;

@Service
public class WeeklyMenuService {

	@Autowired
	WeeklyMenuRepository weekRepo;
	
	@Autowired
	RegularMealRepository regMealRepo;
	
	@Autowired
	FitMealRepository fitMealRepo;
	
	@Autowired 
	ExtraRepository extraMealRepo;
	
	@Autowired
	DailyMenuRepository dailyMenuRepo;
	
	@Autowired
	WeeklyMenuRepository weekMenuRepo;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	
	
	
	 public WeeklyMenuDTO getWeeklyMenuByStartDate() {
		 Date startOfWeek = getStartOfWeek();
		 Date endOfWeek = getEndOfWeek(startOfWeek);
//		 System.out.println(startOfWeek);
//		 System.out.println(endOfWeek);
	     Optional<WeeklyMenu> optionalWeeklyMenu = weekRepo.findByStartDateBetween(startOfWeek, endOfWeek);
		 return optionalWeeklyMenu.map(WeeklyMenuDTO::new).orElse(null);
	 }

	public static Date getStartOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndOfWeek(Date start) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		return calendar.getTime();
	}
	
	  public  boolean fromTheFuture(String dateString)  throws ParseException{
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Date providedDate;
	        
	        providedDate = dateFormat.parse(dateString);
	        Date currentDate = new Date();
	        return providedDate.after(currentDate);

	    }
	
	public  WeeklyMenu saveWeeklyMenu(WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException {
		SimpleDateFormat dateParse = new SimpleDateFormat("dd-MM-yyyy.");
		String dateString = weeklyMenuDTO.getStartDate();
		if (dateString==null) 
			throw new IllegalArgumentException("Date must be provided");
		if (!fromTheFuture(dateString)) 
			throw new IllegalArgumentException("Date must be after today");
		
		return saveWeeklyMenuAuxiliary(dateParse, weeklyMenuDTO);
		
		
	}
	
	public WeeklyMenu getWeeklyMenu(SimpleDateFormat dateParse, WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException {
		Date date = dateParse.parse(weeklyMenuDTO.getStartDate());
		Date endDate = getEndOfWeek(date);
		Optional<WeeklyMenu> weeklyMenuOptional = weekRepo.findByStartDate(date);
		if (weeklyMenuOptional.isPresent()) {
		WeeklyMenu weeklyMenu = weeklyMenuOptional.get();
		if (weeklyMenu==null) {
			weeklyMenu = new WeeklyMenu();
			weeklyMenu.setStartDate(date);
		}
			return weeklyMenu;}
		else {
			return null;
		}
	}
	
	public WeeklyMenu saveWeeklyMenuAuxiliary(SimpleDateFormat dateParse, WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException {
		WeeklyMenu weeklyMenu = getWeeklyMenu(dateParse,weeklyMenuDTO);
		if (weeklyMenu==null)
			return null;
		
		List<DailyMenu> dm = new ArrayList<>();
		for (DailyMenuAdminDTO dailyMenuDTO: weeklyMenuDTO.getDailyMenus() ) {
			Date dailyMenuDate = dateParse.parse(dailyMenuDTO.getDateMenu());
			DailyMenu dailyMenu = new DailyMenu();
			dailyMenu.setDate(dailyMenuDate);
			if (dailyMenuDTO.getRegularMealId()!=null)  {
				if (dailyMenuDTO.getFitMealId()==null)
					throw new IllegalArgumentException("Regular meal cannot be added without a fit meal.");
			
			Optional<RegularMeal> regMealOptional = regMealRepo.findById(dailyMenuDTO.getRegularMealId());
			regMealOptional.ifPresent(regular-> dailyMenu.setRegular(regular));
			}
			
			if (dailyMenuDTO.getFitMealId()!=null) {
				if (dailyMenuDTO.getRegularMealId()==null) 
					throw new IllegalArgumentException("Fit meal cannot be added without a regular meal.");
				
				Optional<FitMeal> fitMealOptional = fitMealRepo.findById(dailyMenuDTO.getFitMealId());
				fitMealOptional.ifPresent(fit -> dailyMenu.setFit(fit));
				}
			
			if (dailyMenu.getSoup()!=null) {
				if ((dailyMenuDTO.getFitMealId()==null ||  dailyMenuDTO.getRegularMealId()==null))
					throw new IllegalArgumentException("Extra meal cannot be added without a regular or a fit  meal.");
					
				Optional<Extra> soupOptional = extraMealRepo.findById(dailyMenuDTO.getSoupId());
				soupOptional.ifPresent(soup -> dailyMenu.setSoup(soup));
				
				}
			
			if (dailyMenu.getDessert()!=null) {
				if ((dailyMenuDTO.getFitMealId()==null || dailyMenuDTO.getRegularMealId()==null))
					throw new IllegalArgumentException("Extra meal cannot be added without a regular or a fit  meal.");
				Optional<Extra> dessertOptional = extraMealRepo.findById(dailyMenuDTO.getDessertId());
				dessertOptional.ifPresent(dessert -> dailyMenu.setDessert(dessert));
				
				
			}
			dailyMenuRepo.save(dailyMenu);
			
			dm.add(dailyMenu);
			}
		
			weeklyMenu.setDailyMenu(dm);
			weekMenuRepo.save(weeklyMenu);
			Image image = new Image();
			image.setWeeklyMenu(weeklyMenu);
			
			if (weeklyMenuDTO.getImageData()==null)
				throw new IllegalArgumentException("Image must be provided");
			
			image.setData(weeklyMenuDTO.getImageData());
			imageService.createOrUpdateImage(image,false);
			weeklyMenu.setImage(image);
			return weeklyMenu;
	}

	public WeeklyMenu updateWeeklyMenu(@Valid WeeklyMenuAdminDTO weeklyMenuDTO) throws Exception{
		SimpleDateFormat dateParse = new SimpleDateFormat("dd-MM-yyyy.");
		Date date = dateParse.parse(weeklyMenuDTO.getStartDate());	
		
		Optional<WeeklyMenu> weekMenuOptional = weekRepo.findByStartDate(date);
		if (!weekMenuOptional.isPresent())
			throw new  IllegalArgumentException("Menu doesn't exist");
		
		WeeklyMenu weekMenu = weekMenuOptional.get();
		
		for (DailyMenuAdminDTO dailyMenuDTO: weeklyMenuDTO.getDailyMenus()) {
			Date dateDaily = dateParse.parse(dailyMenuDTO.getDateMenu());
			Optional<DailyMenu> dailyMenuOptional = dailyMenuRepo.findByDateMenu(dateDaily);
			if (dailyMenuOptional.isPresent()) {
				DailyMenu dailyMenu = dailyMenuOptional.get();
				if (dailyMenuDTO.getRegularMealId()!=null)  {
					if (dailyMenuDTO.getFitMealId()==null && dailyMenu.getFit()==null)
						throw new IllegalArgumentException("Regular meal cannot be added without a fit meal.");
				
				Optional<RegularMeal> regMealOptional = regMealRepo.findById(dailyMenuDTO.getRegularMealId());
				regMealOptional.ifPresent(regular-> dailyMenu.setRegular(regular));
				}
				
				if (dailyMenuDTO.getFitMealId()!=null)  {
					if (dailyMenuDTO.getRegularMealId()==null && dailyMenu.getRegular()==null)
						throw new IllegalArgumentException("Fit meal cannot be added without a fit meal.");
				
				Optional<FitMeal> fitMealOptional = fitMealRepo.findById(dailyMenuDTO.getFitMealId());
				fitMealOptional.ifPresent(fit-> dailyMenu.setFit(fit));
				}
				
				if (dailyMenuDTO.getSoupId()!=null) {
					if (dailyMenu.getFit()==null && dailyMenu.getRegular()==null)
						throw new IllegalArgumentException("Extra meal cannot be added without a regular or a fit  meal.");
				
				Optional<Extra> extraMealOptional = extraMealRepo.findById(dailyMenuDTO.getSoupId());
				extraMealOptional.ifPresent(soup -> dailyMenu.setSoup(soup));
			}
				
				if (dailyMenuDTO.getDessertId() != null) {
					
					if (dailyMenu.getFit()==null && dailyMenu.getRegular()==null)
						throw new IllegalArgumentException("Extra meal cannot be added without a regular or a fit  meal.");
				
				Optional<Extra> extraMealOptional = extraMealRepo.findById(dailyMenuDTO.getDessertId());
				extraMealOptional.ifPresent(dessert -> dailyMenu.setDessert(dessert));
			}
				if (weeklyMenuDTO.getImageData()!=null) {
				Image image = new Image();
				image.setWeeklyMenu(weekMenu);
			
				image.setData(weeklyMenuDTO.getImageData());
				imageService.createOrUpdateImage(image,true);
				weekMenu.setImage(image);
				}
		}		
	}

		return weekMenu;
}
}
	
	