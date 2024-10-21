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
VERB: DELETE
Description: deletes a document where document_id = documentId to the student with students_id = studentId
Response:[]
```

- Test 2 Frontend skills
I have develops the layout.js that has 2 implementations:
- Vanilla JS
- JQuery

In order to test the js you open the layout.html in chrome, then you open console. 
While in console you paste the following:
```json
layout = {
        "pages": [
            {
                "description": {
                    "el_GR": "description in Greek",
                    "en_US": "description in English"
                },
                "rows": [
                    {
                        "columns": [
                            {
                                "size": 6, "fieldNames": [
                                    "RequestId"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ManagerID"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "RequestType"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ActType"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "RequestStatus"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "DateOfSubmission"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "Municipality"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "BuildingServices"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 12,
                                "fieldNames": [
                                    "ProjectDescription"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "DateOfIssue"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ValidUpTo"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ActID"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ProtocolNumber"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ApplicationKey"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "ProtocolDate"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 12,
                                "fieldNames": [
                                    "WithinPlan"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "Street"
                                ]
                            },
                            {
                                "size": 3,
                                "fieldNames": [
                                    "NumberFrom"
                                ]
                            },
                            {
                                "size": 3,
                                "fieldNames": [
                                    "NumberTo"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 3,
                                "fieldNames": [
                                    "Floor"
                                ]
                            },
                            {
                                "size": 3,
                                "fieldNames": [
                                    "City"
                                ]
                            },
                            {
                                "size": 3,
                                "fieldNames": [
                                    "PostalCode"
                                ]
                            },
                            {
                                "size": 3,
                                "fieldNames": [
                                    "MunicipalUnit"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 6,
                                "fieldNames": [
                                    "CityBlock"
                                ]
                            },
                            {
                                "size": 6,
                                "fieldNames": [
                                    "NationalLandRegistryCodeNumber"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 12,
                                "fieldNames": [
                                    "GeospatialIdentification"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 12,
                                "fieldNames": [
                                    "Comments"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 12,
                                "fieldNames": [
                                    "GoogleMapsAddress"
                                ]
                            }
                        ]
                    },
                    {
                        "columns": [
                            {
                                "size": 12,
                                "fieldNames": [
                                    "Locations"
                                ]
                            }
                        ]
                    }
                ],
                "title": {
                    "el_GR": "Title in Greek",
                    "en_US": "Title in English"
                }
            }
        ],
        "paginationMode": "single-page",
        "defaultLanguageId": "en_US"
    }
```
Then you call the functions: 
- generateFormLayout(layout)
- generateFormLayoutJquery(layout)
you can watch the changes on the html. 
