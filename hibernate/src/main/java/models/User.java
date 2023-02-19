package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String firstName;
    @Column(length = 100, nullable = false)
    private String lastName;
    @Column(length = 200, nullable = false)
    private String email;
    @Column(length = 20, nullable = false)
    private String phone;
    @Column(length = 200, nullable = false)
    private String password;

    @OneToMany(mappedBy="user")
    private List<UserRole> userRoles;
    @OneToMany(mappedBy="user")
    private List<Basket> baskets;

    public User() {
        userRoles=new ArrayList<>();
        baskets=new ArrayList<>();
    }

    public User(String firstName, String lastName, String email, String phone, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
