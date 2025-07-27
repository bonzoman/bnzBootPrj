package com.bnz.samg.aggr.au01;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Au01Repository extends JpaRepository<Au01Entity, Au01Entity.PK> {

    List<Au01Entity> findByLobCdAndItemNameStartingWith(String lobCd, String itemName); //StartingWith : like ?%

    List<Au01Entity> findByLobCdAndItemNameEndingWith(String lobCd, String itemName); //EndingWith : like %?

    List<Au01Entity> findByLobCdAndItemNameContaining(String lobCd, String itemName); //Containing : %?%
}
