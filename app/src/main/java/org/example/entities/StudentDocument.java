package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * StudentDocument
 */
public class StudentDocument {
  private int studentDodumentId;
  private String fileName;
  private String content;
  // id for the foreign key
  @JsonIgnore
  private int studentId;

  public StudentDocument() {

  }

  public int getStudentDodumentId() {
    return studentDodumentId;
  }

  public void setStudentDodumentId(int studentDodumentId) {
    this.studentDodumentId = studentDodumentId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

}
