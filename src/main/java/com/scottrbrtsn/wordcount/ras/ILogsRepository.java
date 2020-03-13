package com.scottrbrtsn.wordcount.ras;

import com.scottrbrtsn.wordcount.domain.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILogsRepository extends JpaRepository<Logs, String> {

    List<Logs> findById(long id);

}
