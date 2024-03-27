package com.simpletask.simplemeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "weeky_menus_id")
    private WeeklyMenu weeklyMenu;

    @Lob
    @Column(name = "data")
    private byte[] data;
    
    

	public Image(Long id, WeeklyMenu weeklyMenu, byte[] data) {
		super();
		this.id = id;
		this.weeklyMenu = weeklyMenu;
		this.data = data;
	}
	
	public Image() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WeeklyMenu getWeeklyMenu() {
		return weeklyMenu;
	}

	public void setWeeklyMenu(WeeklyMenu weeklyMenu) {
		this.weeklyMenu = weeklyMenu;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

    
}