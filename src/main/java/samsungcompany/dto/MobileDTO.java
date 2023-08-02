package samsungcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MobileDTO {

    private Long id;
    private String name;
    private int storage;
    private int RAM;
    private int battery;
    private int price;

}
