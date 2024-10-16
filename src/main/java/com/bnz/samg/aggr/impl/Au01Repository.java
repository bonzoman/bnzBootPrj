package com.bnz.samg.aggr.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Au01Repository extends JpaRepository<Au01, Au01.PK>{

    List<Au01> findByLobCdAndItemNameStartingWith(String lobCd, String itemName); //StartingWith : like ?%

    List<Au01> findByLobCdAndItemNameEndingWith(String lobCd, String itemName); //EndingWith : like %?

    List<Au01> findByLobCdAndItemNameContaining(String lobCd, String itemName); //Containing : %?%
}
