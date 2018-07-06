package com.supermarket.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supermarket.common.utils.KklResult;
import com.supermarket.rest.service.ItemService;

/**   
 * @ClassName: ItemController   
 * @Description: item information manage controller   
 * @author: KKL 
 * @date: 2018年7月6日 上午11:47:20      
 */  
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**   
	 * @Title: getItemBaseInfo   
	 * @Description: according to item id query item base information   
	 * @param: @param itemId
	 * @param: @return      
	 * @return: KklResult      
	 * @throws   
	 */ 
	@RequestMapping("/item/info/{itemId}")
	@ResponseBody
	public KklResult getItemBaseInfo(@PathVariable Long itemId) {
		KklResult result = itemService.getBaseItemInfo(itemId);
		return result;
	}
	
	/**   
	 * @Title: getItemDescInfo   
	 * @Description: according to item id query tb_item_desc desc data   
	 * @param: @param itemId
	 * @param: @return      
	 * @return: KklResult      
	 * @throws   
	 */ 
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public KklResult getItemDescInfo(@PathVariable Long itemId) {
		KklResult result = itemService.getDescItemInfo(itemId);
		return result;
	}
	
	/**   
	 * @Title: getItemSpecificationInfo   
	 * @Description: depend item id query table tb_item_param_item data(item specification information)   
	 * @param: @param itemId
	 * @param: @return      
	 * @return: KklResult      
	 * @throws   
	 */ 
	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public KklResult getItemSpecificationInfo(@PathVariable Long itemId) {
		KklResult result = itemService.getItemParamInfo(itemId);
		return result;
	}

}
