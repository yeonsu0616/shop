package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="item_img")
@Getter
@Setter
public class ItemImg extends BaseEntity{
    @Id
    @Column(name="item_img-id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imgName;
    private String originName;
    private String imgUrl;
    private String repImgYn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;
    public void updateItemImg(String oriImgName,String imgName,String imgUrl){
        this.originName=oriImgName;
        this.imgName=imgName;
        this.imgUrl=imgUrl;
    }
}
