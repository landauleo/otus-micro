package leo.landau.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "micro", name = "delivery")
public class Delivery {

    @Id
    private Long id;

    public Delivery() {
    }

    public Delivery(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
