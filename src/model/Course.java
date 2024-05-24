package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private String courseTitle;
    private String[] instructorName;
    private String[] courseRequirement;
    private String startDate;


    public static Integer generateId(){
        return ((new Random().nextInt(9000) + 1001));
    }
}

