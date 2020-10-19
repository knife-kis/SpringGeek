package ru.laboratoriyaoptima.persist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.laboratoriyaoptima.exceptions.EquipmentNotFoundException;
import ru.laboratoriyaoptima.persist.entity.Equipment;
import ru.laboratoriyaoptima.persist.repo.EquipmentRepository;

import java.util.List;

@Service
public class EquipmentsService {
    private EquipmentRepository equipmentRepository;

    @Autowired
    public void setProductsRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment saveOrUpdate(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment findById(int id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFoundException("Can't found equipment with id = " + id));
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public Page<Equipment> findAll(Specification<Equipment> spec, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return equipmentRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public void deleteEquipment(Equipment equipment){
        equipmentRepository.delete(equipment);
    }
    public void deleteById(int id) {
        equipmentRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return equipmentRepository.existsById(id);
    }
}
