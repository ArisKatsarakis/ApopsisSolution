# Katsarakis - Tests development

## Instructions for testing 

- Test 1: backend
```bash
docker compose up -d
```
In order for the mysql db to run. 
Build the project.
```bash
gradle build
```
```bash
gradle run
```

After running the projects you can test the following URLs:
```text
Student Entity : {
  "studentId":1,
  "firstName":"ArisTheGreat",
  "lastName":"Katsarakis",
  "dateOfBirth":"1993-11-20",
  "listOfFiles":[
      {
        "studentDodumentId":1,
        "fileName":"test.txt",
        "content":"java.io.FileOutputStream@66e3954"
      }
  ]
}
- localhost:8080/students
VERB: GET
Response: [
    Array of Students
]
VERB: POST
Requet: Student Entity
```
- localhost:8080/students/{studentId}
```text
VERB: GET
Response: {
    Student with students_id the student_id of the path variable
}
VERB: Delete
Description: deletes the student with students_id = studentId
VERB: PUT
Input: {
  With body of Student
}
Response: {
    Update Student with students_id the student_id of the path variable
}
```
- localhost:8080/students/{studentId}/documents
```text
VERB:GET
Response: [
    Array of the documents of the student with students_id = studentId
]
VERB:POST
Description: Inserts a document to the student with students_id = studentId
Input : {
  Document Entity
}
Response:[]
```
- localhost:8080/students/{studentId}/documents/{documentId}
```text
VERB: PUT
Description: Updates a document where document_id = documentId to the student with students_id = studentId
Input : {
  Document Entity
}
Response:[]
```
