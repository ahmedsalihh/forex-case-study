package com.ahmedsalihh.forexcasestudy.repository;

import com.ahmedsalihh.forexcasestudy.model.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ConversionRepository extends PagingAndSortingRepository<Conversion, Long>, CrudRepository<Conversion, Long> {

    Page<Conversion> findByTransactionId(Long transactionId, Pageable paging);

    Page<Conversion> findByTransactionDate(LocalDate date, Pageable paging);

    Page<Conversion> findConversionsByTransactionIdAndTransactionDate(Long transactionId, LocalDate transactionDate, Pageable paging);
}
