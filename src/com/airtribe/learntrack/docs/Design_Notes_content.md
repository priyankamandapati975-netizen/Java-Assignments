# Design Notes
## LearnTrack - Architectural and Design Decisions

> **File:** `docs/Design_Notes.md` | **Project:** LearnTrack | **Author:** Priyanka Mandapati

---

## Table of Contents

1. [Purpose](#1-purpose)
2. [Why ArrayList Instead of Array](#2-why-arraylist-instead-of-array)
3. [Where Static Members Are Used and Why](#3-where-static-members-are-used-and-why)
4. [Where Inheritance Is Used and What We Gained](#4-where-inheritance-is-used-and-what-we-gained)
5. [Overall Architecture](#5-overall-architecture)
6. [Enum for Status](#6-enum-for-status)
7. [Exception Handling Design](#7-exception-handling-design)
8. [Copy Constructor in Enrollment](#8-copy-constructor-in-enrollment)
9. [Soft Delete Design](#9-soft-delete-design)
10. [Clean Code Principles](#10-clean-code-principles)

---

## 1. Purpose

This document explains the key design decisions made in LearnTrack and directly answers
the three questions required by the assignment:

- Why was **ArrayList** chosen instead of a plain array?
- Where are **static members** used, and why?
- Where is **inheritance** used, and what did we gain from it?

---

## 2. Why ArrayList Instead of Array?

### The Problem with Plain Arrays

A plain Java array has a fixed size declared at creation time. In LearnTrack, we do not
know how many students, courses, or enrollments will be added during a session. Using a
fixed array would force us to pick an arbitrary max size upfront, which is both wasteful
and error-prone.

```java
// The problem with fixed arrays
Student[] students = new Student[10]; // what if we need 11?
students[10] = new Student(...);      // ArrayIndexOutOfBoundsException!

// Also no clean way to:
// - Count only actual students (not null slots)
// - Remove a student without manually shifting all elements
```

### How ArrayList Solves This

```java
// ArrayList grows automatically as we add students
ArrayList<Student> studentList = new ArrayList<>();
studentList.add(new Student(...));    // grows automatically
studentList.add(new Student(...));    // no size limit concerns
studentList.remove(studentObject);    // clean removal, no manual shifting
int count = studentList.size();       // accurate count of real students
```

### Where ArrayList Is Used in LearnTrack

In `Main.java`, the three core lists are declared as `ArrayList` and passed to services:

```java
// From Main.java - actual code in the project
ArrayList<Student> studentList = new ArrayList<>(
    Arrays.asList(
        new Student(122, "Priyanka", "Mandapati", "priyankamandapati975@gmail.com", "C23", true),
        new Student(123, "Renuka",   "Mandapati", "Renukamandapati975@gmail.com",   "C23", true),
        new Student(124, "Gokul",    "Gumma",     "gokulgumma123@gmail.com",        "C23", true)
    )
);

ArrayList<Course>     courseList     = new ArrayList<>();
ArrayList<Enrollment> enrollmentList = new ArrayList<>();
```

Each service receives the list and operates on it:

```java
// StudentService.java - actual code
public void addStudent(List<Student> studentList, Student student) {
    studentList.add(student);
}

public void viewAllStudents(List<Student> studentList) {
    for (Student student : studentList) {
        System.out.println(student);
    }
}
```

### Comparison

| Feature          | Plain Array                             | ArrayList                           |
|------------------|-----------------------------------------|-------------------------------------|
| Size             | Fixed at declaration                    | Dynamic, grows automatically        |
| Add element      | arr[index] = value - manual             | list.add(value) - simple            |
| Remove element   | Manually shift all elements             | list.remove(obj) - built-in         |
| Know actual size | .length includes null slots             | .size() always accurate             |
| Type safety      | Basic                                   | Generics: ArrayList<Student>        |
| Searching        | Write your own loop every time          | .contains(), .indexOf() built-in    |

---

## 3. Where Static Members Are Used and Why

### What is Static?

A `static` member belongs to the **class itself**, not to any individual object instance.
There is exactly ONE copy of a static field in memory, shared across the entire application.

### IdGenerator - The Main Use of Static in LearnTrack

```java
// util/IdGenerator.java - actual code from the project
package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 1;
    private static int courseIdCounter  = 1;
    private static int enrollmentIdCounter = 1;

    public static int getNextStudentId()    { return studentIdCounter++; }
    public static int getNextCourseId()     { return courseIdCounter++; }
    public static int getNextEnrollmentId() { return enrollmentIdCounter++; }
}
```

The `studentIdCounter` field is `static` because:

1. **Shared state** - All service classes need to see the same counter. If it were an instance
   variable, every `new IdGenerator()` would start its own counter from 1 and IDs would collide.
2. **Single source of truth** - There is one counter for the whole app, not one per object.
3. **No object needed** - `IdGenerator.getNextStudentId()` can be called without creating an instance.

### Why Static Without Instance Variable Would Break

```java
// If studentIdCounter were NOT static (wrong way):
public class IdGenerator {
    private int studentIdCounter = 1;  // instance variable!
}

IdGenerator gen1 = new IdGenerator();
IdGenerator gen2 = new IdGenerator();
gen1.getNextStudentId(); // returns 1
gen2.getNextStudentId(); // ALSO returns 1 - DUPLICATE ID!

// With static (correct way):
IdGenerator.getNextStudentId(); // returns 1
IdGenerator.getNextStudentId(); // returns 2
IdGenerator.getNextStudentId(); // returns 3 - always unique!
```
---

## 4. Where Inheritance Is Used and What We Gained

### The Inheritance Hierarchy

```
Person  (base class)
+-- Student  (extends Person)
+-- Trainer  (extends Person)
```

### Person - The Base Class

`Person` holds the common fields shared by every type of person in the system:

```java
// entity/Person.java - actual structure
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    // constructor, getters, setters, toString
}
```

### Student - Extends Person

`Student` adds its own specific fields (`batch`, `active`) on top of the inherited Person fields.
The `super()` call delegates initialization of the Person fields to the parent constructor:

```java
// entity/Student.java - actual code
public class Student extends Person {
    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName,
                   String email, String batch, boolean active) {
        super(id, firstName, lastName, email);  // Person fields initialized here
        this.batch  = batch;
        this.active = active;
    }
}

// Used in Main.java with actual data:
new Student(122, "Priyanka", "Mandapati", "priyankamandapati975@gmail.com", "C23", true)
new Student(123, "Renuka",   "Mandapati", "Renukamandapati975@gmail.com",   "C23", true)
new Student(124, "Gokul",    "Gumma",     "gokulgumma123@gmail.com",        "C23", true)
```

### Trainer - Also Extends Person

`Trainer` follows the same pattern, extending `Person` and adding trainer-specific fields
while reusing the common Person fields through inheritance.

### What We Gained from Inheritance

| Benefit          | Without Inheritance                                     | With Inheritance                           |
|------------------|---------------------------------------------------------|--------------------------------------------|
| Code Reuse       | id, firstName, lastName, email duplicated in both classes | Defined once in Person, inherited by both |
| DRY Principle    | Fix a bug in email handling means updating both classes | Fix once in Person, fixed everywhere       |
| Type Grouping    | Cannot treat Student and Trainer as the same type       | Both can be referenced as Person           |
| Extensibility    | Adding a new person type copies all Person fields again | New class just extends Person              |
| Clean Structure  | Student class bloated with unrelated fields             | Student only contains what is unique to it |

### Without Inheritance - What It Would Look Like

```java
// Without inheritance - repetitive and wrong
public class Student {
    private int id;            // repeated!
    private String firstName;  // repeated!
    private String lastName;   // repeated!
    private String email;      // repeated!
    private String batch;
    private boolean active;
}

public class Trainer {
    private int id;            // repeated again!
    private String firstName;  // repeated again!
    private String lastName;   // repeated again!
    private String email;      // repeated again!
    // trainer-specific fields
}
// Any change to how email is handled means updating both classes
// Violates the DRY (Don't Repeat Yourself) principle
```

---

## 5. Overall Architecture

### Three-Layer Design

LearnTrack follows a clean three-layer architecture:

```
+------------------------------------------------+
|           UI Layer  (Main.java)                |
|  - Displays menus using System.out.println     |
|  - Reads input using Scanner                   |
|  - Creates ArrayList collections               |
|  - Calls service methods passing the lists     |
|  - Catches EntityNotFoundException             |
+------------------+-----------------------------+
                   | calls
+------------------v-----------------------------+
|        Service Layer  (Service classes)        |
|  - Contains business logic                     |
|  - Receives ArrayList from UI as parameter     |
|  - Adds, searches, updates entities            |
|  - Throws EntityNotFoundException on error     |
+------------------+-----------------------------+
                   | uses
+------------------v-----------------------------+
|        Entity Layer  (Model classes)           |
|  - Plain data containers                       |
|  - Private fields + public getters/setters     |
|  - toString() for display                      |
+------------------------------------------------+
```

### How the Layers Interact

```java
// Main.java (UI Layer) creates the list and calls service
ArrayList<Student> studentList = new ArrayList<>(...);
StudentService studentService = new StudentService();

// UI calls service, passing the list as a parameter
studentService.addStudent(studentList, newStudent);

// StudentService (Service Layer) operates on the list
public void addStudent(List<Student> studentList, Student student) {
    studentList.add(student);
    System.out.println("Student added: " + student);
}

// Student (Entity Layer) is a pure data object
public String toString() {
    return "Student{id=" + id + ", name=" + firstName + " " + lastName + "}";
}
```

### Design Decision: Passing ArrayList to Services

In this implementation, the `ArrayList` is created in `Main.java` and passed as a parameter
to each service method. This is a valid and clean design choice because:

- The UI layer controls the lifecycle of the data collections
- Services are stateless - they do not hold the data, they just operate on what is passed in
- The data flow is easy to follow and understand
- Fits the scope of a first Core Java project

---

## 6. Enum for Status

Instead of using plain String values like `"ACTIVE"`, `"COMPLETED"`, `"CANCELLED"`,
an enum is used for enrollment status:

```java
// entity/Status.java - actual code
public enum Status {
    ACTIVE,
    COMPLETED,
    CANCELLED
}
```

Used in `Enrollment.java`:

```java
private Status status;

public Enrollment(int id, int studentId, int courseId, Date enrollmentDate, Status status) {
    this.status = status;
    // ...
}
```

Used in `Main.java` when updating enrollment status:

```java
Status status = activeStatus.equalsIgnoreCase("Completed") ? Status.COMPLETED :
               (activeStatus.equalsIgnoreCase("Cancelled") ? Status.CANCELLED : null);
enrollmentService.updateEnrollmentStatus(enrollId, status, enrollmentList);
```

### Why Enum Over String

```java
// String - dangerous, typo compiles but breaks at runtime
enrollment.setStatus("COMPELTED");        // typo! compiles fine, wrong behavior

// Enum - typo caught at compile time immediately
enrollment.setStatus(Status.COMPELTED);   // compile error right away!
enrollment.setStatus(Status.COMPLETED);   // correct!
```

---

## 7. Exception Handling Design

### EntityNotFoundException

```java
// exception/EntityNotFoundException.java - actual code
package com.airtribe.learntrack.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
```

It extends `RuntimeException` (unchecked) so:
- Services can throw it without declaring `throws` in the method signature
- It propagates up naturally to `Main.java` where it is caught and displayed
- The user sees a clean error message instead of a full stack trace

### Exception Flow

```java
// In a service - thrown when entity not found
public void searchStudentById(List<Student> studentList, int id) {
    for (Student s : studentList) {
        if (s.getId() == id) {
            System.out.println(s);
            return;
        }
    }
    throw new EntityNotFoundException("Student with ID " + id + " not found.");
}

// In Main.java - caught and displayed cleanly to the user
try {
    studentService.searchStudentById(studentList, stuId);
} catch (EntityNotFoundException ex) {
    System.out.println(ex.getMessage());
}
```

This separation keeps the service focused on business logic and the UI focused on display.

---

## 8. Copy Constructor in Enrollment

`Enrollment` demonstrates both a regular constructor and a **copy constructor**:

```java
// Regular constructor - creates a new Enrollment from individual values
public Enrollment(int id, int studentId, int courseId, Date enrollmentDate, Status status) {
    this.id             = id;
    this.studentId      = studentId;
    this.courseId       = courseId;
    this.enrollmentDate = enrollmentDate;
    this.status         = status;
}

// Copy constructor - creates a new Enrollment from an existing one
public Enrollment(Enrollment enrollment) {
    this.id             = enrollment.getId();
    this.studentId      = enrollment.getStudentId();
    this.courseId       = enrollment.getCourseId();
    this.enrollmentDate = enrollment.getEnrollmentDate();
    this.status         = enrollment.getStatus();
}
```

The copy constructor is useful when you want to create a new independent copy of an object
without sharing the same reference. Two constructors with different parameter lists in the
same class is also a demonstration of **constructor overloading**.

---

## 9. Soft Delete Design

When a student or course is deactivated, the record is **not removed** from the `ArrayList`.
Instead, the `active` boolean field is set to `false`. This is called a soft delete:

```java
// Student.java has an active field
private boolean active;

// Deactivate simply flips the flag - data is never removed
public void setActive(boolean active) {
    this.active = active;
}

// In StudentService - toggle the active flag
public void activateDeactivateStudent(List<Student> studentList, int id) {
    for (Student s : studentList) {
        if (s.getId() == id) {
            s.setActive(!s.isActive());   // toggle
            System.out.println("Student status updated: " + s);
            return;
        }
    }
    throw new EntityNotFoundException("Student with ID " + id + " not found.");
}
```

### Why Soft Delete?

1. **Data integrity** - Enrollment records reference `studentId`. A hard delete would leave
   enrollments pointing to a student that no longer exists in the list.
2. **Reversibility** - A deactivated student can be reactivated with no data loss.
3. **Audit trail** - Historical records are preserved.
4. **Real-world accuracy** - Educational institutions rarely permanently erase student records.

---

## 10. Clean Code Principles

| Principle             | How Applied in LearnTrack                                       |
|-----------------------|-----------------------------------------------------------------|
| Meaningful Names      | addStudent, searchStudentById, enrollStudentInCourse            |
| Single Responsibility | Each service class handles exactly one entity type              |
| Separation of Concerns| UI layer only displays and reads; service layer only processes  |
| Encapsulation         | All entity fields are private with public getters/setters       |
| DRY Principle         | Common Person fields defined once, reused via inheritance       |
| Fail-Fast             | EntityNotFoundException thrown immediately when entity missing  |
| Enum for Constants    | Status enum instead of raw String values                        |
| Copy Constructor      | Enrollment shows constructor overloading clearly                |

---

*docs/Design_Notes.md | LearnTrack | Airtribe Java Cohort | Priyanka Mandapati*
