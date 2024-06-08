package de.x8games.octolib3.requests;

import de.x8games.octolib3.OPiRequest;
import de.x8games.octolib3.tools.JsonStatic;
import org.json.JSONObject;

import java.util.List;



public class Connection extends OPiRequest {

    public Connection() {
        super(Method.GET, "/api/connection");
    }

    public String toString() {
        return state + "|" + port + "|" + baudrate + "|" + profile;
    }

    @Override
    public void parseResponse(JSONObject response) {
        if (response.has("current")) parseResponseCurrent(response.getJSONObject("current"));
        if (response.has("options")) parseResponseOptions(response.getJSONObject("options"));
    }
    private void parseResponseCurrent(JSONObject response) {
        state = JsonStatic.jsonString(response, "state");
        port = JsonStatic.jsonString(response, "port");
        profile = JsonStatic.jsonString(response, "printerProfile");
        baudrate = JsonStatic.jsonInteger(response, "baudrate");
    }
    private void parseResponseOptions(JSONObject response) {
        ports = JsonStatic.jsonArrayString(response, "ports");
        baudrates = JsonStatic.jsonArrayInteger(response, "baudrates");
        portPreference = JsonStatic.jsonString(response, "portPreference");
        baudratePreference = JsonStatic.jsonInteger(response, "baudratePreference");
        profilePreference = JsonStatic.jsonString(response, "printerProfilePreference");
        autoconnect = JsonStatic.jsonBoolean(response, "autoconnect");
    }



    private String state;
    private String port;
    private String profile;
    private int baudrate;
    private List<String> ports;
    private List<Integer> baudrates;
    private String portPreference;
    private int baudratePreference;
    private String profilePreference;
    private boolean autoconnect;



    public String getState() {
        return state;
    }

    public String getPort() {
        return port;
    }

    public String getProfile() {
        return profile;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public List<String> getPorts() {
        return ports;
    }

    public List<Integer> getBaudrates() {
        return baudrates;
    }

    public String getPortPreference() {
        return portPreference;
    }

    public int getBaudratePreference() {
        return baudratePreference;
    }

    public String getProfilePreference() {
        return profilePreference;
    }

    public boolean isAutoconnect() {
        return autoconnect;
    }

}
