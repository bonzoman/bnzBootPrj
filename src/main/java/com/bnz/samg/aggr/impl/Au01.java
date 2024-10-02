package com.bnz.samg.aggr.impl;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="AU01")
public class Au01 {

    @Id
    @Column(name="LOB_CD")
    private Long lobCd;

    @Id
    @Column(name="ITEM_NAME")
    private String itemName;

    @Id
    @Column(name="SEQ_NO")
    private Integer seqNo;

    @Column(name="ITEM_ATTR01")
    private String itemAttr01;

    @Column(name="ITEM_ATTR02")
    private String itemAttr02;

    @Column(name="ITEM_ATTR03")
    private String itemAttr03;

    @Column(name="ITEM_ATTR04")
    private String itemAttr04;

    @Column(name="ITEM_ATTR05")
    private Integer itemAttr05;




}
