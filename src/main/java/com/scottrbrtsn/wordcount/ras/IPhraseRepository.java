package com.scottrbrtsn.wordcount.ras;

import com.scottrbrtsn.wordcount.domain.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhraseRepository extends JpaRepository<Phrase, String> {

    Phrase findById(long id);

}
