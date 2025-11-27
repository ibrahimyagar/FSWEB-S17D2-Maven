package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers;
    private final Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }

    @GetMapping
    public List<Developer> getDevelopers() {
        return developers
                .values()
                .stream()
                .toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable("id") int id) {
        return developers.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//201
    public Developer createDeveloper(@RequestBody Developer developer) {
        Developer createdDeveloper = null;

        if (developer.getExperience().equals(Experience.JUNIOR)) {

            double juniourSalary = developer.getSalary()-(developer.getSalary()* taxable.getSimpleTaxRate()/100 );
            createdDeveloper=new JuniorDeveloper(developer.getId(), developer.getName(),juniourSalary);

        } else if (developer.getExperience().equals(Experience.MID)) {

            double midSalary = developer.getSalary()-(developer.getSalary()* taxable.getMiddleTaxRate()/100 );
            createdDeveloper=new MidDeveloper(developer.getId(), developer.getName(),midSalary);

        } else if (developer.getExperience().equals(Experience.SENIOR)) {

            double seniorSalary = developer.getSalary()-(developer.getSalary()* taxable.getUpperTaxRate()/100 );
            createdDeveloper=new SeniorDeveloper(developer.getId(), developer.getName(),seniorSalary);

        }

        if (Objects.nonNull(createdDeveloper)){
            developers.put(developer.getId(), developer);
        }
        return createdDeveloper;


    }

    @PutMapping("/{id}")

    public Developer updateDeveloper(@PathVariable("id") int id,
                                     @RequestBody Developer developer){
        developers.put(id,developer);
        return  developer;
    }

    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)//204 döner
    public void deleteDeveloper(@PathVariable("id") int id){
        developers.remove(id);

    }
}