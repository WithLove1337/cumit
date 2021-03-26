package madbrains.dao;

import madbrains.catalogues.Element;
import madbrains.catalogues.Catalogue;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CatalogueDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createCatalogue(Catalogue catalogue) {
        entityManager.persist(catalogue);
        entityManager.flush();
/*        return catalogue.getName();*/
    }

    public Catalogue findCatalogue(int id) {
        Catalogue catalogue = entityManager.find(Catalogue.class, id);
        return catalogue;
    }

    @Transactional
    public void addElement(Element element) {
        entityManager.persist(element);
        entityManager.flush();
    }

    public void addElements(List<Element> elementList) {
        entityManager.persist(elementList);
    }

    public Element findElement(int id) {
        Element element = entityManager.find(Element.class, id);
        return element;
    }

    public Element findElementFromCatalogue(int id, int catalogue_id) {
        Element element = entityManager.createQuery(
                "select e from Element e where e.id = :id and e.catalogue = :catalogue", Element.class)
                .setParameter("id", id)
                .setParameter("catalogue", findCatalogue(catalogue_id))
                .getSingleResult();
        return element;
    }

    @Transactional
    public void deleteElementFromCatalogue(int id, int catalogue_id) {
        Element element = findElementFromCatalogue(id, catalogue_id);
        entityManager.remove(element);
    }

    public int countElement(int catalogue_id, String name) {
        int count = 0;
        List<Element> elements = entityManager.createQuery(
                "select e from Element e where e.catalogue = :catalogue and e.name = :name", Element.class)
                .setParameter("catalogue", findCatalogue(catalogue_id))
                .setParameter("name", name)
                .getResultList();
        count = elements.size();
        return count;
    }

    public int getCatalogueSize(int catalogue_id) {
        int count = 0;
        List<Element> elements = entityManager.createQuery(
                "select e from Element e where e.catalogue = :catalogue", Element.class)
                .setParameter("catalogue", findCatalogue(catalogue_id))
                .getResultList();
        for (Element item: elements)
            count++;
        return count;
    }

    public List<Element> sortElementsInCatalogue(int catalogue_id) {
        List<Element> elements = entityManager.createQuery(
                "select e from Element e where e.catalogue = :catalogue order by e.id", Element.class)
                .setParameter("catalogue", findCatalogue(catalogue_id))
                .getResultList();
        return elements;
    }

    //current
    public List<Element> sortElementsInCatalogueCustom(int catalogue_id) {
        List<Element> elements = entityManager.createQuery(
                "select e from Element e where e.catalogue = :catalogue", Element.class)
                .setParameter("catalogue", findCatalogue(catalogue_id))
                .getResultList();
        return elements;
    }

    public List<Element> shuffleElementsInCatalogue(int catalogue_id) {
        List<Element> elements = entityManager.createQuery(
                "select e from Element e where e.catalogue = :catalogue order by e.id", Element.class)
                .setParameter("catalogue", findCatalogue(catalogue_id))
                .getResultList();
        return elements;
    }
}
