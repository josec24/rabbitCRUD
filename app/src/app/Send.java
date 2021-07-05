
package app;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

import java.util.Scanner;

//list
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//new
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.tools.json.JSONWriter;



public class Send {
        private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        String RECIPE_NR="4"; 
        System.out.println(" ** RabbitmqCookBook - Recipe number "+RECIPE_NR+". Body Serialization with JSON **");
		
	String RabbitmqHost = "localhost";
	if (argv.length > 0)
            RabbitmqHost = argv[0];

	ConnectionFactory factory = new ConnectionFactory();
	factory.setHost(RabbitmqHost);

        try 
	{
	Connection connection = factory.newConnection();
	System.out.println("Connected: " + RabbitmqHost);
	Channel channel = connection.createChannel();
			
	String myQueue="myJSONBodyQueue_"+RECIPE_NR;
	boolean isDurable = false;
	boolean isExclusive = false;
	boolean isAutoDelete = false;
	// let's create the queue
            channel.queueDeclare(QUEUE_NAME, isDurable, isExclusive, isAutoDelete, null);
                   
	
	// we fill the books with simple data.
	/*
        for (int i = 1; i < 2; i++) {
            Book book = new Book();
            book.setBookID(i);
            book.setCollection("books");
            book.setOption(3);
            book.setBookDescription("History VOL: " + 4  );
            book.setAuthor("John Doe");
            book.setNPages(200);
            newBooks.add(book);
            }
        */
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String jsonmessage="";
        
        System.out.println("Ingrese:\n1.Operaciones para libros\n2.Operaciones para autores");
        int opt=sc1.nextInt();
        
        if(opt==1){  
            List<Book> newBooks = new ArrayList<Book>();
            String name="";
            String description="";
            String author="";
            int nPages=1;

            // entrada de una cadena
            System.out.println("Ingrese el id");
            int id = sc1.nextInt();
            System.out.println("Ingrese:\n1.Registrar un nuevo libro\n2.Buscar libro\n3.Actualizar un libro\n4.Eliminar un libro");
            int option = sc1.nextInt();
            if(option==1 ||option==3){
                System.out.println("Ingrese el nombre del libro");
                name = sc2.nextLine();
                System.out.println("Ingrese el author");
                author = sc2.nextLine();
                System.out.println("Ingrese la descripción");
                description = sc2.nextLine();
                System.out.println("Ingrese el número de páginas");
                nPages = sc1.nextInt();
            }


            //agregar 
            Book book = new Book();
            book.setBookID(id);
            book.setCollection("books");
            book.setOption(option);
            book.setBookName(name);
            book.setBookDescription(description);
            book.setAuthor(author);
            book.setNPages(nPages);
            newBooks.add(book);

                //JSONWriter --> com.rabbitmq.tools.json.JSONWriter  
            JSONWriter rabbitmqJson = new JSONWriter();
            jsonmessage = rabbitmqJson.write(newBooks);
        }else if(opt==2){
            List<Author> newAuthors = new ArrayList<Author>();
            String name="";
            int authorPublic=0;
            int nBooks=1;

            // entrada de una cadena
            System.out.println("Ingrese el id");
            int id = sc1.nextInt();
            System.out.println("Ingrese:\n1.Registrar un nuevo author\n2.Buscar author\n3.Actualizar un author\n4.Eliminar un author");
            int option = sc1.nextInt();
            if(option==1 ||option==3){
                System.out.println("Ingrese el nombre");
                name = sc2.nextLine();
                System.out.println("Ingrese el año de la primera publicación");
                authorPublic = sc1.nextInt();
                System.out.println("Ingrese el número de libros publicados");
                nBooks = sc1.nextInt();
            }


            //agregar
            Author author = new Author();
            author.setAuthorID(id);
            author.setCollection("authors");
            author.setOption(option);
            author.setName(name);
            author.setPublic(authorPublic);
            author.setNBooks(nBooks);
            newAuthors.add(author);

                //JSONWriter --> com.rabbitmq.tools.json.JSONWriter  
            JSONWriter rabbitmqJson = new JSONWriter();
            jsonmessage = rabbitmqJson.write(newAuthors);
        }
        
        
	channel.basicPublish("", QUEUE_NAME, null, jsonmessage.getBytes());
			
	System.out.println("JSON Message sent :" + jsonmessage);
	channel.close();
	System.out.println("Done.");
	connection.close();
	} catch (IOException e) {
            e.printStackTrace();
	} catch (ShutdownSignalException e) {
            e.printStackTrace();
	}

    }
}
