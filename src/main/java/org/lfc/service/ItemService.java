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
		int size = this.itemList.size();
		if (size > 0)
			item.setId(Long.valueOf(this.itemList.get(size - 1).getId().intValue() + 1));
		else
			item.setId(Long.valueOf(0));
		this.itemList.add(item);
		return item;
	}

	public Item delete(Long id)
	{
		int size = this.itemList.size();
		for (int i = 0; i < size; i++)
			if (this.itemList.get(i).getId() == id)
				return itemList.remove(i);
		return null;
	}

	public Item put(Long id, String name)
	{
		int size = this.itemList.size();
		for (int i = 0; i < size; i++)
		{
			Item item = this.itemList.get(i);
			if (item.getId() == id)
			{
				item.setName(name);
				return item;
			}
		}
		return null;
	}

	public Item get(Long id)
	{
		int size = this.itemList.size();
		for (int i = 0; i < size; i++)
		{
			Item item = this.itemList.get(i);
			if (item.getId() == id)
				return item;
		}
		return null;
	}

	public ArrayList<Item> getAll()
	{
		return itemList;
	}
}
