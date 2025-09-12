# Hybrid Test Automation Framework (Selenium + Java)

[![Execution TestScenarios Pipeline](https://github.com/nour-allah-khaled/VOIS_Automation_Challenge/actions/workflows/Execution%20TestScenarios%20Pipeline.yml/badge.svg)](https://github.com/nour-allah-khaled/VOIS_Automation_Challenge/actions/workflows/Execution%20TestScenarios%20Pipeline.yml)

This project is a hybrid test automation framework built using **Selenium WebDriver with Java**, following best practices in **object-oriented design** and **modular architecture**.  
It combines **data-driven testing** with reusable components to ensure high scalability, flexibility, and maintainability.

## ✅ Framework Components

| Module / Layer           | Responsibility                                                                 |
|--------------------------|---------------------------------------------------------------------------------|
| **Driver Management**     | Manages browser drivers using Factory pattern (`Chrome`, `Firefox`, `Edge`).   |
| **Configuration Loader**  | Loads test configs and environment data from `.properties` & `JSON` files.     |
| **Data Reader**           | Handles test data extraction from external sources (JSON, properties).         |
| **Page Objects**          | Implements the Page Object Model (POM) for modular and maintainable code.      |
| **Actions Layer**         | Contains reusable methods for UI interactions (clicks, input, scrolling). |
| **Assertion Layer**       | Wraps TestNG assertions with custom hard/soft assertion handlers.              |
| **Listeners & Reporting** | Integrates TestNG listeners + Allure for detailed HTML reporting with screenshots. |
| **Parallel Execution**    | Supports parallel test runs via TestNG and Thread-safe WebDriver handling.     |
| **Cross-Browser Support** | Executes tests on Chrome, Firefox, and Edge based on config.                   |
| **CI/CD Pipeline**        | GitHub Actions workflow for automated test execution and reporting.            |

---
## 📂 Project Structure
```plaintext
VOIS_Automation_Challenge/
├── .github/
│   └── workflows/
│       └── Execution TestScenarios Pipeline.yml
├── .idea/
├── .mvn/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── challenge/
│                   ├── assertions/
│                   │   ├── BaseAssertion.java
│                   │   ├── HardAssertion.java
│                   │   └── SoftAssertion.java
│                   ├── datareader/
│                   │   ├── JsonReader.java
│                   │   └── PropertyReader.java
│                   ├── drivers/
│                   │   ├── AbstractDriver.java
│                   │   ├── Browser.java
│                   │   ├── ChromeDriverFactory.java
│                   │   ├── EdgeDriverFactory.java
│                   │   ├── FirefoxDriverFactory.java
│                   │   ├── GUIFactory.java
│                   │   └── WebDriverProvider.java
│                   ├── listeners/
│                   │   └── TestNGListeners.java
│                   ├── media/
│                   │   └── ScreenShotMedia.java
│                   ├── pages/
│                   │   ├── Page_01.java
│                   │   ├── Page_02.java
│                   │   └── Page_03.java
│                   └── utils/
│                       ├── actions/
│                       │   ├── BrowserAction.java
│                       │   └── ElementAction.java
│                       └── logs/
│                           ├── AllureUtil.java
│                           ├── TimeManager.java
│                           └── WaitManager.java
├── src/
│   └── resources/
│       ├── META-INF.services/
│       │   └── org.testng.ITestNGListener
│       ├── test_data/
│       ├── allure.properties
│       ├── enviroment.properties
│       ├── log4j2.properties
│       ├── wait.properties
│       └── webapp.properties
├── test/
│   ├── java/
│   │   ├── parallel_execution/
│   │   │   ├── BaseClassTC.java
│   │   │   └── ParallelExecutionTC.java
│   │   └── tests/
│   │       └── TC_Page_01.java
│   └── resources/
│       └── Test_Data/
│           └── Data.json
├── target/
├── Test_out/
│   ├── allure-report/
│   │   └── index.html
│   ├── allure-results/
│   ├── Logs/
│   └── ScreenShots/
├── .gitattributes
├── .gitignore
├── generate-allureReport.bat
├── pom.xml
├── RunParallelExecution.xml
└── RunTest.xml
```
---
## 🚀 How to Run
Run tests using the following Maven commands (via `.bat` or terminal):

- **Main Test Execution:**
  ```bash
  mvn clean test -Dtest=TC_Page_01
  ```
- **Parallel Execution:**
  ```bash
  mvn -DsuiteXmlFile="RunParallelExecution.xml" -DexecutionType="LocalHeadless" clean test
  ```
- Allure Report is automatically generated via .bat using CMD.  
  You can view the latest **Parallel Execution Report** here:  
  [Download / View Allure Report](https://github.com/nour-allah-khaled/VOIS_Automation_Challenge/blob/main/Test_out/allure-report/index.html)
---
## 🖼️ Test Report Screenshots

Below are sample screenshots captured from the Allure report during test execution.

> ⚠️ **Note:** Some tests may not complete due to reCAPTCHA (`I'm not a robot`) blocking automated execution.

### 🔹 Main Test (TC_Page_01):

![Main Test Screenshot](https://github.com/nour-allah-khaled/VOIS_Automation_Challenge/blob/main/AllureResult_ScreenShots/Attachment_TC_Page_01/Main%20Test.png?raw=true)

### 🔹 Parallel Execution Test (ParallelExecutionTC):

![Parallel Execution Screenshot](https://github.com/nour-allah-khaled/VOIS_Automation_Challenge/blob/main/AllureResult_ScreenShots/Attachment_ParallelExecutionTC/Parallel%20Execution.png?raw=true)

---
