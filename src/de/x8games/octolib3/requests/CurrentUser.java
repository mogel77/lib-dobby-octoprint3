package de.x8games.octolib3.requests;

import de.x8games.octolib3.OPiRequest;
import de.x8games.octolib3.tools.JsonStatic;
import org.json.JSONObject;

import java.util.List;


public class CurrentUser extends OPiRequest {

    public CurrentUser() {
        super(Method.GET, "/api/currentuser");
    }

    @Override
    public void parseResponse(JSONObject response) {
        name = JsonStatic.jsonString(response, "name");
        groups = JsonStatic.jsonArrayString(response, "groups");
        permissions = JsonStatic.jsonArrayString(response, "permissions");
    }



    private String name;
    private List<String> groups;
    private List<String> permissions;



    public String getName() {
        return name;
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}

