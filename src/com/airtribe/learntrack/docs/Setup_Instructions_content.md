# Setup Instructions
## LearnTrack - Java Development Environment Setup Guide

> **File:** `docs/Setup_Instructions.md` | **Project:** LearnTrack | **Author:** Priyanka Mandapati

---

## Table of Contents

1. [Overview](#1-overview)
2. [JDK Version Used](#2-jdk-version-used)
3. [Installing JDK 11](#3-installing-jdk-11)
4. [Setting Environment Variables](#4-setting-environment-variables)
5. [IDE Setup - IntelliJ IDEA](#5-ide-setup---intellij-idea)
6. [Hello World Verification](#6-hello-world-verification)
7. [Troubleshooting](#8-troubleshooting)

---

## 1. Overview

This document covers the complete setup required to compile and run the LearnTrack project.
It includes JDK installation, environment variable configuration, IDE setup, and a Hello World
verification to confirm your Java installation is working correctly before running the project.

---

## 2. JDK Version Used

This project was built and tested using **OpenJDK 11.0.32.1 (Eclipse Adoptium Temurin)** on Windows.

| Property          | Value                                                         |
|-------------------|---------------------------------------------------------------|
| JDK Version       | **OpenJDK 11.0.32.1**                                         |
| Distribution      | Eclipse Adoptium Temurin-11.0.32.1+1                          |
| JVM Type          | OpenJDK 64-Bit Server VM (mixed mode)                         |
| OS                | Windows (C:\Users\ADMIN)                                      |
| IDE               | IntelliJ IDEA 2026.2.3                                        |
| JDK Install Path  | C:\Program Files\Eclipse Adoptium\jdk-11.0.32.101-hotspot\   |
| Download Source   | https://adoptium.net/                                         |
| Minimum Required  | JDK 11+                                                       |

### Screenshot — java -version Output

The following was confirmed by running `java -version` in the Node.js command prompt on Windows:

```
C:\Users\ADMIN>java -version
openjdk version "11.0.32.1" 2026-08-18
OpenJDK Runtime Environment Temurin-11.0.32.1+1 (build 11.0.32.1+1)
OpenJDK 64-Bit Server VM Temurin-11.0.32.1+1 (build 11.0.32.1+1, mixed mode)

C:\Users\ADMIN>
```

> **Screenshot 1 — Java Version Verified**
> ![java-version](../docs/screenshots/java-version.png)
> *(Terminal showing OpenJDK 11.0.32.1 Temurin on Windows)*

---

## 3. Installing JDK 11

### Download Eclipse Adoptium Temurin JDK 11 (Recommended)

Eclipse Adoptium (formerly AdoptOpenJDK) provides free, production-ready OpenJDK builds.
This is the exact distribution used in this project.

1. Go to **https://adoptium.net/**
2. Select **Temurin 11 (LTS)** from the version dropdown
3. Select your OS (**Windows x64**)
4. Download the `.msi` installer
5. Run the installer — it automatically sets `JAVA_HOME` and updates `PATH` on Windows

### Verify Installation After Installing

Open a new command prompt and run:

```
C:\Users\ADMIN>java -version
openjdk version "11.0.32.1" 2026-08-18
OpenJDK Runtime Environment Temurin-11.0.32.1+1 (build 11.0.32.1+1)
OpenJDK 64-Bit Server VM Temurin-11.0.32.1+1 (build 11.0.32.1+1, mixed mode)
```

If you see output similar to the above, your JDK is installed and working correctly.

```

---

## 4. Setting Environment Variables

> **Note for Windows users:** If you used the Adoptium `.msi` installer,
> `JAVA_HOME` and `PATH` are set automatically. Skip to Step 4.3 to verify.

### 4.1 Windows — Manual Setup

1. Right-click **This PC** -> Properties -> Advanced system settings
2. Click **Environment Variables**
3. Under **System variables**, click **New**:
   - Variable name:  `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-11.0.32.101-hotspot`
4. Find **PATH** in System variables -> Edit -> New -> Add: `%JAVA_HOME%\bin`
5. Click OK on all dialogs

### 4.2 macOS / Linux

Add to `~/.zshrc` (macOS) or `~/.bashrc` (Linux):

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

# Apply
source ~/.zshrc
```

### 4.3 Verify Environment Variables

Open a **new** terminal/command prompt and run:

```cmd
echo %JAVA_HOME%
REM Expected: C:\Program Files\Eclipse Adoptium\jdk-11.0.32.101-hotspot

java -version
REM Expected: openjdk version "11.0.32.1" 2026-08-18 ...
```

---

## 5. IDE Setup - IntelliJ IDEA

This project was developed using **IntelliJ IDEA 2026.2.3**. The Community Edition (free) works perfectly.

### 5.1 Download and Install IntelliJ IDEA

1. Go to https://www.jetbrains.com/idea/download/
2. Select **Community Edition** (free) for your OS
3. Install with default settings

### 5.2 Configure JDK in IntelliJ IDEA

1. Open IntelliJ IDEA -> **File -> Project Structure** (`Ctrl+Alt+Shift+S`)
2. Go to **SDKs** -> click **+** -> **Add JDK**
3. Browse to: `C:\Program Files\Eclipse Adoptium\jdk-11.0.32.101-hotspot`
4. Click **Apply** and **OK**
5. Go to **Project Settings -> Project** -> Set **Project SDK** to the JDK 11 you just added

### 5.3 Open the LearnTrack Project

Once JDK is configured:

1. **File -> Open** -> navigate to `C:\Users\ADMIN\Documents\priyanka Java\Java-Assignments`
2. IntelliJ indexes the project automatically
3. The project tree looks like this:

```
Java-Assignments
+-- .idea
+-- out
+-- src
|   +-- com.airtribe.learntrack.docs
|   +-- com.airtribe.learntrack.entity
|   +-- com.airtribe.learntrack.exception
|   +-- com.airtribe.learntrack.service
|   +-- com.airtribe.learntrack.ui
|   +-- com.airtribe.learntrack.util
|   +-- HelloWorld.java
+-- README.md
```

> **Screenshot 2 — IntelliJ IDEA Project Structure (your actual setup)**
> ![intellij-helloworld](../docs/screenshots/intellij-helloworld.png)
> *(Shows project tree and HelloWorld.java running successfully in IntelliJ IDEA 2026.2.3)*

---

## 6. Hello World Verification

### 6.1 The HelloWorld File

A `HelloWorld.java` file exists at the root of the `src` folder in your project,
as visible in the IntelliJ project tree above.

```java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

### 6.2 Running HelloWorld in IntelliJ IDEA

As confirmed in the screenshot:

1. Open IntelliJ IDEA with the project loaded
2. Navigate to `HelloWorld.java` in the `src` folder (visible at root level in project tree)
3. Click the green **Run** triangle next to line 3 (`public static void main`)
4. The **Run panel** at the bottom shows:

```
"C:\Program Files\Eclipse Adoptium\jdk-11.0.32.101-hotspot\bin\java.exe"
"-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2026.2.3\lib\idea_rt.jar=6190..."
Hello World!

Process finished with exit code 0
```

> **This confirms:**
> - JDK 11 (Adoptium Temurin) is correctly configured in IntelliJ
> - The project compiles and runs without errors
> - Output: `Hello World!` with `exit code 0`

### 6.3 Running HelloWorld via Terminal

```cmd
REM Navigate to the src directory
cd "C:\Users\ADMIN\Documents\priyanka Java\Java-Assignments\src"

REM Compile
javac HelloWorld.java

REM Run
java HelloWorld

REM Expected output:
REM Hello World!
```

---

## 7. Troubleshooting

| Error                          | Cause                                 | Fix                                                                    |
|--------------------------------|---------------------------------------|------------------------------------------------------------------------|
| `'java' is not recognized`     | JAVA_HOME / PATH not set              | Re-run Adoptium installer or manually set JAVA_HOME and PATH           |
| `javac: file not found`        | Wrong directory or filename typo      | Check you are in the correct folder (use `dir` on Windows to confirm)  |
| `ClassNotFoundException`       | Wrong classpath or package name       | Ensure you compile from the `src` folder root                          |
| `illegal start of expression`  | Syntax error in Java source           | Check for missing braces `{}` or semicolons `;`                        |
| `Cannot find symbol`           | Missing import or wrong class name    | Add the correct `import` statement at the top of the file              |
| IntelliJ: no SDK configured    | JDK not set in IntelliJ project       | File -> Project Structure -> SDKs -> Add JDK 11 (Adoptium path)       |
| IntelliJ: red imports / errors | Wrong SDK version selected            | Ensure Project SDK is set to JDK 11, not a newer or older version      |
| `exit code 1` on run           | Compilation or runtime error in code  | Read the error in the Run panel and fix the highlighted line            |

---

*docs/Setup_Instructions.md | LearnTrack | Airtribe Java Cohort | Priyanka Mandapati*
