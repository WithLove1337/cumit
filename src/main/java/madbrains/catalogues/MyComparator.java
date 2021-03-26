package madbrains.catalogues;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comparators")
public class MyComparator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "code")
    private String code;

    public String getCode() {
        return code;
    }
}
