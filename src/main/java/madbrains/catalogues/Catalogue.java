package madbrains.catalogues;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class Catalogue extends ElementManager<Element>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalogue() {
    }

    public Catalogue(String name) {
        this.name = name;
    }
}
