/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;


/**
 *
 * @author jose-
 */
public class Book {
	private int bookID;
        private String collection;
        private int option;
        private String name;
        private int nPages;
	private String bookDescription;
	private String author;
	
	public void setBookID(int value) 
	{
		bookID = value;
	}
        
        public int getBookID()
	{
	  return bookID;	
	}
	
        public void setCollection(String value) 
	{
		collection = value;
	}
        
        public String getCollection() 
	{
		return collection;
	}
        
        public void setOption(int value) 
	{
		option = value;
	}
        
        public int getOption() 
	{
		return option;
	}
	
        public void setBookName(String value)
	{
		name = value;
	}
	
	public String getBookName()
	{
		return name;
	}
        
	public void setBookDescription(String value)
	{
		bookDescription = value;
	}
	
	public String getBookDescription()
	{
		return bookDescription;
	}
	
	public void setAuthor(String value)
	{
		author = value;
	}
	
	public String getAuthor()
	{
		return author ;
	}
        
        public void setNPages(int value) 
	{
		nPages = value;
	}
        
        public int getNPages() 
	{
		return nPages;
	}
	
	
		
	
	
}

