package de.x8games.octolib3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;


/**
 * führt einen Request an die API aus
 */
public abstract class OPiRequest {

    protected Logger logger = LogManager.getLogger("octolib3");

    public enum Method {
        GET,
        POST
    }


    /**
     * legt die nötigen Parameter für den Request fest
     * @param newMethod Methode für den Request
     * @param newPath API-Pfad
     */
    protected OPiRequest(Method newMethod, String newPath) {
        path = newPath;
        method = newMethod;
        params = new JSONObject();
    }



    /**
     * verarbeitet die Antwort des Druckers
     * @param response vom Drucker zurück gegebene Antwort
     */
    public abstract void parseResponse(JSONObject response);



    private String path;
    private Method method;
    private JSONObject params;
    private int responseCode;
    private String responseMessage;



    public String getPath() { return path; }
    public Method getMethod() { return method; }
    public JSONObject getParams() { return params; }
    public boolean hasParams() { return params.length() != 0; }
    public int getResponseCode() { return responseCode; }
    public void setResponseCode(int responseCode) { this.responseCode = responseCode; }
    public String getResponseMessage() { return responseMessage; }
    public void setResponseMessage(String responseMessage) { this.responseMessage = responseMessage; }

    public boolean hasError() { return responseCode >= 400; }

}
