# LearnTrack - Student & Course Management System

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Type](https://img.shields.io/badge/Type-Console%20Application-blue?style=flat-square)
![OOP](https://img.shields.io/badge/Paradigm-OOP-green?style=flat-square)
![Status](https://img.shields.io/badge/Assignment-Airtribe%20Java%20Cohort-purple?style=flat-square)

> A console-based **Student & Course Management System** built in **Core Java**.
> Demonstrates OOP fundamentals, clean layered architecture, and modular design.

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Technology Stack](#2-technology-stack)
3. [Project Structure](#3-project-structure)
4. [Class Diagram](#4-class-diagram)
5. [Getting Started](#5-getting-started)
6. [Feature Walkthrough](#6-feature-walkthrough)
7. [OOP Concepts Demonstrated](#7-oop-concepts-demonstrated)

---

## 1. Project Overview

**LearnTrack** is a menu-driven, in-memory console application that allows an administrator to:

- Manage **Students** — add, view all, search by ID, activate/deactivate
- Manage **Courses** — add, view all, activate/deactivate
- Manage **Enrollments** — enroll a student, view enrollments by student, update status

The project is built entirely using **Core Java** with no frameworks, no database, and no file I/O.
All data is stored in-memory using `ArrayList`.

---

## 2. Technology Stack

| Technology   | Details                           |
|--------------|-----------------------------------|
| Language     | Java                       |
| Type         | Console Application (menu-driven) |
| Build Tool   | None — plain `javac` compilation  |
| IDE          | IntelliJ IDEA                     |
| Paradigm     | Object-Oriented Programming       |
| Data Storage | In-Memory `ArrayList`             |
| Input        | `java.util.Scanner`               |

---

## 3. Project Structure

```
Java-Assignments/
│
└── src/
    ├── com.airtribe.learntrack.entity/
    │   ├── Person.java            <- Base class (Inheritance root)
    │   ├── Student.java           <- Extends Person
    │   ├── Trainer.java           <- Extends Person
    │   ├── Course.java            <- Course entity
    │   ├── Enrollment.java        <- Enrollment entity
    │   ├── Status.java            <- Enum: ACTIVE, COMPLETED, CANCELLED
    │   └── Hello.java             <- Hello World verification program
    │
    ├── com.airtribe.learntrack.service/
    │   ├── StudentService.java    <- Student CRUD operations
    │   ├── CourseService.java     <- Course CRUD operations
    │   └── EnrollmentService.java <- Enrollment operations
    │
    ├── com.airtribe.learntrack.ui/
    │   └── Main.java              <- Console menu entry point
    │
    ├── com.airtribe.learntrack.exception/
    │   └── EntityNotFoundException.java <- Custom exception
    │
    └── com.airtribe.learntrack.util/
        └── IdGenerator.java       <- Static ID counter utility
```

### Package Responsibilities

| Package       | Classes                                                | Responsibility                          |
|---------------|--------------------------------------------------------|-----------------------------------------|
| `entity`      | Person, Student, Trainer, Course, Enrollment, Status   | Domain models with encapsulation        |
| `service`     | StudentService, CourseService, EnrollmentService       | Business logic and data operations      |
| `ui`          | Main.java                                              | Console menu, Scanner input             |
| `exception`   | EntityNotFoundException                                | Custom exception for missing entities   |
| `util`        | IdGenerator                                            | Static ID generation utility            |

---

## 4. Class Diagram

```
                    +---------------------------+
                    |          Person           |   <- Base Class
                    +---------------------------+
                    | - id          : int       |
                    | - firstName   : String    |
                    | - lastName    : String    |
                    | - email       : String    |
                    +---------------------------+
                    | + getters / setters       |
                    | + toString() : String     |
                    +---------------------------+
                               |
              +----------------+----------------+
              |                                 |
+-------------+-----------+     +--------------+-----------+
|          Student        |     |          Trainer         |
+-------------------------+     +--------------------------+
| - batch    : String     |     | (trainer-specific fields)|
| - active   : boolean    |     +--------------------------+
+-------------------------+     | + getters / setters      |
| + Student(id,fn,ln,     |     | + toString() : String    |
|     email,batch,active) |     +--------------------------+
| + getters / setters     |
| + toString() : String   |
+-------------------------+
         |
         | 1..* enrolled in
         v
+---------------------------+          +---------------------------+
|        Enrollment         | *---- 1  |          Course           |
+---------------------------+          +---------------------------+
| - id             : int    |          | - id              : int   |
| - studentId      : int    |          | - courseName      : String|
| - courseId       : int    |          | - description     : String|
| - enrollmentDate : Date   |          | - durationInWeeks : int   |
| - status  : Status(enum)  |          | - active          : boolean|
+---------------------------+          +---------------------------+
| + Enrollment(id,sId,cId,  |          | + getters / setters       |
|     date, status)         |          | + toString() : String     |
| + Enrollment(Enrollment)  |          +---------------------------+
|   <- copy constructor     |
| + getters / setters       |
| + toString() : String     |
+---------------------------+

  <<enum>> Status
  +------------+
  | ACTIVE     |
  | COMPLETED  |
  | CANCELLED  |
  +------------+

+------------------------+   +------------------------+   +------------------------+
|    StudentService      |   |     CourseService      |   |   EnrollmentService    |
+------------------------+   +------------------------+   +------------------------+
| + addStudent()         |   | + addCourse()          |   | + enrollStudentIn      |
| + viewAllStudents()    |   | + viewAllCourses()     |   |     Course()           |
| + searchStudentById()  |   | + activateDeactivate() |   | + studentAllEnroll     |
| + activateDeactivate() |   +------------------------+   |     ments()            |
+------------------------+                               | + updateEnrollment     |
                                                         |     Status()           |
                                                         +------------------------+

+------------------------+
|      IdGenerator       |
+------------------------+
| - studentIdCounter:int | <- static
+------------------------+
| + getNextStudentId()   | <- static (currently incomplete)
| + getNextCourseId()    | <- static (currently incomplete)
+------------------------+

+-------------------------------------+
|      EntityNotFoundException        |
|      extends RuntimeException       |
+-------------------------------------+
| + EntityNotFoundException(String)   |
+-------------------------------------+

LEGEND:
  extends ---->   Inheritance
  *---- 1         Many-to-One association
  - field         private field
  + method()      public method
```

---

## 5. Getting Started

### Prerequisites
- OpenJDK 11.0.32.1 or later
- Git installed
- IntelliJ IDEA (recommended)

### Clone the Repository

```bash
git clone https://github.com/priyankamandapati975-netizen/Java-Assignments.git
cd Java-Assignments
git checkout feature/studentManagement
```

### Run via IntelliJ IDEA

1. Open IntelliJ IDEA -> **File -> Open** -> select the project folder
2. Navigate to `src/com.airtribe.learntrack.ui/Main.java`
3. Right-click -> **Run 'Main.main()'**
4. Interact with the console menu

### Run via Terminal

```bash
cd src

# Compile all files
javac -d ../out \
  com.airtribe.learntrack.entity/*.java \
  com.airtribe.learntrack.exception/*.java \
  com.airtribe.learntrack.util/*.java \
  com.airtribe.learntrack.service/*.java \
  com.airtribe.learntrack.ui/*.java

# Run
java -cp ../out com.airtribe.learntrack.ui.Main
```

### Hello World Verification

The project includes `Hello.java` in the entity package to verify Java setup:

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

---

## 6. Feature Walkthrough

### Main Menu

```
==================================================
1. Student Management
2. Course Management
3. Enrollment Management
Select the management option:
```

### Student Management

```
==================================================
1. Add new student
2. View all students
3. Search student by ID
4. Activate/Deactivate a student
Select option in student management:
```

| Option | What It Does                                                           |
|--------|------------------------------------------------------------------------|
| 1      | Add a new Student (prompts for name, email, batch)                    |
| 2      | Print all students in the ArrayList                                    |
| 3      | Search by student ID — throws `EntityNotFoundException` if not found  |
| 4      | Toggle `active` boolean — soft delete (data is never removed)          |

### Course Management

```
==================================================
1. Add new course
2. View all courses
3. Activate/Deactivate a course
Select option in course management:
```

### Enrollment Management

```
==================================================
1. Enroll student in course
2. View student enrollments by student ID
3. Update enrollment status (Completed / Cancelled)
Select option in enrollment management:
```

| Option | What It Does                                                           |
|--------|------------------------------------------------------------------------|
| 1      | Enroll a student into a course                                         |
| 2      | Show all enrollments for a given student ID                            |
| 3      | Update enrollment status to `COMPLETED` or `CANCELLED`                |

---

## 7. OOP Concepts Demonstrated

### Encapsulation
All entity fields (`id`, `firstName`, `batch`, `status`, etc.) are `private`.
Access is only through public getters and setters.

```java
// Student.java
public class Student extends Person {
    private String batch;
    private boolean active;

    public String getBatch()              { return batch; }
    public void   setBatch(String batch)  { this.batch = batch; }
    public boolean isActive()             { return active; }
    public void   setActive(boolean a)    { this.active = a; }
}
```

### Inheritance
`Student` and `Trainer` both extend `Person`, inheriting `id`, `firstName`, `lastName`, `email`.
The `super()` constructor call is used to initialize the `Person` fields.

```java
// Person.java - base class
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    // constructor, getters, setters
}

// Student.java - child class
public class Student extends Person {
    public Student(int id, String firstName, String lastName,
                   String email, String batch, boolean active) {
        super(id, firstName, lastName, email);  // calls Person constructor
        this.batch  = batch;
        this.active = active;
    }
}
```

### Polymorphism
Both `Student` and `Trainer` extend `Person`. A `Person` reference can point to either:

```java
Person p1 = new Student(122, "Priyanka", "Mandapati",
                        "priyankamandapati975@gmail.com", "C23", true);
Person p2 = new Trainer(...);
// Both treated as Person — runtime polymorphism
```

### Copy Constructor (Enrollment)
`Enrollment` demonstrates a copy constructor — a way to create a new object
from an existing object of the same type:

```java
// Copy constructor in Enrollment.java
public Enrollment(Enrollment enrollment) {
    this.id             = enrollment.getId();
    this.studentId      = enrollment.getStudentId();
    this.courseId       = enrollment.getCourseId();
    this.enrollmentDate = enrollment.getEnrollmentDate();
    this.status         = enrollment.getStatus();
}
```

### Enum — Status
Instead of error-prone Strings, `Status` is an enum ensuring only valid values are used:

```java
public enum Status {
    ACTIVE,
    COMPLETED,
    CANCELLED
}
```

### Static Members — IdGenerator
`IdGenerator` uses a `static` field so a single counter is shared across the entire application:

```java
public class IdGenerator {
    private static int studentIdCounter;

    static int getNextStudentId() { return studentIdCounter; }
    static int getNextCourseId()  { return studentIdCounter; }
}
```

### Custom Exception Handling

```java
// EntityNotFoundException.java
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}

// Used in services and caught in Main.java
try {
    studentService.searchStudentById(studentList, id);
} catch (EntityNotFoundException ex) {
    System.out.println(ex.getMessage());
}
```

### ArrayList for Dynamic Storage

```java
// Main.java - students pre-loaded into ArrayList
ArrayList<Student> studentList = new ArrayList<>(
    Arrays.asList(
        new Student(122, "Priyanka", "Mandapati", "priyankamandapati975@gmail.com", "C23", true),
        new Student(123, "Renuka",   "Mandapati", "Renukamandapati975@gmail.com",   "C23", true),
        new Student(124, "Gokul",    "Gumma",     "gokulgumma123@gmail.com",        "C23", true)
    )
);
```

---

*LearnTrack | Airtribe Java Cohort | Priyanka Mandapati | 2026*
