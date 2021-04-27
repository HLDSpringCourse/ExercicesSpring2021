package org.lfc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Item {
	
	private @Id @GeneratedValue Long id;

	private String name;

	public Item(String name)
	{
		this.name = name;

	}
}
