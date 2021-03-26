package madbrains.controller;

import madbrains.catalogues.MyComparator;
import madbrains.component.ComparatorComponent;
import madbrains.dao.ComparatorDAO;
import madbrains.service.CatalogueService;
import madbrains.service.ComparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comparator")
public class ComparatorController {

    private ComparatorService comparatorService;

    @Autowired
    public ComparatorController(ComparatorService comparatorService) {
        this.comparatorService = comparatorService;
    }
    /*@Autowired
    private ComparatorComponent component;*/

    @PostMapping("/load")
    public ResponseEntity load(@RequestBody MyComparator groovy) {
        /*try {
            component.load(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }*/
        try {
            comparatorService.createComparator(groovy);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable int id) {
        return ResponseEntity.ok(comparatorService.getComparator(id));
    }
}
