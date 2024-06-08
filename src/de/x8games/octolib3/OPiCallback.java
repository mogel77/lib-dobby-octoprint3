package de.x8games.octolib3;


/**
 * für einen OPirequest ansynchron aus, bei bedarf auch alle X Sekunden
 */
public abstract class OPiCallback {

    /**
     * der OPiRequest der asynchron ausgeführt werden soll
     * @param request auszuführende OPiRequest
     * @param timeout Zeit zwischen den einzelnen Requests in ms
     * @note bei einem Timeout von 0ms wird der Request nur einmal ausgeführt
     */
    public OPiCallback(OPiRequest request, int timeout) {
        this.request = request;
        this.timeout = timeout;
    }

    /**
     * der OPiRequest der asynchron und genau einmal ausgeführt werden soll
     * @param request auszuführende OPiRequest
     */
    public OPiCallback(OPiRequest request) {
        this.request = request;
    }

    private OPiRequest request;
    private int timeout;
    private boolean abort;

    public OPiRequest getRequest() {
        return request;
    }

    public int getTimeout() {
        return timeout;
    }

    public boolean isAbort() {
        return abort;
    }

    /**
     * bricht die Ausführung des OPiCallbacks ab
     */
    public void abort() {
        abort = true;
    }

    /**
     * wird aufgerufen, wenn die Antwort vom Drucker zurück ist
     * @param request der abgesetzte OPiRequest
     * @note dieser Aufruf erfolgt im UI-Thread
     */
    protected abstract void OnSuccessful(OPiRequest request);

    /**
     * wird aufgerufen, wenn irgend etwas schief gelaufen ist
     * @param request der abgesetzte OPiRequest
     * @note dieser Aufruf erfolgt NICHT UI-Thread, sondern ansynchron
     */
    protected abstract void OnFailedAsync(OPiRequest request);

}
