package samsungcompany.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Mobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private int storage;
    private int RAM;
    private int battery;
    private int price;

    public Mobile() {}

    public Mobile(Long id, String name, int storage, int RAM, int battery, int price) {}
}
