package org.roman.carsharing.controller;

import org.roman.carsharing.service.impl.CarDataBase;
import org.roman.carsharing.model.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    private final CarDataBase carDataBase;

    @Autowired
    public CarController(CarDataBase carDataBase) {
        this.carDataBase = carDataBase;
    }

    @GetMapping("/car")
    public String listCarController(Model model) {
        model.addAttribute("modelListCar", carDataBase.listObject());

        return "carPage/pgListCar";
    }

    @GetMapping("/car/{car_id}")
    public String getCarController(@PathVariable("car_id") int car_id,
                                   Model model) {
        model.addAttribute("modelGetCar", carDataBase.getObject(car_id));

        return "carPage/pgGetCar";
    }

    @GetMapping("/car/add")
    public String addCarController(Model model) {
        model.addAttribute("modelAddCar", new Car());

        return "carPage/pgAddCar";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute("car") Car car) {
        carDataBase.addObject(car);

        return "redirect:/car";
    }

    @GetMapping("/car/{car_id}/edit")
    public String updateCarController(@PathVariable("car_id") int car_id,
                                      Model model) {
        model.addAttribute("modelEditCar", carDataBase.getObject(car_id));

        return "carPage/pgEditCar";
    }

    @PatchMapping("/car/{car_id}/edit")
    public String updateCar(@PathVariable("car_id") int car_id,
                            @ModelAttribute("car") Car car) {
        carDataBase.updateObject(car, car_id);

        return "redirect:/car/{car_id}";
    }

    @DeleteMapping("/car/{car_id}/edit")
    public String deleteCar(@PathVariable("car_id") int car_id) {
        carDataBase.deleteObject(car_id);

        return "redirect:/car";
    }
}