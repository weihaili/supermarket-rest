package com.supermarket.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * general and common methods
	 * @param callBack
	 * @return
	 */
	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catResult);
		String result= callback +"("+json+");";
		return result;
	}
	
	@RequestMapping(value="/itemcatobj/list")
	@ResponseBody
	public Object getItemCatListObject(String callbackname) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callbackname);
		return mappingJacksonValue;
	}
	
}
