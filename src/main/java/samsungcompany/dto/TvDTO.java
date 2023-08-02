package samsungcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TvDTO {

    private Long id;
    private String name;
    private int inch;
    private int price;
}
