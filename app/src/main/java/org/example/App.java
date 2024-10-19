package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.entities.StudentJdbc;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {

  public static void main(String[] args) throws Exception {

    // Create a Jetty server on port 8080
    Server server = new Server(8080);
    

    // Set up the Jersey servlet and context
    ResourceConfig config = new ResourceConfig();
    config.packages("org.example.resources"); // Package containing resources
    ServletHolder servlet = new ServletHolder(new ServletContainer(config));
    // Set the servlet context
    ServletContextHandler context = new ServletContextHandler(server, "/");
    context.addServlet(servlet, "/*");

    //JDBC Config
    StudentJdbc jdbc =new StudentJdbc();
    

    // Start the Jetty server
    try {
      server.start();
      System.out.println("Server started at http://localhost:8080/");
      jdbc.createStudentTable();
      server.join();
    } catch (Exception e) {
      System.out.println(e.getMessage()); 
    } finally {
      server.destroy();
      jdbc.dropStudentTable();
    }
  }
}
