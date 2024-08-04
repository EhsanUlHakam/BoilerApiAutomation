package e2e.payload;

import e2e.model.RespondLoginModel;

public class RespondLoginCreatePayload {
    public static RespondLoginModel createPayload(String email, String password) {
        RespondLoginModel respondLoginModel = new RespondLoginModel();
        respondLoginModel.setEmail(email);
        respondLoginModel.setPassword(password);
        return respondLoginModel;
    }
}
