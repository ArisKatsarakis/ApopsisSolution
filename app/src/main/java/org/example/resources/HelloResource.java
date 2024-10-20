package org.example.resources;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.entities.Student;

/**
 * HelloResource
 */
@Path("/hello")
public class HelloResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response sayHello() {
    Student student = new Student();
    student.setStudentId(1);
    student.setFirstName("Aris");
    student.setLastName("Katsarakis");
    Date dateOfBirth = new Date(1993, 11, 22);
    student.setDateOfBirth(dateOfBirth.toString());
    return Response.ok(Response.Status.OK).entity(student).build();
  }

  @GET
  @Path("/there")
  @Produces(MediaType.APPLICATION_JSON)
  public Response sayHelloToStudents() {
    Student student = new Student();
    student.setStudentId(1);
    student.setFirstName("Aris");
    student.setLastName("Katsarakis");
    Date dateOfBirth = new Date(1993, 11, 22);
    student.setDateOfBirth(dateOfBirth.toString());
    List<Student> students = new ArrayList<>();
    students.add(student);
    return Response.ok(Response.Status.OK).entity(students).build();
  }
}
