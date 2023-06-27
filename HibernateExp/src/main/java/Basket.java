import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "baskets")
public class Basket {
    @EmbeddedId
    private BasketKey basketKey;
//    @Column(name = "order_id", insertable = false, updatable = false)
//    private Integer orderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
//    @Column(name = "good_id", insertable = false, updatable = false)
//    private Integer goodId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "good_id", insertable = false, updatable = false)
    private Good good;
    @Column(name = "goods_count")
    private Integer goodsCount;

    public Basket() {
    }

    public Basket(BasketKey basketKey, Order order, Good good, Integer goodsCount) {
        this.basketKey = basketKey;
        this.order = order;
        this.good = good;
        this.goodsCount = goodsCount;
    }

//    public Basket(BasketKey basketKey, Order order, Good good) {
//        this.basketKey = basketKey;
//        this.order = order;
//        this.good = good;
//    }

    public BasketKey getBasketKey() {
        return basketKey;
    }

    public void setBasketKey(BasketKey basketKey) {
        this.basketKey = basketKey;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Order getOrder() {
        return order;
    }

    public Good getGood() {
        return good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(basketKey, basket.basketKey) && Objects.equals(order, basket.order) && Objects.equals(good, basket.good) && Objects.equals(goodsCount, basket.goodsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketKey, order, good, goodsCount);
    }
}
