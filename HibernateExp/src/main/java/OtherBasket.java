import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "other_baskets")
public class OtherBasket {
    @EmbeddedId
    private OtherBasketKey otherBasketKey;

    @Column(name = "goods_count")
    private Integer goodsCount;

    @Embeddable
    public static class OtherBasketKey implements Serializable {
        @Column(name = "order_id")
        private Integer orderId;
        @Column(name = "good_id")
        private Integer goodId;

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

        public OtherBasketKey(Integer orderId, Integer goodId) {
            this.orderId = orderId;
            this.goodId = goodId;
        }

        public OtherBasketKey() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OtherBasketKey that = (OtherBasketKey) o;
            return Objects.equals(orderId, that.orderId) && Objects.equals(goodId, that.goodId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderId, goodId);
        }
    }

    public OtherBasket() {
    }

    public OtherBasket(OtherBasketKey otherBasketKey, Integer goodsCount) {
        this.otherBasketKey = otherBasketKey;
        this.goodsCount = goodsCount;
    }

    public OtherBasketKey getOtherBasketKey() {
        return otherBasketKey;
    }

    public void setOtherBasketKey(OtherBasketKey otherBasketKey) {
        this.otherBasketKey = otherBasketKey;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
