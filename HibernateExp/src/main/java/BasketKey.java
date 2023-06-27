import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class BasketKey implements Serializable {
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "good_id")
    private Integer goodId;

    public BasketKey() {
    }

    public BasketKey(Integer orderId, Integer goodId) {
        this.orderId = orderId;
        this.goodId = goodId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketKey basketKey = (BasketKey) o;
        return Objects.equals(orderId, basketKey.orderId) && Objects.equals(goodId, basketKey.goodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, goodId);
    }
}
