import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "other_orders")
public class OtherOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
//    @Column(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('NEW','PACKED', 'DELIVERED', 'PAYED', 'CANCELLED')")
    private OrderStatus orderStatus;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "other_baskets", joinColumns = {@JoinColumn(name = "order_id")},
        inverseJoinColumns = {@JoinColumn(name = "good_id")})
    private List<OtherGood> otherGoods;

    public OtherOrder(User user, OrderStatus orderStatus, LocalDateTime creationDate) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.creationDate = creationDate;
    }

    public OtherOrder() {
    }

    public OtherOrder(User user, OrderStatus orderStatus, LocalDateTime creationDate, List<OtherGood> otherGoods) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.creationDate = creationDate;
        this.otherGoods = otherGoods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<OtherGood> getOtherGoods() {
        return otherGoods;
    }

    public void setOtherGoods(List<OtherGood> otherGoods) {
        this.otherGoods = otherGoods;
    }
}
