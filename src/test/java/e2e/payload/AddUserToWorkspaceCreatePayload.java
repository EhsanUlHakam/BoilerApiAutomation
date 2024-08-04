package e2e.payload;

import e2e.model.AddUserToWorkspaceModel;

public class AddUserToWorkspaceCreatePayload {
    public static AddUserToWorkspaceModel createPayload(String email) {
        AddUserToWorkspaceModel addUserToWorkspaceModel = new AddUserToWorkspaceModel();
        addUserToWorkspaceModel.setEmail(email);
        return addUserToWorkspaceModel;
    }
}
