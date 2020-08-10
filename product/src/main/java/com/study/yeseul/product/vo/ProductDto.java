package com.study.yeseul.product.vo;


import com.study.yeseul.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductDto {

    @Getter
    @Setter
    public static class ProductCreateDto {
        private String name;
        private int price;
        private String vender;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductDetailDto {
        private long id;
        private String name;
        private int price;
        private String vender;

        public static ProductDetailDto valueOf(Product product) {
            return new ProductDetailDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getVender()
            );
        }

        public ProductDetailDto(long id, String name, int price, String vender) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.vender = vender;
        }
    }

    @Getter
    @Setter
    public static class ProductUpdateDto {
        private int price;
    }

}
