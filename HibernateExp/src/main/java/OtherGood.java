import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "other_goods")
public class OtherGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "count")
    private Integer count;
    @Column(name = "price")
    private Integer price;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "other_baskets", joinColumns = {@JoinColumn(name = "good_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<OtherOrder> otherOrders;

    public OtherGood() {
    }

    public OtherGood(String name, Integer count, Integer price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<OtherOrder> getOtherOrders() {
        return otherOrders;
    }

    public void setOtherOrders(List<OtherOrder> otherOrders) {
        this.otherOrders = otherOrders;
    }
}
