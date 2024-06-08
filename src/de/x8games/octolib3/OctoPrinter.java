package de.x8games.octolib3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.x8games.octolib3.events.*;

import javax.swing.*;


/**
 * @brief Klasse für den Zugriff auf einen Octo-printer
 */
public class OctoPrinter {

    protected Logger logger = LogManager.getLogger("octolib3");

    /**
     * Zugriff auf den lokalen Octo-Printer über 127.0.0.1
     * @param apiKey der nötige API-Key
     */
    public OctoPrinter(String apiKey) {
        this("http://127.0.0.1", apiKey);
    }

    /**
     * Zugriff auf einen Octo-Printer über eine spezifische IP
     * @param host IP des Octo-Printers
     * @param apiKey der nötige API-Key
     */
    public OctoPrinter(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }



    private final String host;
    private final String apiKey;


    /** synchrone Abarbeitung des API-Calls
     * @param command Befehl der an den Drucker gesendet wird
     */
    public void execute(OPiRequest command) {
        try {
            interalCall(command);
            raiseOnConnected();
        } catch(Exception ex) {
            logger.error(ex);
            raiseOnDisconnected();
        }
    }

    /**
     * führt einen asynchronen Callback aus, solange bis dieser Abgebrochen wird
     * @param callback der auszuführende Callback
     */
    public void execute(OPiCallback callback) {
        Thread t = new Thread(() -> {
            OPiRequest request = callback.getRequest();
            while(!callback.isAbort()) {
                try {
                    interalCall(request);
                    SwingUtilities.invokeLater(() -> {
                        callback.OnSuccessful(request);
                        raiseOnConnected();
                    });
                    Thread.sleep(callback.getTimeout());
                    if (callback.getTimeout() == 0) callback.abort();
                } catch (Exception ex) {
                    logger.error(ex);
                    SwingUtilities.invokeLater(() -> {
                        raiseOnDisconnected();
                    });
                    callback.OnFailedAsync(request);
                }
            }
        });
        t.start();
    }

    // region . TCP-Kram .
    private void interalCall(OPiRequest command) throws Exception {
        StringBuilder jsonString = new StringBuilder();
        String url = "http://" + host + command.getPath();
        HttpURLConnection connection = createConnection(command.getMethod().toString(), url, apiKey);
        if (command.hasParams()) addParams(connection, command);
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while(true) {
            String temp = br.readLine();
            if (temp != null) {
                jsonString.append(temp);
            } else {
                break;
            }
        }
//        logger.debug(jsonString);
        command.setResponseCode(connection.getResponseCode());
        command.setResponseMessage(connection.getResponseMessage());
        JSONObject response = new JSONObject(jsonString.toString());
        command.parseResponse(response);
        connection.disconnect();
    }
    private HttpURLConnection createConnection(String method, String url, String apiKey) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)apiUrl.openConnection();
        connection.setRequestProperty("X-Api-Key", apiKey);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestMethod(method);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }
    private void addParams(HttpURLConnection connection, OPiRequest command) {
        try {
            OutputStream os = connection.getOutputStream();
            os.write(command.getParams().toString().getBytes());
            os.flush();
        } catch(Exception ex) {
            logger.error(ex);
        }
    }
    // endregion

    //region . Connection-State .
    private Object monitor = new Object();
    private List<ConnectionStateListener> listener = new ArrayList<>();
    private boolean connected = false;
    public boolean isConnected() {
        return connected;
    }
    public void addConnectionStateListener(ConnectionStateListener csl) {
        synchronized (monitor) {
            listener.add(csl);
        }
    }
    public void removeConnectionStateListener(ConnectionStateListener csl) {
        synchronized (monitor) {
            listener.remove(csl);
        }
    }
    protected void raiseOnConnected() {
        if (connected) return;
        synchronized (monitor) {
            connected = true;
            for(ConnectionStateListener csl : listener) {
                try {
                    csl.OnConnected();
                } catch(Exception ex) {
                    logger.error(ex);
                }
            }
        }
    }
    protected void raiseOnDisconnected() {
        if (!connected) return;
        synchronized (monitor) {
            connected = false;
            for(ConnectionStateListener csl : listener) {
                try {
                    csl.OnDisconnected();
                } catch(Exception ex) {
                    logger.error(ex);
                }
            }
        }
    }
    //endregion

}
