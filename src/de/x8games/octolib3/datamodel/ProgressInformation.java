package de.x8games.octolib3.datamodel;

import de.x8games.octolib3.annotations.OPiValue;
import de.x8games.octolib3.tools.JsonMirror;
import org.json.JSONObject;

import java.util.Optional;





public class ProgressInformation extends JsonMirror {

    public ProgressInformation(JSONObject json) {
        super(json);
    }



    @OPiValue
    private Optional<Float> completion;
    @OPiValue
    private Optional<Integer> filepos;
    @OPiValue
    private Optional<Integer> printTime;
    @OPiValue
    private Optional<Integer> printTimeLeft;
    @OPiValue
    private Optional<String> printTimeLeftOrigin;



    public Optional<Float> getCompletion() {
        return completion;
    }
    public Optional<Integer> getFilepos() {
        return filepos;
    }
    public Optional<Integer> getPrintTime() {
        return printTime;
    }
    public Optional<Integer> getPrintTimeLeft() {
        return printTimeLeft;
    }
    public Optional<String> getPrintTimeLeftOrigin() {
        return printTimeLeftOrigin;
    }

}
