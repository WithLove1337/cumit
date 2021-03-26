package madbrains.controller;

import lombok.extern.slf4j.Slf4j;
import madbrains.catalogues.Catalogue;
import madbrains.catalogues.Element;
import madbrains.dto.CatalogueDTO;
import madbrains.dto.ElementDTO;
import madbrains.service.CatalogueService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/")
public class CatalogueController {
    private CatalogueService catalogueService;

    @Autowired
    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @PostMapping("/lists")
    public void createCatalogue(@RequestBody CatalogueDTO catalogueDTO) {
        catalogueService.createCatalogue(catalogueDTO.toCatalogue());
    }

    //TBD
    /*@GetMapping("/lists")
    public ResponseEntity<Catalogue> getCatalogues() {
        try {
            return ResponseEntity.ok(catalogueService.findCatalogue(id));
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/lists/{id}")
    public ResponseEntity<List<ElementDTO>> getElementsByCatalogueID(@PathVariable int id) {
        List<ElementDTO> list = catalogueService.findElements(id).stream()
                .map(ElementDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping("lists/{id}/elements")
    public ResponseEntity addElement(@RequestBody Element element, @PathVariable(name = "id") int id) {
        catalogueService.addElement(element, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lists/{id}/elements/{id_element}")
    public ResponseEntity<ElementDTO> getElementFromCatalogue(@PathVariable int id, @PathVariable int id_element) {
        return ResponseEntity.ok(ElementDTO.from(catalogueService.findElementFromCatalogue(id_element, id)));
    }

    @DeleteMapping("/lists/{id}/elements/{id_element}")
    public void deleteElementFromCatalogue(@PathVariable int id, @PathVariable int id_element){
        catalogueService.deleteElementFromCatalogue(id, id_element);
    }

    @GetMapping("/lists/{id}/size")
    public ResponseEntity<Integer> getCatalogueSize(@PathVariable int id) {
        return ResponseEntity.ok(catalogueService.getCatalogueSize(id));
    }

    @PutMapping("/lists/{id}/elements")
    public ResponseEntity addElements(@RequestBody List<Element> elementList, @PathVariable int id) {
        catalogueService.addElements(elementList, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lists/{id}/find")
    public ResponseEntity<Integer> countElement(@RequestParam(name = "name") String json_element, @PathVariable int id) {
        return ResponseEntity.ok(catalogueService.countElement(id, json_element));
    }

    @GetMapping("/lists/{id}/sort")
    public ResponseEntity<List<ElementDTO>> sortElementsInCatalogue(@RequestParam int parameter, @PathVariable int id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (parameter == 0) {
            List<ElementDTO> list = catalogueService.sortElementsInCatalogue(id).stream()
                    .map(ElementDTO::from)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(list);
        }
        else  {
            List<ElementDTO> list = catalogueService.sortElementsInCatalogueCustom(id, parameter).stream()
                    .map(ElementDTO::from)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(list);
        }
        /*else if (parameter.equals("custom")) {
            List<ElementDTO> list = catalogueService.sortElementsInCatalogueCustom(id).stream()
                    .map(ElementDTO::from)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(list);
        }
        return ResponseEntity.badRequest().build();*/
    }

    /*@GetMapping("/execute")
    public ResponseEntity execute() {
        component.compare();
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/lists/{id}/shuffle")
    public ResponseEntity<List<ElementDTO>> shuffleElementsInCatalogue(@PathVariable int id) {
        List<ElementDTO> list = catalogueService.shuffleElementsInCatalogue(id).stream()
                .map(ElementDTO::from)
                .collect(Collectors.toList());
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //TBD
    /*@GetMapping("/lists/{id}/sort?comparator={name}")*/
}
