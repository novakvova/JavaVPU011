package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    private double price;
    @Column(length = 4000)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private boolean idDelete;
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy="product")
    private List<ProductImage> productImages;

    public Product() {
        productImages=new ArrayList<>();
    }

    public Product(String name, double price, String description, Date dateCreated,
                   boolean idDelete, Category category) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
        this.dateCreated = dateCreated;
        this.idDelete = idDelete;
        this.category = category;
    }
}
