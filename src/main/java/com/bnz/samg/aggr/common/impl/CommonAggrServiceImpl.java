package com.bnz.samg.aggr.common.impl;


import com.bnz.samg.aggr.au01.impl.Au01EntityVo;
import com.bnz.samg.aggr.common.spec.CommonAggrService;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Deprecated
//@RequiredArgsConstructor 달면 안됨
@Service
public class CommonAggrServiceImpl<T> implements CommonAggrService<T> {


//    @Autowired //달면 안됨
//    private CommonRepository<T, Au01EntityVo> commonRepository;

//    public CommonAggrServiceImpl(CommonRepository<T, Au01EntityDto> commonRepository) {
//        this.commonRepository = commonRepository;
//    }


//    public CommonAggrServiceImpl() {
//    }
//
//    public CommonAggrServiceImpl(JpaSpecificationExecutor<T> commonRepository) {
//        this.commonRepository = commonRepository;
//    }


    //    @Override
    public List<T> findByCondition(Class<T> entityDtoClass, Specification<T> dtoSpecification) {

        // DTO Specification -> Entity Specification 변환
//        Specification<?> entitySpecification = (root, query, builder) -> dtoSpecification.toPredicate(root, query, builder);

        Specification<T> entitySpecification = (Specification<T>) (root, query, builder) -> {
            if (dtoSpecification != null) {
                return dtoSpecification.toPredicate((Root<T>) root, query, builder);
            }
            return builder.conjunction();
        };


//        JpaSpecificationExecutor<T> executor = (JpaSpecificationExecutor<T>) commonRepository;
//        return executor.findAll(specification);

        // Entity 조회
//        CommonRepository<T, Long> repository = new SimpleJpaRepository<>(entityClass, entityManager);

        Specification<Au01EntityVo> spec1 = (root, query, cb) -> cb.equal(root.get("lobCd"), "MV");

//        List<T> entities = commonRepository.findAll(entitySpecification);

        return null;
    }

}
