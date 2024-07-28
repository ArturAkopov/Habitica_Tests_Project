package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserBodyTasksOrder {
    private ArrayList<Object> habits;
    private ArrayList<Object> dailys;
    private ArrayList<Object> todos;
    private ArrayList<String> rewards;
}
