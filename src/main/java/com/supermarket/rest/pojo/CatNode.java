package com.supermarket.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatNode {
	
	/**
	 * change the key display value for generating json data
	 * json:{name:"tomcat",age:21,address:"alsj"}
	 * use @JsonProperty on json objec key :for example 
	 * @JsonProperty("n")
	 * private String name;
	 * after convert to json object 
	 * json:{n:"tomcat",age:21,address:"alsj"}
	 */
	
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("i")
	private List<?> item;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<?> getItem() {
		return item;
	}

	public void setItem(List<?> item) {
		this.item = item;
	} 

}
