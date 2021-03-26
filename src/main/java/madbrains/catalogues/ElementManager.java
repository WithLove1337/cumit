package madbrains.catalogues;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class ElementManager<T extends madbrains.catalogues.Element> {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalogue_id")
    private List<T> elements;

    public ElementManager() {
        this.elements = new ArrayList<>();
    }

    public int getSize() {
        return elements.size();
    }

    public List<T> getElements() {
        return elements;
    }

}
