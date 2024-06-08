package de.x8games.octolib3.requests;

import de.x8games.octolib3.OPiRequest;
import de.x8games.octolib3.tools.JsonStatic;
import de.x8games.octolib3.datamodel.JobInformation;
import de.x8games.octolib3.datamodel.ProgressInformation;
import org.json.JSONObject;

import java.util.Optional;





public class Job extends OPiRequest {

    public Job() {
        super(Method.GET, "/api/job");
    }

    @Override
    public void parseResponse(JSONObject response) {
        job = new JobInformation(response.getJSONObject("job"));
        progress = new ProgressInformation(response.getJSONObject("progress"));

        state = JsonStatic.jsonString(response, "state");
        error = JsonStatic.optionalString(response, "error");
    }



    private JobInformation job;
    private ProgressInformation progress;

    private String state;
    private Optional<String> error;



    public JobInformation getJob() {
        return job;
    }

    public ProgressInformation getProgress() {
        return progress;
    }

    public String getState() {
        return state;
    }

    public Optional<String> getError() {
        return error;
    }

}
