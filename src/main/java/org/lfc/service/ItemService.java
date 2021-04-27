package org.lfc.service;

import java.util.ArrayList;

import org.lfc.entity.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	ArrayList<Item> itemList = new ArrayList<>();

	public Item add(String name)
	{
		Item item = new Item(name);
		itemList.add(item);
		System.out.println(item.getId());
		return item;
	}
}
