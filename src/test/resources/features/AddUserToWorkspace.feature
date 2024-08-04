Feature: Add User to Workspace

  Scenario Outline: To add user to workspace
    When I hit the post request to add user to workspace using <email>

    Examples:
      | email     |
      | "test@email.com" |