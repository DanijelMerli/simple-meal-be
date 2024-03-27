package com.simpletask.simplemeal.service;



import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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
	
	public  boolean isMondayOrNextMonday(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Date providedDate;
	        
	    providedDate = dateFormat.parse(dateString);		
        Date currentMonday = getStartOfWeek();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentMonday);
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        Date nextMonday = calendar.getTime();

        return providedDate.equals(currentMonday) || providedDate.equals(nextMonday);
    }


	public static Date getEndOfWeek(Date start) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		return calendar.getTime();
	}
	
	
	public  WeeklyMenu saveWeeklyMenu(WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException, IOException {
		SimpleDateFormat dateParse = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = weeklyMenuDTO.getStartDate(); 
		
		
		if (dateString==null) 
			throw new IllegalArgumentException("Date must be provided");
		if (!isMondayOrNextMonday(dateString)) 
			throw new IllegalArgumentException("Date must be after today");
		
		return saveWeeklyMenuAuxiliary(dateParse, weeklyMenuDTO);
		
		
	}
	
	public WeeklyMenu getWeeklyMenu(SimpleDateFormat dateParse, WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException {
		Date date = dateParse.parse(weeklyMenuDTO.getStartDate());
		
		Optional<WeeklyMenu> weeklyMenuOptional = weekRepo.findByStartDate(date);
		WeeklyMenu weeklyMenu = null;
		if (!weeklyMenuOptional.isEmpty()) 
			weeklyMenu = weeklyMenuOptional.get();
		  
		return weeklyMenu;
		
	}
		 
			
		
	
	
	public WeeklyMenu saveWeeklyMenuAuxiliary(SimpleDateFormat dateParse, WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException, IOException {
		WeeklyMenu weeklyMenu = getWeeklyMenu(dateParse,weeklyMenuDTO);
		
		if (weeklyMenu!=null) 
			return null;
		
		
		weeklyMenu = new WeeklyMenu();
		
		Date date =null;
		if (weeklyMenuDTO!=null && weeklyMenuDTO.getStartDate()!=null)
			 date = dateParse.parse(weeklyMenuDTO.getStartDate());
		
		weeklyMenu.setStartDate(date);
		
		weekMenuRepo.save(weeklyMenu);
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
			
			if (dailyMenuDTO.getSoupId()!=null) {
				if ((dailyMenuDTO.getFitMealId()==null ||  dailyMenuDTO.getRegularMealId()==null))
					throw new IllegalArgumentException("Extra meal cannot be added without a regular or a fit  meal.");
					
				Optional<Extra> soupOptional = extraMealRepo.findById(dailyMenuDTO.getSoupId());
				soupOptional.ifPresent(soup -> dailyMenu.setSoup(soup));
				
				}
			
			if (dailyMenuDTO.getDessertId()!=null) {
				if ((dailyMenuDTO.getFitMealId()==null || dailyMenuDTO.getRegularMealId()==null))
					throw new IllegalArgumentException("Extra meal cannot be added without a regular or a fit  meal.");
				Optional<Extra> dessertOptional = extraMealRepo.findById(dailyMenuDTO.getDessertId());
				dessertOptional.ifPresent(dessert -> dailyMenu.setDessert(dessert));
				
				
			}
			dailyMenu.setWeeklyMenu(weeklyMenu);
			dailyMenuRepo.save(dailyMenu);
			
			dm.add(dailyMenu);
			}
		
			weeklyMenu.setDailyMenu(dm);
			weekMenuRepo.save(weeklyMenu);
			
			return weeklyMenu;
	}
	
	

	public WeeklyMenu updateWeeklyMenu(@Valid WeeklyMenuAdminDTO weeklyMenuDTO) throws Exception{
		SimpleDateFormat dateParse = new SimpleDateFormat("dd-MM-yyyy");
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
						throw new IllegalArgumentException("Fit meal cannot be added without a Regular meal.");
				
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
				System.out.println(dailyMenu.toString());
				dailyMenuRepo.save(dailyMenu);
		}		
	}
		System.out.println(weekMenu.toString());
		return weekMenu;
		
}

	
	
	public WeeklyMenuDTO getNextWeeklyMenuByStartDate() {
		Date startOfWeek = getStartOfNextWeek();
		Date endOfWeek = getEndOfWeek(startOfWeek);
		Optional<WeeklyMenu> optionalWeeklyMenu = weekRepo.findByStartDateBetween(startOfWeek, endOfWeek);
		return optionalWeeklyMenu.map(WeeklyMenuDTO::new).orElse(null);
	}

	public Date getStartOfNextWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.add(Calendar.DAY_OF_WEEK, 7);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

}
