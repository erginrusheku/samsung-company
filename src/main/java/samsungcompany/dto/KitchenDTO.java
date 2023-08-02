package samsungcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitchenDTO {

    private Long id;
    private String name;
    private int height;
    private int length;
    private int width;
    private int price;

    public int getCubic(){

        return height* length* width;
    }

    public void setCubic(int cubic) {
    }
}
