package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserResponseBodyModel {

    private boolean success;
    private GetUserBodyData data;

}
