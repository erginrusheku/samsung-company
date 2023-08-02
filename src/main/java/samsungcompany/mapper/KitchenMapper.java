package samsungcompany.mapper;

import org.springframework.stereotype.Component;
import samsungcompany.dto.KitchenDTO;
import samsungcompany.model.Kitchen;

@Component
public class KitchenMapper {

    public KitchenDTO toDto(Kitchen kitchen){

        KitchenDTO kitchenDTO = new KitchenDTO();

        kitchenDTO.setId(kitchen.getId());
        kitchenDTO.setName(kitchen.getName());
        kitchenDTO.setLength(kitchen.getLength());
        kitchenDTO.setHeight(kitchen.getHeight());
        kitchenDTO.setWidth(kitchen.getWidth());
        kitchenDTO.setCubic(kitchen.getCubic());
        kitchenDTO.setPrice(kitchen.getPrice());

        return kitchenDTO;
    }

    public Kitchen toEntity(KitchenDTO kitchenDTO){

        Kitchen kitchen = new Kitchen();

        kitchen.setId(kitchenDTO.getId());
        kitchen.setName(kitchenDTO.getName());
        kitchen.setLength(kitchenDTO.getLength());
        kitchen.setHeight(kitchenDTO.getHeight());
        kitchen.setWidth(kitchenDTO.getWidth());
        kitchen.setCubic(kitchenDTO.getCubic());
        kitchen.setPrice(kitchenDTO.getPrice());

        return kitchen;
    }
}
