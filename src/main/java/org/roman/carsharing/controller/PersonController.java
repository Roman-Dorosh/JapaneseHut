package org.roman.carsharing.controller;

import org.roman.carsharing.dao.car.CarDataBase;
import org.roman.carsharing.dao.person.PersonDataBase;
import org.roman.carsharing.model.Car;
import org.roman.carsharing.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonDataBase personDataBase;
    private final CarDataBase carDataBase;

    @Autowired
    public PersonController(PersonDataBase personDataBase, CarDataBase carDataBase) {
        this.personDataBase = personDataBase;
        this.carDataBase = carDataBase;
    }

    @GetMapping("/person")
    public String listPersonController(Model model) {
        model.addAttribute("modelListPerson", personDataBase.listObject());

        return "personPage/pgListPerson";
    }

    @GetMapping("/person/{person_id}")
    public String getPersonController(@PathVariable("person_id") int person_id,
                                      Model model) {
        model.addAttribute("modelGetPerson", personDataBase.getObject(person_id));
        model.addAttribute("modelListCars", carDataBase.listPurchasedObjects(person_id));

        return "personPage/pgGetPerson";
    }

    @GetMapping("/person/add")
    public String addPersonController(Model model) {
        model.addAttribute("modelAddPerson", new Person());

        return "personPage/pgAddPerson";
    }

    @PostMapping("/person/add")
    public String addPerson(@ModelAttribute("person") Person person) {
        personDataBase.addObject(person);

        return "redirect:/person";
    }

    @GetMapping("/person/{person_id}/edit")
    public String updatePersonController(@PathVariable("person_id") int person_id,
                                         Model model) {
        model.addAttribute("modelEditPerson", personDataBase.getObject(person_id));

        return "personPage/pgEditPerson";
    }

    @PatchMapping("/person/{person_id}/edit")
    public String updatePerson(@PathVariable("person_id") int person_id,
                               @ModelAttribute("person") Person person) {
        personDataBase.updateObject(person, person_id);

        return "redirect:/person/{person_id}";
    }

    @DeleteMapping("/person/{person_id}/edit")
    public String deletePerson(@PathVariable("person_id") int person_id) {
        personDataBase.deleteObject(person_id);

        return "redirect:/person";
    }

    @GetMapping("/person/{person_id}/buyCar")
    public String buyCarController(@PathVariable("person_id") int person_id,
                                   Model model) {
        model.addAttribute("modelGetPerson", personDataBase.getObject(person_id));
        model.addAttribute("modellistCar", carDataBase.listVacantObject());
        model.addAttribute("car", new Car());

        return "personPage/pgBuyCar";
    }

    @PatchMapping("/person/{person_id}/buyCar")
    public String buyCar(@PathVariable("person_id") int person_id,
                         @RequestParam("car_id") int car_id) {
        carDataBase.buyObject(person_id, car_id);

        return "redirect:/person/{person_id}";
    }

    @GetMapping("/car/{car_id}/{person_id}/sellCar")
    public String sellCarController(@PathVariable("car_id") int car_id,
                                    @PathVariable("person_id") int person_id,
                                    Model model) {
        model.addAttribute("modelGetCar", carDataBase.getObject(car_id));
        model.addAttribute("modelGetPerson", personDataBase.getObject(person_id));

        return "personPage/pgSellCar";
    }

    @PatchMapping("/car/{car_id}/{person_id}/sellCar")
    public String sellCar(@PathVariable("car_id") int car_id,
                          @PathVariable("person_id") int person_id) {
        carDataBase.sellObject(car_id);

        return "redirect:/person/{person_id}";
    }
}