package samsungcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import samsungcompany.dto.TvDTO;
import samsungcompany.service.TvService;

import java.util.List;

@RestController
@RequestMapping("/tv")
public class TvController {

    private final TvService tvService;

    @Autowired
    public TvController(TvService tvService) {
        this.tvService = tvService;
    }

    @GetMapping
    public List<TvDTO> getAll() {
        return tvService.getAll();
    }

    @PostMapping
    public TvDTO addTv(@RequestBody TvDTO tvDTO) {
        return tvService.addTv(tvDTO);
    }

    @GetMapping("/{id}")
    public TvDTO getById(@PathVariable Long id) {
        return tvService.getById(id);
    }

    @PutMapping("/{id}")
    public TvDTO updateTv(@PathVariable Long id, @RequestBody TvDTO tvDTO) {
        return tvService.updateTv(id, tvDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        tvService.deleteById(id);
    }
}
