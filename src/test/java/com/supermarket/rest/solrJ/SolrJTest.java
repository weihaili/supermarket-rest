package com.supermarket.rest.solrJ;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Admin
 * add document test
 * 1. create solrJ connection
 * 2. create documnet object
 * 3. write the document object to the index library
 * 4. commit
 */
public class SolrJTest {
	
	private String baseURL="http://192.168.25.133:8080/solr";
	
	private SolrServer solrServer;
	
	private SolrInputDocument document;
	
	private SolrQuery query;
	
	@Before
	public void init() {
		//single version use httpSolrServer
		//cluster version use cloudSolrServer
		solrServer=new HttpSolrServer(baseURL);
		
		document=new SolrInputDocument();
		
		query =new SolrQuery();
	}
	
	@Test
	public void queryList() {
		query.setQuery("*:*");
		query.setStart(1);
		query.setRows(5);
		try {
			QueryResponse response = solrServer.query(query);
			SolrDocumentList solrDocumentList = response.getResults();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *test query 
	 */
	@Test
	public void query() {
		//create solrQuery object
		
		//set query condition
		query.setQuery("*:*");
		//page parameter  page and rows
		query.setStart(1);
		query.setRows(5);
		//execute query
		try {
			QueryResponse response = solrServer.query(query);
			//get query result
			SolrDocumentList solrDocumentList = response.getResults();
			long total = solrDocumentList.getNumFound();
			System.out.println("total= "+total);
			for (SolrDocument solrDocument : solrDocumentList) {
				System.out.println(solrDocument.get("id"));
				System.out.println(solrDocument.get("item_title"));
				System.out.println(solrDocument.get("item_price"));
				System.out.println(solrDocument.get("item_image"));
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *delete by query 
	 */
	@Test
	public void deletByQuery() {
		try {
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * delete by id
	 */
	@Test
	public void deleteById() {
		try {
			solrServer.deleteById("test001");
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * update 
	 * attention:id consistent
	 */
	@Test
	public void update() {
		document.addField("id", "test001");
		document.addField("item_desc", "testSolrJupdate");
		document.addField("item_price", 4545);
		try {
			solrServer.add(document);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test add
	 */
	@Test
	public void add() {
		document.addField("id", "test001");
		document.addField("item_title", "testSolrJadd");
		document.addField("item_price", 1234);
		try {
			solrServer.add(document);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
