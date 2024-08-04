package e2e.model;

import java.util.ArrayList;
import java.util.List;

public class AddUserToWorkspaceModel {

    private String ownership = "manager";
    private List<String> permissions = new ArrayList<>(); // Initialize with an empty list
    private String email;

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
