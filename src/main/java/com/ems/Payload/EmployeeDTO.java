package com.ems.Payload;

import com.ems.entities.Bonus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long eid;
    private String name;
    private String city;
    private String email;
    private long mobile;
    private List<Bonus> bonuses;
}
