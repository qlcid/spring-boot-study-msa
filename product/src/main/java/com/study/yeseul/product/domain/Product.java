package com.study.yeseul.product.domain;

import com.study.yeseul.product.vo.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 50)
    private String vender;


    public static Product valueOf(ProductDto.ProductCreateDto productCreateDto) {
        return new Product(
                productCreateDto.getName(),
                productCreateDto.getPrice(),
                productCreateDto.getVender());
    }

    public Product() {
    }

    public Product(String name, int price, String vender) {
        this.name = name;
        this.price = price;
        this.vender = vender;
    }
}
