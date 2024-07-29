package tests.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserResponseBodyModel {
    private Boolean success;
    private GetUserBodyData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GetUserBodyData {
        private GetUserBodyTasksOrder tasksOrder;
        private String id;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class GetUserBodyTasksOrder {
            private ArrayList<Object> habits;
            private ArrayList<Object> dailys;
            private ArrayList<Object> todos;
            private ArrayList<Object> rewards;
        }
    }
}
