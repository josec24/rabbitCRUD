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
public class Author {
        private int authorID;
        private String collection;
        private int option;
        private String name;
        private int authorPublic;
	private int nBooks;
        
	public void setAuthorID(int value) 
	{
		authorID = value;
	}
        
        public int getAuthorID()
	{
	  return authorID;	
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
	
        public void setName(String value) 
	{
		name = value;
	}
        
        public String getName() 
	{
		return name;
	}
        
	public void setPublic(int value)
	{
		authorPublic = value;
	}
	
	public int getPublic()
	{
		return authorPublic;
	}
	
	public void setNBooks(int value)
	{
		nBooks = value;
	}
	
	public int getNBooks()
	{
		return nBooks ;
	}
       
	
	
		
}
