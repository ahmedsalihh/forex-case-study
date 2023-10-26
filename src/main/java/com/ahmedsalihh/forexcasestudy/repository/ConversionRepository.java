package com.ahmedsalihh.forexcasestudy.repository;

import com.ahmedsalihh.forexcasestudy.model.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ConversionRepository extends PagingAndSortingRepository<Conversion, Long>, CrudRepository<Conversion, Long> {

    Page<Conversion> findById(Long id, Pageable paging);

    Page<Conversion> findByTransactionDateGreaterThan(Date date, Pageable paging);

    @Query(value = "SELECT * FROM Exchange where id = ?1 and transaction_date >= ?2",
            countQuery = "SELECT count(*) FROM Exchange  where id = ?1 and transaction_date >= ?2",
            nativeQuery = true)
    Page<Conversion> findExchangeListByTransactionIdAndTransactionDate(Long transactionId,
                                                                       Date transactionDate,
                                                                       Pageable paging);
}
