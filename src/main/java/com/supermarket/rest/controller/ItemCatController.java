package com.supermarket.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supermarket.common.utils.JsonUtils;
import com.supermarket.rest.pojo.CatResult;
import com.supermarket.rest.service.ItemCatService;

/**
 * @author Admin
 * commodity category controller
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;

	/**
	 * @param callBack
	 * @return
	 */
	@RequestMapping("/itemcat/list")
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catResult);
		String result= callback +"("+json+");";
		return result;
	}
	
}
