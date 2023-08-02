package samsungcompany.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Kitchen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private int height;
    private int length;
    private int width;
    private int price;

    public Kitchen() {}

    public Kitchen(Long id, String name, int height, int length, int width, int price ) {
    }

    public int getCubic() {
        return height * length * width;
    }

    public void setCubic(int cubic) {
    }
}
