package de.x8games.octolib3.datamodel;

import de.x8games.octolib3.annotations.OPiValue;
import de.x8games.octolib3.tools.JsonMirror;
import org.json.JSONObject;

import java.util.Optional;

public class Tool extends JsonMirror {

    public Tool(JSONObject json) {
        super(json);
    }

    @OPiValue
    private Optional<Float> length;
    @OPiValue
    private Optional<Float> volume;

}
