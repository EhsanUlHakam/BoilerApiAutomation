Feature: 001 Login Respond.io

  Scenario Outline: Login to Respond.io Application
    When I hit the post request of respond.io login api with following <email> and <password>

    Examples:
      | email                   | password     |
      | "farazali756@gmail.com" | "C0c0m0_123" |