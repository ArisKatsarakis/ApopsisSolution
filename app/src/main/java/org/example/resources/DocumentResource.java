package org.example.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.entities.ErrorResponse;
import org.example.entities.StudentDocument;
import org.example.entities.StudentDocumentJdbc;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * DocumentResource
 */
@Path("/students")
public class DocumentResource {
  private StudentDocumentJdbc jdbc = new StudentDocumentJdbc();

  @GET
  @Path("/{id}/documents")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStudentsDocuments(@PathParam("id") int id) {
    List<StudentDocument> sDocuments;
    try {
      sDocuments = jdbc.getDocumentsForStudents(id);
    } catch (Exception e) {
      return Response.noContent().status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().entity(sDocuments).status(Response.Status.OK).build();
  }

  @POST
  @Path("/{id}/documents")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response saveStudentsDocuments(@PathParam("id") int studentId,
      @FormDataParam("file") InputStream inputStream, @FormDataParam("file") FormDataContentDisposition fileDetails) {
    try {
      String uploadFilePath = "uploads/" + fileDetails.getFileName();
      OutputStream stream = new FileOutputStream(new File(uploadFilePath));
      byte[] bytes = new byte[1024];
      int read;

      while ((read = inputStream.read(bytes)) != -1) {
        stream.write(bytes, 0, read);
      }
      stream.close();
      System.out.println("File Uploaded! Inserting in DB ");
      StudentDocument sDocument = new StudentDocument();
      sDocument.setFileName(fileDetails.getFileName());
      sDocument.setContent(stream.toString());
      sDocument.setStudentId(studentId);
      jdbc.insertDocumentInTable(sDocument);
    } catch (Exception e) {
      return Response.noContent().status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.ok().entity(null).status(Response.Status.OK).build();
  }

  @PUT
  @Path("/{id}/documents/{documentId}")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateStudentDocument(@PathParam("id") int studentId, @FormDataParam("file") InputStream inputStream,
      @FormDataParam("file") FormDataContentDisposition fileDetails, @PathParam("documentId") int documentId) {
    try {
      String uploadFilePath = "uploads/" + fileDetails.getFileName();
      File oldFile = new File(uploadFilePath);
      if (!oldFile.exists()) {
        throw new Exception("not found");
      }

      OutputStream stream = new FileOutputStream(oldFile);
      byte[] bytes = new byte[1024];
      int read;

      while ((read = inputStream.read(bytes)) != -1) {
        stream.write(bytes, 0, read);
      }
      stream.close();
      StudentDocument sDocument = new StudentDocument();
      sDocument.setStudentDodumentId(documentId);
      sDocument.setStudentId(studentId);
      sDocument.setFileName(fileDetails.getFileName());
      sDocument.setContent(stream.toString());
      jdbc.updateDocument(sDocument);
    } catch (Exception e) {
      ErrorResponse error = new ErrorResponse();
      if (e.getMessage().equals("not found")) {
        error.setError("File not found !");
        return Response.ok().entity(error).status(Response.Status.NOT_FOUND).build();
      }
      error.setError(e.getMessage());
      return Response.ok().entity(error).status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    return Response.ok().entity(null).status(Response.Status.OK).build();
  }
}
