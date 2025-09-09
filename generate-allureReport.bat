@echo off
REM Paths
set RESULTS_DIR=%cd%\Test_out\allure-results
set REPORT_DIR=%cd%\Test_out\allure-report

REM Generate single-file report
allure generate "%RESULTS_DIR%" -o "%REPORT_DIR%" --clean --single-file
