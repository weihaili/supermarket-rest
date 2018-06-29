package com.supermarket.rest.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.mapper.TbItemCatMapper;
import com.supermarket.pojo.TbItemCat;
import com.supermarket.pojo.TbItemCatExample;
import com.supermarket.pojo.TbItemCatExample.Criteria;
import com.supermarket.rest.pojo.CatNode;
import com.supermarket.rest.pojo.CatResult;
import com.supermarket.rest.service.ItemCatService;
/**
 * @author Admin
 * commodity category service
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	/* query category list
	 * @see com.supermarket.rest.service.ItemCatService#getItemCatList()
	 */
	@Override
	public CatResult getItemCatList() {
		CatResult catResult=new CatResult();
		catResult.setData(getCatList(0));
		
		return catResult;
	}
	
	private List<?> getCatList(long parentId){
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List resultList=new ArrayList();
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		int count=14;
		for (TbItemCat tbItemCat : list) {
			CatNode catNode = new CatNode();
			if(tbItemCat.getIsParent()) {
				if (parentId==0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count--;
				if(parentId==0 && count==0) {
					break;
				}
			}else {
				resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return resultList;
	}

}
