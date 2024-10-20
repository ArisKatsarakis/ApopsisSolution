package org.example.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Student
 */
public class Student {
  private int studentId;
  private String firstName;
  private String lastName;
  private String dateOfBirth;

  @JsonIgnore
  private List<StudentDocument> listOfFiles;

  public Student() {
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public List<StudentDocument> getListOfFiles() {
    return listOfFiles;
  }

  public void setListOfFiles(List<StudentDocument> listOfFiles) {
    this.listOfFiles = listOfFiles;
  }

}
