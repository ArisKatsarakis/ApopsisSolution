package org.example.entities;

/**
 * Student
 */
public class Student {
  private int studentId;
  private String firstName;
  private String lastName;
  private String dateOfBirth;

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

}
