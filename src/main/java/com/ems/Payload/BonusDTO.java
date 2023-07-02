package com.ems.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusDTO {
    private Long bid;
    private Double amount;
    private Long employeeId;
}
