package com.supermarket.rest.service;

import com.supermarket.common.utils.KklResult;

public interface ItemService {
	
	/**   
	 * @Title: getBaseItemInfo   
	 * @Description: according item id query table tb_item information then add redis cache  
	 * @param: @param itemId
	 * @param: @return      
	 * @return: KklResult      
	 * @throws   
	 */ 
	KklResult getBaseItemInfo(long itemId);
	
	/**   
	 * @Title: getDescItemInfo   
	 * @Description: depend item id query table tb_item_desc filed desc then and redis cache    
	 * @param: @param itemId
	 * @param: @return      
	 * @return: KklResult      
	 * @throws   
	 */ 
	KklResult getDescItemInfo(long itemId);
	
	/**   
	 * @Title: getItemParamInfo   
	 * @Description: depend item id query table tb_item_param_item information 
	 * @param: @param itemId
	 * @param: @return      
	 * @return: KklResult      
	 * @throws   
	 */ 
	KklResult getItemParamInfo(long itemId);

}
