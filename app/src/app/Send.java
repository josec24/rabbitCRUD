
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

	List<Book> newBooks = new ArrayList<Book>();
			// we fill the books with simple data.
	for (int i = 1; i < 2; i++) {
            Book book = new Book();
            book.setBookID(i);
            book.setBookDescription("History VOL: " + i  );
            book.setAuthor("John Doe");
            newBooks.add(book);
            }
			
            //JSONWriter --> com.rabbitmq.tools.json.JSONWriter  
	JSONWriter rabbitmqJson = new JSONWriter();
	String jsonmessage = rabbitmqJson.write(newBooks);
			
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
