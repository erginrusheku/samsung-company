package samsungcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import samsungcompany.dto.KitchenDTO;
import samsungcompany.service.KitchenService;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {

    private final KitchenService kitchenService;

    @Autowired
    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping
    public List<KitchenDTO> getAll(){ return kitchenService.getAll(); }

    @PostMapping
    public KitchenDTO addKitchen(@RequestBody KitchenDTO kitchenDTO){ return kitchenService.addKitchen(kitchenDTO); }

    @GetMapping("/{id}")
    public KitchenDTO getById(@PathVariable Long id){ return kitchenService.getById(id); }

    @PutMapping("/{id}")
    public KitchenDTO updateKitchen(@PathVariable Long id,@RequestBody KitchenDTO kitchenDTO){ return  kitchenService.updateKitchen(id,kitchenDTO);};

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){ kitchenService.deleteById(id); }

}
