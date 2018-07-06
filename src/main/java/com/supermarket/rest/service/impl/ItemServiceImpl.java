package com.supermarket.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.supermarket.common.utils.JsonUtils;
import com.supermarket.common.utils.KklResult;
import com.supermarket.mapper.TbItemDescMapper;
import com.supermarket.mapper.TbItemMapper;
import com.supermarket.mapper.TbItemParamItemMapper;
import com.supermarket.pojo.TbItem;
import com.supermarket.pojo.TbItemDesc;
import com.supermarket.pojo.TbItemParamItem;
import com.supermarket.pojo.TbItemParamItemExample;
import com.supermarket.pojo.TbItemParamItemExample.Criteria;
import com.supermarket.rest.dao.JedisClient;
import com.supermarket.rest.service.ItemService;
/**   
 * @ClassName:  ItemServiceImpl   
 * @Description: item base information manage service   
 * @author: KKL 
 * @date:   2018年7月6日 上午11:39:05   
 *     
 */  
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	@Qualifier("jedisClinetSingle")
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EFFECTIVE_TOTAL_TIME}")
	private int effectiveTime;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	
	/**   
	 * <p>Title: getBaseItemInfo</p>   
	 * <p>Description: according to itemId query item base information and add redis cache</p>   
	 * @param itemId
	 * @return   
	 * @see com.supermarket.rest.service.ItemService#getBaseItemInfo(long)   
	 */ 
	@Override
	public KklResult getBaseItemInfo(long itemId) {
		try {
			String cacheStr = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":base");
			if (!StringUtils.isBlank(cacheStr)) {
				TbItem cachePojo = JsonUtils.jsonToPojo(cacheStr, TbItem.class);
				return KklResult.ok(cachePojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		try {
			String jsonStr = JsonUtils.objectToJson(item);
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base", jsonStr);
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base", effectiveTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return KklResult.ok(item);
	}


	/**   
	 * <p>Title: getDescItemInfo</p>   
	 * <p>Description: depend on item id querying tb_item_desc desc filed   
	 * @param itemId
	 * @return   
	 * @see com.supermarket.rest.service.ItemService#getDescItemInfo(long)   
	 */ 
	@Override
	public KklResult getDescItemInfo(long itemId) {
		try {
			String cacheStr = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":description");
			if (!StringUtils.isBlank(cacheStr)) {
				TbItemDesc jsonToPojo = JsonUtils.jsonToPojo(cacheStr, TbItemDesc.class);
				return KklResult.ok(jsonToPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		try {
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":description", JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":description", effectiveTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return KklResult.ok(itemDesc);
	}


	/**   
	 * <p>Title: getItemParamInfo</p>   
	 * <p>Description: according to item id query table tb_item_param_item specification information   
	 * @param itemId
	 * @return   
	 * @see com.supermarket.rest.service.ItemService#getItemParamInfo(long)   
	 */ 
	@Override
	public KklResult getItemParamInfo(long itemId) {
		try {
			String cacheStr = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":specification");
			if (!StringUtils.isBlank(cacheStr)) {
				TbItemParamItem jsonToPojo = JsonUtils.jsonToPojo(cacheStr, TbItemParamItem.class);
				return KklResult.ok(jsonToPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemParamItemExample example =new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>0) {
			TbItemParamItem itemParamItem = list.get(0);
			try {
				jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":specification", JsonUtils.objectToJson(itemParamItem));
				jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":specification", effectiveTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return KklResult.ok(itemParamItem);
		}
		return KklResult.build(400, "no such item specification information");
	}
	
	

}
