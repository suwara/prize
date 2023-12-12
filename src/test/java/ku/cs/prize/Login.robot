*** Settings ***
Documentation     Test Login
Library           SeleniumLibrary

*** Variables ***
${Home URL}      http://localhost:8090
${BROWSER}        Chrome

*** Test Cases ***
Valid Login
    Open Browser To Home Page
    Open Login Page
    Input Username    test
    Input Password    test
    Submit Credentials
    Welcome Page Should Be Open
    [Teardown]    Close Browser

*** Keywords ***
Open Browser To Home Page
    Open Browser    ${Home URL}    ${BROWSER}
    Title Should Be    Home Page

Open Login Page
    Click Link    เข้าสู่ระบบ
    Title Should Be    Login Page

Input Username
    [Arguments]    ${username}
    Input Text    username   ${username}

Input Password
    [Arguments]    ${password}
    Input Text    password    ${password}

Submit Credentials
    Click Button    submit-button

Welcome Page Should Be Open
    Title Should Be    Home Page