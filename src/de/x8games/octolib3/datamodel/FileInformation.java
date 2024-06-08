package de.x8games.octolib3.datamodel;

import de.x8games.octolib3.annotations.OPiValue;
import de.x8games.octolib3.tools.JsonMirror;
import org.json.JSONObject;

import java.util.Optional;

public class FileInformation extends JsonMirror {

    public FileInformation(JSONObject json) {
        super(json);
    }


    @OPiValue
    private Optional<String> name;
    @OPiValue
    private Optional<String> display;
    @OPiValue
    private Optional<String> path;
    @OPiValue
    private Optional<String> type;

    // type == model || machinecode

    @OPiValue(MayBeMissing = true)
    private String hash;
    @OPiValue
    private Optional<Integer> size;
    @OPiValue
    private Optional<Long> date;
    @OPiValue
    private Optional<String> origin;


    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getDisplay() {
        return display;
    }

    public Optional<String> getPath() {
        return path;
    }

    public Optional<String> getType() {
        return type;
    }

    public String getHash() {
        return hash;
    }

    public Optional<Integer> getSize() {
        return size;
    }

    public Optional<Long> getDate() {
        return date;
    }

    public Optional<String> getOrigin() {
        return origin;
    }
}
