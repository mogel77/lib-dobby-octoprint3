package de.x8games.octolib3.datamodel;

import de.x8games.octolib3.annotations.OPiValue;
import de.x8games.octolib3.tools.JsonMirror;
import org.json.JSONObject;

import java.util.Optional;





public class JobInformation extends JsonMirror {

    public JobInformation(JSONObject json) {
        super(json);
        fileInformation = new FileInformation(json.getJSONObject("file"));
    }



    private FileInformation fileInformation;
    @OPiValue
    private Optional<Float> estimatedPrintTime;
    @OPiValue
    private Optional<Float> lastPrintTime;

    private Optional<Object> filament;
    private Optional<Float> length;
    private Optional<Float> volume;



    public FileInformation getFileInformation() {
        return fileInformation;
    }
    public Optional<Float> getEstimatedPrintTime() {
        return estimatedPrintTime;
    }
    public Optional<Float> getLastPrintTime() {
        return lastPrintTime;
    }
    public Optional<Object> getFilament() {
        return filament;
    }
    public Optional<Float> getLength() {
        return length;
    }
    public Optional<Float> getVolume() {
        return volume;
    }

}
