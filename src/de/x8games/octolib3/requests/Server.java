package de.x8games.octolib3.requests;

import de.x8games.octolib3.OPiRequest;
import de.x8games.octolib3.tools.JsonStatic;
import org.json.JSONObject;


/**
 * ungetestet, da aktuell nur 1.4.2 vorhanden - abarbeitung nach Webseite
 */
public class Server extends OPiRequest {

    public Server() {
        super(Method.GET, "/api/server");
    }

    @Override
    public void parseResponse(JSONObject response) {
        version = JsonStatic.jsonString(response, "version");
        safemode = JsonStatic.jsonString(response, "safemode");
    }


    public String getVersion() {
        return version;
    }

    public String getSafemode() {
        return safemode;
    }

    private String version;
    private String safemode;


}
