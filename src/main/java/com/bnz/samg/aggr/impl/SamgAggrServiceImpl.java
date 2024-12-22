package com.bnz.samg.aggr.impl;


import com.bnz.samg.aggr.spec.SamgAggrService;
import com.bnz.samg.aggr.spec.SamgEntityMapper;
import com.bnz.samg.biz.spec.SamgReqVo;
import com.bnz.samg.biz.spec.SamgSrchReqVo;
import com.bnz.samg.biz.spec.SamgSrchResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SamgAggrServiceImpl implements SamgAggrService {

    @Autowired
    private Au01Repository au01Repository;

    /**
     * selectList
     * @param reqVo reqvo
     * @return 결과
     */
    public List<SamgSrchResVo> selectList(SamgSrchReqVo reqVo) {

        //findAll
        List<Au01Entity> resultAll = au01Repository.findAll();


        //desc findAllById(stream)
        List<Au01Entity.PK> pkList = resultAll.stream()
                .map(au01 -> Au01Entity.PK.builder().lobCd(au01.getLobCd()).itemName(au01.getItemName()).seqNo(au01.getSeqNo()).build())
                .collect(Collectors.toList());
        resultAll = au01Repository.findAllById(pkList);

        //desc findAllById(forEach)
//        List<Au01.PK> pkList = new ArrayList<>();
//        resultAll.forEach(au01 -> {
//            Au01.PK pk = Au01.PK.builder().lobCd(au01.getLobCd()).itemName(au01.getItemName()).seqNo(au01.getSeqNo()).build();
//            pkList.add(pk);
//        });
//        resultAll = au01Repository.findAllById(pkList);

        //desc findBy~~~~~~~~~
        //     StartingWith : like ?%    EndingWith : like %?    Containing : %?%
        resultAll = au01Repository.findByLobCdAndItemNameStartingWith(reqVo.lobCd(), reqVo.itemName());//lobCd = ? and itemName Like ?%
        resultAll = au01Repository.findByLobCdAndItemNameEndingWith(reqVo.lobCd(), reqVo.itemName());//lobCd = ? and itemName Like %?
        resultAll = au01Repository.findByLobCdAndItemNameContaining(reqVo.lobCd(), reqVo.itemName());//lobCd = ? and itemName Like %?%

        //findById
        Au01Entity.PK pk = Au01Entity.PK.builder().lobCd(reqVo.lobCd()).itemName(reqVo.itemName()).seqNo(reqVo.seqNo()).build();
        Au01Entity au01Entity = au01Repository.findById(pk).orElseGet(Au01Entity::new);




        List<SamgSrchResVo> resVoList = SamgEntityMapper.INSTANCE.entityListToResVoList(resultAll);
        return resVoList;
    }

    /**
     *
     * @param reqVo request
     */
    public void insert(SamgReqVo reqVo) {

        Au01Entity au01Entity = SamgEntityMapper.INSTANCE.reqVoToAU01(reqVo);

        au01Repository.save(au01Entity);

    }
}
