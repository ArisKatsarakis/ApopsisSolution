package org.example.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.entities.StudentDocument;
import org.example.entities.StudentDocumentJdbc;

/**
 * DocumentResource
 */
@Path("/students")
public class DocumentResource {
  private StudentDocumentJdbc jdbc = new StudentDocumentJdbc();

  // @PathParam("id") int id
  @GET
  @Path("/{id}/documents")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStudentsDocuments(@PathParam("id") int id) {
    List<StudentDocument> sDocuments;
    System.out.println("GET CALLEd");
    try {
      sDocuments = jdbc.getDocumentsForStudents(id);
    } catch (Exception e) {
      return Response.noContent().status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().entity(sDocuments).status(Response.Status.OK).build();
  }

}
