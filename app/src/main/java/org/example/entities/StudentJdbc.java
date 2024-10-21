package org.example.entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentJdbc
 */
public class StudentJdbc {

  String dateString = "2024-10-19"; // Example date string
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  // JDBC settings
  String url = "jdbc:mysql://localhost:3308/apopsis?allowPublicKeyRetrieval=true&useSSL=false";
  String user = "root";
  String password = "password";
  String createTableQuery = new StringBuilder().append("CREATE TABLE students  (")
      .append("students_id  integer NOT NULL AUTO_INCREMENT PRIMARY KEY,")
      .append("firstName varchar(100),")
      .append("lastName varchar(100),")
      .append("dateOfBirth varchar(100)")
      .append(");")
      .toString();

  String dropTableQuery = new StringBuilder().append("DROP TABLE students;")
      .toString();

  String prepareInsertStatement = new StringBuilder()
      .append("INSERT INTO students ( firstName, lastName ,dateOfBirth) ")
      .append("VALUES(?,?,?);")
      .toString();

  String selectStudentQuery = new StringBuilder()
      .append("SELECT * from students;")
      .toString();

  String selectStudentByIDQuery = new StringBuilder()
      .append("SELECT * from students where students_id = ?;")
      .toString();

  String updateStudentByID = new StringBuilder()
      .append("UPDATE students SET ")
      .append("firstname = ?,")
      .append("lastname = ?,")
      .append("dateOfBirth = ? ")
      .append(" where students_id = ?;")
      .toString();

  String deleteStudentById = new StringBuilder()
      .append("DELETE FROM students where students_id = ?;")
      .toString();
  private StudentDocumentJdbc documentJdbc = new StudentDocumentJdbc();

  public int createStudentTable() throws Exception {
    try {

      Connection con = DriverManager.getConnection(url, user, password);
      Statement statement = con.createStatement();
      statement.executeUpdate(createTableQuery);
      statement.close();
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
    }
    return 0;
  }

  public int dropStudentTable() {
    try {

      Connection con = DriverManager.getConnection(url, user, password);
      Statement statement = con.createStatement();
      statement = con.createStatement();
      statement.executeUpdate(dropTableQuery);
      statement.close();
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
    }

    return 0;
  }

  public Student saveStudent(Student student) {
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement preparedStatement = con.prepareStatement(prepareInsertStatement);
      preparedStatement.setString(1, student.getFirstName());
      preparedStatement.setString(2, student.getLastName());
      Date dateOfBirth = Date.valueOf(LocalDate.parse(student.getDateOfBirth(), formatter));
      preparedStatement.setDate(3, dateOfBirth);

      System.out.println("JDBC Executing Query: ");
      System.out.println(preparedStatement.toString());
      preparedStatement.execute();
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
    }
    return null;
  }

  public List<Student> getStudents() {
    List<Student> students = new ArrayList<>();
    try {

      Connection con = DriverManager.getConnection(url, user, password);
      Statement statement = con.createStatement();
      ResultSet set = statement.executeQuery(selectStudentQuery);
      while (set.next()) {
        Student student = new Student();
        student.setStudentId(set.getInt(1));
        student.setFirstName(set.getString(2));
        student.setLastName(set.getString(3));
        student.setDateOfBirth(set.getDate(4).toLocalDate().toString());
        student.setListOfFiles(documentJdbc.getDocumentsForStudents(student.getStudentId()));
        students.add(student);
      }
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
    }
    return students;
  }

  public Student getStudentById(int studentId) {
    Student student = new Student();
    try {

      Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement statement = con.prepareStatement(selectStudentByIDQuery);
      statement.setInt(1, studentId);
      System.out.println("EXECUTING QUERY: ");
      System.out.println(statement.toString());
      ResultSet set = statement.executeQuery();
      while (set.next()) {
        student.setStudentId(set.getInt(1));
        student.setFirstName(set.getString(2));
        student.setLastName(set.getString(3));
        student.setDateOfBirth(set.getDate(4).toLocalDate().toString());
        student.setListOfFiles(documentJdbc.getDocumentsForStudents(student.getStudentId()));
      }
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
    }
    return student;
  }

  public Student updateStudentById(int id, Student student) {
    Student studentUpdated = new Student();
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement statement = con.prepareStatement(updateStudentByID);
      statement.setString(1, student.getFirstName());
      statement.setString(2, student.getLastName());
      statement.setDate(3, Date.valueOf(LocalDate.parse(student.getDateOfBirth(), formatter)));
      statement.setInt(4, id);
      System.out.println("EXECUTING QUERY: ");
      System.out.println(statement.toString());
      System.out.println(statement.execute());
      statement.close();
      studentUpdated = getStudentById(id);
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
    }
    return studentUpdated;
  }

  public Student deleteStudentById(int id) {
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement statement = con.prepareStatement(deleteStudentById);
      statement.setInt(1, id);
      System.out.println("EXECUTING QUERY: ");
      System.out.println(statement.toString());
      statement.execute();
      statement.close();
    } catch (Exception e) {
      System.out.println("JDBC EXCEPTIONS ");
      System.out.println(e.getMessage());
      return new Student();
    }
    return null;
  }

}
