package com.bnz.samg.aggr.au02.impl;


import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(Au02Entity.PK.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "AU02")
class Au02Entity {
    @Id
    @Column(name = "LOB_CD")
    private String lobCd;

    @Id
    @Column(name = "ITEM_NAME")
    private String itemName;

    @Id
    @Column(name = "ITEM_DETL")
    private String itemDetl;

    @Id
    @Column(name = "SEQ_NO")
    private Integer seqNo;

    @Column(name = "ITEM_DETL_ATTR01")
    private String itemDetlAttr01;

    @Column(name = "ITEM_DETL_ATTR02")
    private String itemDetlAttr02;

    @Column(name = "ITEM_DETL_ATTR03")
    private String itemDetlAttr03;

    @Column(name = "ITEM_DETL_ATTR04")
    private String itemDetlAttr04;

    @Column(name = "ITEM_DETL_ATTR05")
    private Integer itemDetlAttr05;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    static class PK {
        private String lobCd;
        private String itemName;
        private String itemDetl;
        private Integer seqNo;
    }


}
