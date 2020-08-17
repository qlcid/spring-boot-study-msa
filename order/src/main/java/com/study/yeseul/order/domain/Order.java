package com.study.yeseul.order.domain;

import com.study.yeseul.order.vo.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false)
    private long productId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime cratedAt;

    @Column(nullable = false, updatable = false)
    private String userName;

    @Column(updatable = false)
    private int count;

    public static Order valueOf(OrderDto.OrderCreateReq orderCreateReq) {
        return new Order(
                orderCreateReq.getProductId(),
                orderCreateReq.getUserName(),
                orderCreateReq.getCount()
        );
    }

    public Order(long productId, String userName, int count) {
        this.productId = productId;
        this.userName = userName;
        this.count = count;
    }
}
