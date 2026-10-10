# ☕ JVM Basics
## Understanding JDK, JRE, JVM — The Core of Java Execution

> **File:** `docs/JVM_Basics.md` | **Project:** LearnTrack | **Author:** Priyanka Mandapati

---

## 📋 Table of Contents

1. [Introduction](#1-introduction)
2. [The Big Picture](#2-the-big-picture--how-they-relate)
3. [JVM — Java Virtual Machine](#3-jvm--java-virtual-machine)
4. [JRE — Java Runtime Environment](#4-jre--java-runtime-environment)
5. [JDK — Java Development Kit](#5-jdk--java-development-kit)
6. [JDK vs JRE vs JVM — Comparison Table](#6-jdk-vs-jre-vs-jvm--quick-comparison)
7. [What Is Bytecode?](#7-what-is-bytecode)
8. [Write Once Run Anywhere — WORA](#8-write-once-run-anywhere-wora)
9. [JIT Compiler — Making Java Fast](#9-jit-compiler--making-java-fast)
10. [JVM Memory Model](#10-jvm-memory-model-simplified)
11. [Summary](#11-summary)

---

## 1. Introduction

One of Java's most powerful features is its **"Write Once, Run Anywhere"** philosophy.
To truly understand what this means and how Java achieves it, we need to understand three
foundational concepts: the **JDK**, **JRE**, and **JVM**.

When you write a Java program and run it, there are multiple layers of software working
together behind the scenes. This document explains each layer in simple, clear language
with examples from the LearnTrack project.

---

## 2. The Big Picture — How They Relate

```
┌──────────────────────────────────────────────────────┐
│                        JDK                           │
│            (Java Development Kit)                    │
│                                                      │
│   ┌────────────────────────────────────────────┐    │
│   │                  JRE                       │    │
│   │        (Java Runtime Environment)          │    │
│   │                                            │    │
│   │   ┌──────────────────────────────────┐    │    │
│   │   │             JVM                  │    │    │
│   │   │     (Java Virtual Machine)       │    │    │
│   │   │    Executes Bytecode (.class)    │    │    │
│   │   └──────────────────────────────────┘    │    │
│   │   + Java Standard Libraries (java.*)      │    │
│   └────────────────────────────────────────────┘    │
│   + Compiler (javac) + Debugger (jdb) + Tools        │
└──────────────────────────────────────────────────────┘

Relationship:  JDK  ⊃  JRE  ⊃  JVM
```

> 💡 **Simple Analogy:**
> - **JDK** = Full Kitchen — you can cook AND eat
> - **JRE** = Dining Table — you can only eat, not cook
> - **JVM** = Your Mouth — actually processes and executes the food

---

## 3. JVM — Java Virtual Machine

### 3.1 What Is It?

The **Java Virtual Machine (JVM)** is the heart of Java's runtime environment.
It is a **software-based virtual machine** that:

- Reads and **executes** compiled Java bytecode (`.class` files)
- **Manages memory** (Heap, Stack, Method Area)
- Handles **garbage collection** (automatic memory cleanup)
- Provides **security** through bytecode verification
- **Translates** bytecode to native machine code via the JIT compiler

### 3.2 How It Works — Step by Step

```
Your Java Code  (Main.java)
         │
         │  javac  (Java Compiler — part of JDK)
         ▼
   Bytecode  (Main.class)             ← Platform-INDEPENDENT
         │
         │  JVM  (specific to your OS)
         ▼
   Native Machine Code                ← Platform-SPECIFIC
         │
         ▼
   Program executes on CPU
```

### 3.3 Key Components Inside the JVM

| Component         | What It Does                           | Simple Explanation              |
|-------------------|----------------------------------------|---------------------------------|
| Class Loader      | Loads `.class` files into memory       | Like a librarian fetching books |
| Bytecode Verifier | Checks bytecode is valid and safe      | Security guard at the door      |
| JIT Compiler      | Compiles hot bytecode to native code   | Speeds up frequently used code  |
| Heap              | Stores objects and class instances     | Big memory room for objects     |
| Stack             | Stores method calls & local variables  | LIFO stack of method frames     |
| Garbage Collector | Frees unused memory automatically      | Janitor cleaning unused space   |
| Method Area       | Stores class metadata and static vars  | Blueprint storage for classes   |

---

## 4. JRE — Java Runtime Environment

### 4.1 What Is It?

The **Java Runtime Environment (JRE)** is the package that contains everything needed
to **RUN** a compiled Java application. It is designed for **end users** — people who
want to run Java programs but don't need to write or compile them.

### 4.2 What JRE Contains

- ✅ **JVM** — the execution engine
- ✅ **Java Class Libraries** — pre-built code for common operations (`java.lang`, `java.util`, etc.)
- ✅ Supporting configuration files

### 4.3 Key Java Libraries Available in JRE

| Library      | Package      | Used For                                   |
|--------------|--------------|--------------------------------------------|
| Core Types   | `java.lang`  | String, Integer, Math, System, Object      |
| Collections  | `java.util`  | ArrayList, HashMap, List, Scanner          |
| Input/Output | `java.io`    | File reading/writing, streams              |
| Networking   | `java.net`   | HTTP connections, URLs                     |
| Date & Time  | `java.time`  | LocalDate, LocalDateTime (Java 8+)         |
| Exceptions   | `java.lang`  | RuntimeException, Exception hierarchy      |

> 📌 **NOTE:** From **Java 9 onwards**, the JRE is bundled within the JDK.
> Users can generate custom runtimes using `jlink`.

---

## 5. JDK — Java Development Kit

### 5.1 What Is It?

The **Java Development Kit (JDK)** is the complete software development package for
**building** Java applications. It is what developers install. The JDK includes
everything in the JRE, plus additional tools for writing, compiling, and debugging code.

### 5.2 What JDK Contains

| Tool             | Command    | Purpose                                         |
|------------------|------------|-------------------------------------------------|
| Java Compiler    | `javac`    | Compiles `.java` files → `.class` bytecode      |
| Java Launcher    | `java`     | Runs compiled `.class` files via the JVM        |
| Debugger         | `jdb`      | Debugs Java programs from terminal              |
| Archiver         | `jar`      | Packages `.class` files into `.jar` archives    |
| Documentation    | `javadoc`  | Generates HTML API documentation                |
| Class Viewer     | `javap`    | Disassembles `.class` bytecode to readable form |
| JShell (Java 9+) | `jshell`   | Interactive Java REPL (try code instantly)      |
| Runtime          | JRE        | Executes Java programs (included in JDK)        |

### 5.3 Using JDK in LearnTrack

```bash
# Step 1: Write Java source code
# File: src/com/airtribe/learntrack/ui/Main.java

# Step 2: Compile with javac (JDK tool) → creates .class bytecode
javac -d out src/com/airtribe/learntrack/ui/Main.java

# Step 3: Run with java (invokes JVM internally)
java -cp out com.airtribe.learntrack.ui.Main

# Behind the scenes:
# javac (JDK)  → compiles .java  → .class (bytecode)
# java  (JVM)  → loads .class   → executes on your machine
```

---

## 6. JDK vs JRE vs JVM — Quick Comparison

| Feature            | JVM                       | JRE                        | JDK                          |
|--------------------|---------------------------|----------------------------|------------------------------|
| Full Form          | Java Virtual Machine      | Java Runtime Environment   | Java Development Kit         |
| Purpose            | Execute bytecode          | Run Java programs          | Develop Java programs        |
| Contains           | Execution engine only     | JVM + Libraries            | JRE + Dev Tools + Compiler   |
| Who Needs It       | Internal (part of JRE)    | End users                  | Developers                   |
| Has Compiler?      | No                        | No                         | Yes (`javac`)                |
| Has JVM?           | It IS the JVM             | Yes (includes JVM)         | Yes (includes JRE + JVM)     |
| Runs `.class`?     | Yes                       | Yes                        | Yes                          |
| Platform-specific? | Yes (per OS)              | Yes (per OS)               | Yes (per OS)                 |

---

## 7. What Is Bytecode?

### 7.1 Definition

**Bytecode** is the **intermediate, compiled form** of a Java program. It is:

- ❌ **NOT** machine code — not binary instructions for a specific CPU
- ❌ **NOT** source code — not human-readable `.java` text
- ✅ A set of instructions designed specifically for the **JVM** to understand and execute

When you compile a `.java` file using `javac`, the output is a `.class` file containing
bytecode. The JVM on any target machine can execute this bytecode.

### 7.2 Bytecode Example

```java
// Source code — Human-readable (Main.java)
public class Main {
    public static void main(String[] args) {
        int x = 10;
        System.out.println(x);
    }
}
```

```
// After javac → Main.class (bytecode viewed with: javap -c Main)

public static void main(java.lang.String[]);
  Code:
     0: bipush        10       ← push integer 10 onto operand stack
     2: istore_1               ← store value in local variable slot 1
     3: getstatic   #7         ← get System.out reference
     6: iload_1                ← load variable from slot 1
     7: invokevirtual #13      ← call println() method
    10: return
```

### 7.3 Bytecode vs Machine Code vs Source Code

| Type         | Example        | Read By              | Platform-Specific?              |
|--------------|----------------|----------------------|---------------------------------|
| Source Code  | `.java` files  | Human developer      | No                              |
| Bytecode     | `.class` files | JVM (any platform)   | No — runs anywhere with a JVM   |
| Machine Code | Binary 0s & 1s | CPU directly         | Yes — specific to CPU/OS        |

> 🔑 **KEY INSIGHT:** The same `.class` bytecode file can run on Windows, macOS, or Linux —
> as long as a compatible JVM is installed. This is the foundation of Java's WORA promise.

---

## 8. "Write Once, Run Anywhere" (WORA)

### 8.1 What Does It Mean?

**"Write Once, Run Anywhere" (WORA)** is Java's most famous promise, coined by Sun Microsystems
in the 1990s. It means a Java program written and compiled on one operating system can be
**run on any other operating system** — without rewriting or recompiling the code.

You write your code once → compile once → the `.class` bytecode runs on any machine
that has a compatible JVM installed.

### 8.2 How WORA Works

```
DEVELOPER MACHINE (Windows)             OTHER MACHINES
┌─────────────────────────┐
│  1. Write Main.java     │    ┌────────────────────────────────────────┐
│                         │    │  Same Main.class bytecode runs on:     │
│  2. javac Main.java     │    │                                        │
│     ↓                   │    │  ● Windows  (Windows JVM)              │
│     Main.class          │───▶│  ● macOS    (macOS JVM)                │
│     (Bytecode)          │    │  ● Linux    (Linux JVM)                │
│                         │    │  ● Android  (Dalvik / ART JVM)         │
│  3. java Main (runs)    │    └────────────────────────────────────────┘
└─────────────────────────┘

KEY: Each platform's JVM translates the same bytecode into that platform's
     machine code. You as a developer don't have to do anything extra.
```

### 8.3 Why This Is Important

| Benefit              | Explanation                                                    |
|----------------------|----------------------------------------------------------------|
| No Platform Lock-in  | Java apps work across different OS without rewriting           |
| Portability          | Ship the same `.jar` file to any customer on any OS            |
| Consistency          | Behavior is predictable and uniform across all environments    |
| Maintainability      | One codebase instead of platform-specific versions             |

### 8.4 The Clever Design Behind WORA

1. The **JVM is platform-specific** — there is a different JVM binary for Windows, macOS, Linux.
2. Your **Java bytecode is platform-independent** — it does not care which OS runs it.
3. Each JVM translates the universal bytecode into machine instructions for its specific platform.
4. As a developer, you **only write Java** — the JVM handles all the platform differences.

> 💬 **Analogy — UN Interpreter:**
>
> Imagine you speak English and need to address an audience of French, Spanish, and Japanese
> speakers. You speak once in English — and interpreters on each side translate your words.
>
> - **Java bytecode** = Your English speech (universal, written once)
> - **JVM on each OS** = The interpreter (translates to the local machine language)
> - **You, the developer** = Only need to know one language (Java)

---

## 9. JIT Compiler — Making Java Fast

Early Java was considered slow because bytecode was **interpreted line-by-line** at runtime.
The **JIT (Just-In-Time) Compiler** was introduced to fix this:

```
Without JIT:
  Bytecode → Interpreter reads each instruction one-by-one → slow, repetitive

With JIT Compiler:
  Bytecode → JVM detects "hot" code (frequently executed methods/loops)
           → JIT compiles hot bytecode to native machine code ONCE
           → Native code is cached and reused → much faster!

Performance Result:
  First execution  : Slightly slower (JIT compilation overhead)
  Later executions : Significantly faster (native machine code reused)
```

This is why **Java performance is comparable to C/C++** for long-running applications —
the JIT compiler continuously optimizes the most-used parts of your program.

---

## 10. JVM Memory Model (Simplified)

```
┌────────────────────────────────────────────────────────┐
│                    JVM Memory Areas                    │
├────────────────────────────────────────────────────────┤
│  HEAP                                                  │
│  ┌──────────────────────────────────────────────────┐ │
│  │  All objects and arrays live here                 │ │
│  │  new Student(...)     ← created in Heap          │ │
│  │  new ArrayList<>()    ← created in Heap          │ │
│  │  Managed by Garbage Collector automatically      │ │
│  └──────────────────────────────────────────────────┘ │
│                                                        │
│  STACK  (one per thread)                               │
│  ┌──────────────────────────────────────────────────┐ │
│  │  Method call frames and local variables           │ │
│  │  int choice = scanner.nextInt()  ← in Stack      │ │
│  │  LIFO order — each method call adds a frame       │ │
│  └──────────────────────────────────────────────────┘ │
│                                                        │
│  METHOD AREA  (Metaspace in Java 8+)                   │
│  ┌──────────────────────────────────────────────────┐ │
│  │  Class definitions, static fields, static methods │ │
│  │  IdGenerator.studentIdCounter  ← stored here     │ │
│  │  InputValidator static methods ← stored here     │ │
│  └──────────────────────────────────────────────────┘ │
└────────────────────────────────────────────────────────┘
```

> 📌 **LearnTrack Connection — Where Our Data Lives:**
>
> | Data                              | Memory Area  |
> |-----------------------------------|--------------|
> | `new Student(...)` objects        | Heap         |
> | `ArrayList<Student>` in services  | Heap         |
> | `int choice` in menu loop         | Stack        |
> | `IdGenerator.studentIdCounter`    | Method Area  |
> | `InputValidator` static methods   | Method Area  |

---

## 11. Summary

| Concept           | One-Line Summary                                             |
|-------------------|--------------------------------------------------------------|
| JDK               | Everything a developer needs: compiler + tools + JRE         |
| JRE               | What end users need to run Java: JVM + libraries             |
| JVM               | The engine that executes Java bytecode on any platform       |
| Bytecode          | Platform-neutral compiled form of Java code (`.class` files) |
| WORA              | Write code once, run it on any OS that has a JVM             |
| JIT Compiler      | Makes Java fast by compiling hot bytecode to native code     |
| Heap              | Memory area where all Java objects live at runtime           |
| Garbage Collector | Automatically frees memory of unused objects                 |

> 🎓 **Final Thought:**
>
> Java's genius lies in this elegant three-step design:
>
> **Developer writes `.java`**  →  `javac` compiles to `.class` (bytecode)  →  **JVM on any OS executes it**
>
> This separation between source code, bytecode, and machine code is what makes Java one of
> the most portable, widely-used programming languages in the world — powering everything from
> Android apps to large-scale enterprise banking systems.

---

*docs/JVM_Basics.md | LearnTrack | Airtribe Java Cohort | Priyanka Mandapati*
