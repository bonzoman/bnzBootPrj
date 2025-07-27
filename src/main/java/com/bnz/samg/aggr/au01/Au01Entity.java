package com.bnz.samg.aggr.au01;


import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(Au01Entity.PK.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "AU01")
class Au01Entity {
    @Id
    @Column(name = "LOB_CD")
    private String lobCd;

    @Id
    @Column(name = "ITEM_NAME")
    private String itemName;

    @Id
    @Column(name = "SEQ_NO")
    private Integer seqNo;

    @Column(name = "ITEM_ATTR01")
    private String itemAttr01;

    @Column(name = "ITEM_ATTR02")
    private String itemAttr02;

    @Column(name = "ITEM_ATTR03")
    private String itemAttr03;

    @Column(name = "ITEM_ATTR04")
    private String itemAttr04;

    @Column(name = "ITEM_ATTR05")
    private Integer itemAttr05;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    static class PK {
        private String lobCd;
        private String itemName;
        private Integer seqNo;
    }


}
