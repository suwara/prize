*** Settings ***
Documentation     Simple example using SeleniumLibrary.
Library           SeleniumLibrary

*** Variables ***
${LOGIN URL}      http://localhost:8090
${BROWSER}        Chrome

*** Test Cases ***
Try to Signup
    Open Browser To Home Page
    Open Sign Up Page
    Input Username    test
    Input Password    test
    Submit Register
    Expected Signup Fail
    
Try to Signup 2
    Input Username    test09
    Input Password    test
    Submit Register
    Success Register
    [Teardown]    Close Browser

*** Keywords ***
Open Browser To Home Page
    Open Browser    ${LOGIN URL}    ${BROWSER}
    Title Should Be    Home Page

Open Sign Up Page
    Click Link    ลงทะเบียน
    Title Should Be    Signup Page

Input Username
    [Arguments]    ${username}
    Input Text    username  ${username}

Input Password
    [Arguments]    ${password}
    Input Text    password    ${password}

Submit Register
    Click Button    submit-button

Expected Signup Fail
    Title Should Be    Signup Page

Success Register
    Title Should Be    Login Page