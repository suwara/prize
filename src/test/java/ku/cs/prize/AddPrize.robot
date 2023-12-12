*** Settings ***
Documentation     Add Prizes more
Library           SeleniumLibrary

*** Variables ***
${Home URL}      http://localhost:8090
${BROWSER}        Chrome

*** Test Cases ***
Login
    Open Browser To Home Page
    Open Login Page
    Input Username    test3
    Input Password    test
    Submit Credentials
    Welcome Page Should Be Open

Add Prizes
    Open Prize Page
    Open Add Prize Page
    Input Day    7/8/2015
    Input Sources    Mobile Resturant
    Input Tournaments    แข่งกิน
    Input Level    ปลอบใจ
    Select From List By Value    type  ถ้วยรางวัล



#    [Teardown]    Close Browser

*** Keywords ***
Open Browser To Home Page
    Open Browser    ${Home URL}    ${BROWSER}
    Title Should Be    Home Page

Open Login Page
    Click Link    เข้าสู่ระบบ
    Title Should Be    Login Page

Open Prize Page
    Click Link    prizes-link
    Title Should Be    Show All Prizes Page

Open Add Prize Page
    Click Link    เพิ่มรายการ
    Title Should Be    Add Prizes Page

Input Day
    [Arguments]    ${day}
    Input Text    dayToGet    ${day}

Input Sources
    [Arguments]    ${sources}
    Input Text    sources   ${sources}

Input Tournaments
    [Arguments]    ${tournaments}
    Input Text    tournaments    ${tournaments}

Input Level
    [Arguments]    ${level}
    Input Text    level    ${level}

Input Namegiver
    [Arguments]    ${nameGiver}
    Input Text    nameGiver    ${nameGiver}

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