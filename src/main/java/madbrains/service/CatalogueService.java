package madbrains.service;

import lombok.extern.slf4j.Slf4j;
import madbrains.dao.CatalogueDAO;
import madbrains.catalogues.Element;
import madbrains.catalogues.Catalogue;
import madbrains.dao.ComparatorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import madbrains.component.ComparatorComponent;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CatalogueService {

    private final CatalogueDAO catalogueDAO;
    private final ComparatorDAO comparatorDAO;

    /*@Autowired
    public CatalogueService(CatalogueDAO catalogueDAO) {
        this.catalogueDAO = catalogueDAO;
    }*/

    @Autowired
    public CatalogueService(CatalogueDAO catalogueDAO, ComparatorDAO comparatorDAO) {
        this.catalogueDAO = catalogueDAO;
        this.comparatorDAO = comparatorDAO;
    }


    @Autowired
    public ComparatorComponent comp;

    public void createCatalogue(Catalogue catalogue) {
        catalogueDAO.createCatalogue(catalogue);
    }

    public Catalogue findCatalogue(int id) {
        return catalogueDAO.findCatalogue(id);
    }

    public void addElement(Element element, int catalogue_id) {
        element.setCatalogue(findCatalogue(catalogue_id));
        catalogueDAO.addElement(element);
    }

    public void addElements(List<Element> elementList, int catalogue_id) {
        for (Element element: elementList) {
            element.setCatalogue(findCatalogue(catalogue_id));
            catalogueDAO.addElement(element);
        }
    }

    public Element findElement(int id) {
        return catalogueDAO.findElement(id);
    }

    public List<Element> findElements(int catalogueID) {
        List<Element> list = catalogueDAO.findCatalogue(catalogueID).getElements();
        return list;
    }

    public Element findElementFromCatalogue(int id, int catalogue_id) {
        return catalogueDAO.findElementFromCatalogue(id, catalogue_id);
    }

    public void deleteElementFromCatalogue(int catalogue_id, int id) {
        catalogueDAO.deleteElementFromCatalogue(id, catalogue_id);
    }

    public int getCatalogueSize(int id) {
        return catalogueDAO.getCatalogueSize(id);
    }

    public int countElement(int catalogue_id, String json_element) {
        return catalogueDAO.countElement(catalogue_id, json_element);
    }

    public List<Element> sortElementsInCatalogue(int id) {
        List<Element> list = catalogueDAO.sortElementsInCatalogue(id);
        return list;
    }

    public List<Element> sortElementsInCatalogueCustom(int id, int comp_id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        /*List<Element> list = catalogueDAO.sortElementsInCatalogueCustom(id);
        Element array[] = new Element[list.size()];
        int i = 0;
        for (Element e: list) {
            array[i] = e;
            i++;
        }
        for (int j = array.length - 1; j > 0; j--) {
            for (int k = 0; k < j; k++) {
                if (comp.compare(array[k], array[k+1]) > 0) {
                    Element tmp = array[k];
                    array[k] = array[k+1];
                    array[k+1] = tmp;
                }
            }
        }
        list = Arrays.asList(array);
        return list;*/
        comp.load(comparatorDAO.getComparatorByID(comp_id));
        List<Element> list = catalogueDAO.sortElementsInCatalogueCustom(id);
        Element array[] = new Element[list.size()];
        int i = 0;
        for (Element e: list) {
            array[i] = e;
            i++;
        }
        for (int j = array.length - 1; j > 0; j--) {
            for (int k = 0; k < j; k++) {
                if (comp.compare(array[k], array[k+1]) > 0) {
                    Element tmp = array[k];
                    array[k] = array[k+1];
                    array[k+1] = tmp;
                }
            }
        }
        list = Arrays.asList(array);
        return list;
    }

    public List<Element> shuffleElementsInCatalogue(int id) {
        List<Element> list = catalogueDAO.shuffleElementsInCatalogue(id);
        return list;
    }
}
