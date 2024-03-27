package com.simpletask.simplemeal.controllers;

import java.io.IOException;
import java.text.ParseException;

import com.simpletask.simplemeal.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.simpletask.simplemeal.dto.WeeklyMenuAdminDTO;
import com.simpletask.simplemeal.dto.WeeklyMenuDTO;
import com.simpletask.simplemeal.dto.WeeklyMenuResponseDTO;
import com.simpletask.simplemeal.model.WeeklyMenu;
import com.simpletask.simplemeal.service.ImageService;
import com.simpletask.simplemeal.service.WeeklyMenuService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/meals/")
public class WeeklyMenuController {

    @Autowired
    WeeklyMenuService weekService;


    @Autowired
    ImageService imageService;

    @PostMapping("save-weekly-menu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_THE_CHOSEN_ONE')")
    public ResponseEntity<WeeklyMenuResponseDTO> saveWeeklyMenu(@RequestBody WeeklyMenuAdminDTO weeklyMenuDTO) throws ParseException, IOException {
        WeeklyMenu weekMenu = weekService.saveWeeklyMenu(weeklyMenuDTO);
        if (weekMenu != null) {
            WeeklyMenuResponseDTO weekMenuResponse = new WeeklyMenuResponseDTO(weekMenu.getId());
            return ResponseEntity.ok(weekMenuResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("uploadFile/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_THE_CHOSEN_ONE')")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) throws IOException {
        WeeklyMenu weekMenu = imageService.addImage(file, id);
        if (weekMenu != null && weekMenu.getImage() != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("update-weekly-menu")
    public ResponseEntity<String> updateWeeklyMenu(@Valid @RequestBody WeeklyMenuAdminDTO weeklyMenuDTO) throws Exception {
        WeeklyMenu weekMenu = weekService.updateWeeklyMenu(weeklyMenuDTO);
        if (weekMenu != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping("this-week")
    public ResponseEntity<WeeklyMenuDTO> getWeeklyMenu() {
        WeeklyMenuDTO weeklyMenuDTO = weekService.getWeeklyMenuByStartDate();
        if (weeklyMenuDTO != null) {
            return new ResponseEntity<>(weeklyMenuDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("next-week")
    public ResponseEntity<WeeklyMenuDTO> getNextWeeklyMenu() {
        WeeklyMenuDTO weeklyMenuDTO = weekService.getNextWeeklyMenuByStartDate();
        if (weeklyMenuDTO != null) {
            return new ResponseEntity<>(weeklyMenuDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        Image image = imageService.getImageById(id);
        byte[] imageData = image.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageData.length);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}


