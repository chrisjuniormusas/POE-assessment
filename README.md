# Java Maven Login Application

This repository contains a small Java console application built around the requirements in the assignment prompt. The solution is structured for **GitHub**, **Maven**, and **automated unit testing**.

## What this project demonstrates

The application shows how to:

- create **classes**, **methods**, and core **object-oriented programming constructs**
- use **decisions** for validation and authentication
- accept **input** and return **output** through a console application
- validate a **username**, **password**, and **South African cell phone number**
- run **JUnit 5** tests through **Maven**
- support **GitHub Actions** continuous integration

## Project structure

```text
java-login-maven/
├── .github/
│   └── workflows/
│       └── maven.yml
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/github/chrisjunior/loginapp/
    │           ├── App.java
    │           ├── Login.java
    │           └── User.java
    └── test/
        └── java/
            └── com/github/chrisjunior/loginapp/
                └── LoginTest.java
```

## Main classes

### `User`
Stores the registered user's:
- first name
- last name
- username
- password
- cell phone number

### `Login`
Contains the business logic required by the assignment:
- `checkUserName()`
- `checkPasswordComplexity()`
- `checkCellPhoneNumber()`
- `registerUser()`
- `loginUser(String enteredUsername, String enteredPassword)`
- `returnLoginStatus()`

It also includes helper methods that return the exact validation messages required for testing:
- `getUsernameMessage()`
- `getPasswordMessage()`
- `getCellPhoneMessage()`

### `App`
A console-based entry point that:
1. accepts user input
2. validates the registration details
3. allows the user to log in
4. displays the required output messages

## Validation rules implemented

### Username
A valid username must:
- contain an underscore (`_`)
- be no more than **5 characters** long

### Password
A valid password must:
- be at least **8 characters** long
- contain at least **1 uppercase letter**
- contain at least **1 number**
- contain at least **1 special character**

### South African cell phone number
A valid cell phone number must:
- start with **`+27`**
- contain **9 digits after `+27`**
- example: `+27838968976`

The regex rule is documented in the source code with references to the relevant South African numbering and ITU E.164 guidance.

## How to run the application

### 1. Compile the project

```bash
mvn clean compile
```

### 2. Run the tests

```bash
mvn test
```

### 3. Run the console application

```bash
mvn exec:java -Dexec.mainClass="com.github.chrisjunior.loginapp.App"
```

If your environment does not have the Maven Exec plugin configured globally, you can also run it from your IDE such as NetBeans or IntelliJ.

## Unit tests included

The test class `LoginTest` uses the **exact data values** requested in the assignment, including:

- Username: `kyl_1`
- Invalid username: `kyle!!!!!!!`
- Valid password: `Ch&&sec@ke99!`
- Invalid password: `password`
- Valid phone number: `+27838968976`
- Invalid phone number: `08966553`

The tests cover:
- `assertTrue()` and `assertFalse()` checks
- `assertEquals()` checks for the exact expected messages
- successful and failed login scenarios
- successful and failed registration scenarios

## GitHub Actions

A GitHub Actions workflow is included in:

```text
.github/workflows/maven.yml
```

This workflow:
- checks out the repository
- installs Java
- runs `mvn test`

That means every push and pull request can automatically run the tests.

## Notes for NetBeans

This is a **standard Maven project**, so NetBeans should detect it automatically.

To open it in NetBeans:
1. Open NetBeans
2. Choose **Open Project**
3. Select the `java-login-maven` folder
4. NetBeans will load the Maven structure and test classes

To run tests in NetBeans:
- right-click the project and choose **Test**
- or right-click `LoginTest.java` and choose **Test File**

## Design choices

- The project is separated into small classes for readability and testability.
- Validation logic is kept inside `Login`, which makes unit tests straightforward.
- The `User` class is a simple data holder.
- The application uses descriptive comments to make the repository easy to understand for lecturers, teammates, or future employers reviewing the code on GitHub.

## Expected outcome

By using this project, it will demonstrate that I am able to:
- apply OOP principles in Java
- use decisions and validation rules
- build a console application with input and output
- create clean, testable code
- use Maven and GitHub for automated testing
