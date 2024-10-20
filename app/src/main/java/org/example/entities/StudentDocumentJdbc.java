package org.example.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDocumentJdbc
 */
public class StudentDocumentJdbc {

  static String url = "jdbc:postgresql://localhost:5438/postgres";
  static String user = "postgres";
  static String password = "postgres";

  String createTableQuery = new StringBuilder()
      .append("CREATE TABLE documents_students (")
      .append("document_id BIGSERIAL NOT NULL,  ")
      .append("fileName varchar(100),")
      .append("content varchar(4000),")
      .append("students_id integer, ")
      .append("CONSTRAINT document_student FOREIGN KEY (students_id ) ")
      .append("REFERENCES students(students_id )")
      .append(");")
      .toString();

  String dropTableQuery = new StringBuilder()
      .append("DROP TABLE documents_students;")
      .toString();

  String insertDocumentQuery = new StringBuilder()
      .append("INSERT INTO TABBLE documents_students ")
      .append("(filename, conntent, students_id) ")
      .append("VALUES ( ?,  ?, ?); ")
      .toString();

  String updateDocumentQuery = new StringBuilder()
      .append("UPDATE documents_students set ")
      .append(" filename = ?, ")
      .append(" content = ? ")
      .append(" where document_id = ? ")
      .toString();

  String selectDocumentsByStudentsIQuery = new StringBuilder()
      .append("SELECT * FROM documents_students ")
      .append("where students_id = ? ;")
      .toString();

  public void createDocumetsTable() throws Exception {
    try {
      executeUpdateQuery(createTableQuery);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void dropDocumentsTable() throws Exception {
    try {
      executeUpdateQuery(dropTableQuery);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void insertDocumentInTable(StudentDocument sDocument, int studentId) throws Exception {
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement preparedStatement = con.prepareStatement(insertDocumentQuery);
      preparedStatement.setString(1, sDocument.getFileName());
      preparedStatement.setString(2, sDocument.getContent());
      preparedStatement.setInt(3, studentId);
      System.out.println("EXECUTING QUERY");
      System.out.println(preparedStatement.toString());
      preparedStatement.execute();
      preparedStatement.close();
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS");
      System.out.println(e.getMessage());
    }
  }

  public List<StudentDocument> getDocumentsForStudents(int studentId) throws Exception {
    List<StudentDocument> sDocuments = new ArrayList<>();
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement preparedStatement = con.prepareStatement(selectDocumentsByStudentsIQuery);
      preparedStatement.setInt(1, studentId);
      System.out.println("Executing Query;");
      System.out.println(preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        StudentDocument studentDocument = new StudentDocument();
        studentDocument.setContent(rs.getString("content"));
        studentDocument.setFileName(rs.getString("filename"));
        studentDocument.setStudentId(rs.getInt("students_id"));
        studentDocument.setStudentDodumentId(rs.getInt("document_id"));
        sDocuments.add(studentDocument);
      }
      System.out.println("Size of Query Resutlts : " + sDocuments.size());
    } catch (Exception e) {
      System.out.println("JDBC Exceptions");
      System.out.println(e.getMessage());
      throw e;
    }
    return sDocuments;
  }

  private void executeUpdateQuery(String query) throws Exception {
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      Statement statement = con.createStatement();
      System.out.println("Executing query: ");
      System.out.println(query);
      statement.execute(query);
      statement.close();
    } catch (Exception e) {
      System.out.println("JDBD EXCEPTIONS");
      throw e;
    }
  }

}
