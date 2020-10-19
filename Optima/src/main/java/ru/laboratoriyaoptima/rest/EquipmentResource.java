package ru.laboratoriyaoptima.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laboratoriyaoptima.exceptions.EquipmentNotFoundException;
import ru.laboratoriyaoptima.persist.entity.Equipment;
import ru.laboratoriyaoptima.persist.services.EquipmentsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journal")
public class EquipmentResource {

    private final EquipmentsService equipmentsService;

    @Autowired
    public EquipmentResource(EquipmentsService equipmentsService) {
        this.equipmentsService = equipmentsService;
    }

    @GetMapping
    public List<Equipment> findAll() {
        return equipmentsService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
        if (!equipmentsService.existsById(id)) {
            throw new EquipmentNotFoundException("Equipment not found, id: " + id);
        }
        return new ResponseEntity<>(equipmentsService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        if (equipment.getId() != null) {
            equipment.setId(null);
        }
        return equipmentsService.saveOrUpdate(equipment);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOneProducts(@PathVariable int id) {
        equipmentsService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<?> modifyProduct(@RequestBody Equipment equipment) {
        if (equipment.getId() == null || !equipmentsService.existsById(equipment.getId())) {
            throw new EquipmentNotFoundException("Equipment not found, id: " + equipment.getId());
        }
        return new ResponseEntity<>(equipmentsService.saveOrUpdate(equipment), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(EquipmentNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }

}
