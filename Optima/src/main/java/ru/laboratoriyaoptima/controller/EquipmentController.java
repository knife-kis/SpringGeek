package ru.laboratoriyaoptima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.laboratoriyaoptima.persist.entity.Equipment;
import ru.laboratoriyaoptima.persist.services.EquipmentsService;
import ru.laboratoriyaoptima.persist.utils.EquipmentFilter;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    private EquipmentsService equipmentsService;

    @Autowired
    public EquipmentController(EquipmentsService equipmentsService) {
        this.equipmentsService = equipmentsService;
    }


    @GetMapping
    public String allEquipment(Model model,
                             @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("page", "1"));
        EquipmentFilter equipmentFilter = new EquipmentFilter(requestParams);

        Page<Equipment> equipmentPage = equipmentsService.findAll(equipmentFilter.getSpec(), pageNumber);
        model.addAttribute("equipmentsPage", equipmentPage);
        model.addAttribute("filterDef", equipmentFilter.getFilterDefinition().toString());
        return "equipments";
    }

    @GetMapping("/{id}")
    public String editEquipments(@PathVariable("id") Integer id, Model model) {
        Equipment equipment = equipmentsService.findById(id);
        model.addAttribute("equipment", equipment);
        return "equipment";
    }

    @GetMapping("/new")
    public String newEquipment(Model model) {
        Equipment equipment = new Equipment();
        model.addAttribute("equipment", equipment);
        return "equipment";
    }

    @PostMapping("/update")
    public String updateEquipment(@Valid Equipment equipment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "equipment";
        }

        // TODO реализовать проверку повторного ввода пароля.
        // TODO Использовать метод bindingResult.rejectValue();

        equipmentsService.saveOrUpdate(equipment);
        return "redirect:/equipment";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteEquipment(@PathVariable("id") Integer id) {
        equipmentsService.deleteEquipment(equipmentsService.findById(id));
        return "redirect:/equipment";
    }
}
