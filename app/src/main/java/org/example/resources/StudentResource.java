package org.example.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.entities.Student;
import org.example.entities.StudentJdbc;

/**
 * StudentResource
 */
@Path("/students")
public class StudentResource {

  private StudentJdbc jdbc = new StudentJdbc();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createStudent(Student student) {
    jdbc.saveStudent(student);
    return Response.ok().entity(student).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createStudentForm(@FormParam("lastName") String lastName, @FormParam("firstName") String firstName, @FormParam("dateOfBirth") String dateOfBirth) {
    Student student = new Student();
    student.setLastName(lastName);
    student.setFirstName(firstName);
    student.setDateOfBirth(dateOfBirth);
    jdbc.saveStudent(student);
    return Response.ok().entity(student).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStudents() {
    List<Student> students = jdbc.getStudents();
    return Response.ok().entity(students).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStudentById(@PathParam("id") int id) {
    Student student = jdbc.getStudentById(id);
    if (student.getStudentId() == 0) {
      return Response.noContent().status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().entity(student).build();
  }

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateStudent(@PathParam("id") int id, Student student) {
    Student studentUpdated = jdbc.updateStudentById(id, student);
    if (studentUpdated.getFirstName() == null) {
      return Response.noContent().status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().entity(studentUpdated).build();
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteStudentById(@PathParam("id") int id) {
    Student studentDeleted = jdbc.deleteStudentById(id);
    if (studentDeleted != null) {
      return Response.noContent().status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().status(Response.Status.OK).build();
  }

}
