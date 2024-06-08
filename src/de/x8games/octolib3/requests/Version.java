package de.x8games.octolib3.requests;

import de.x8games.octolib3.OPiRequest;
import de.x8games.octolib3.tools.JsonStatic;
import org.json.JSONObject;





public class Version extends OPiRequest {

    public Version() {
        super(Method.GET, "/api/version");
    }

    public String toString() {
        return text;
    }

    private String api;
    private String server;
    private String text;

    public void parseResponse(JSONObject response) {
        api = JsonStatic.jsonString(response, "api");
        server = JsonStatic.jsonString(response, "server");
        text = JsonStatic.jsonString(response, "text");
    }

    public String getApi() {
        return api;
    }

    public String getServer() {
        return server;
    }

    public String getText() {
        return text;
    }

}
