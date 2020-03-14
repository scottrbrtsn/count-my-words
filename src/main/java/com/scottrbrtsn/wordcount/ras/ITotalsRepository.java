package com.scottrbrtsn.wordcount.ras;

import com.scottrbrtsn.wordcount.domain.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITotalsRepository extends JpaRepository<Total, String> {

    Total findById(String id);

}
