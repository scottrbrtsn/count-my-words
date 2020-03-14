package services;

import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.managers.impl.PhraseManager;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import com.scottrbrtsn.wordcount.services.impl.PhraseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class PhraseManagerTest {

    @Mock
    IPhraseRepository logsRepository;

    @Mock
    PhraseService phraseService;

    @InjectMocks
    PhraseManager phraseManager;

    @Test
    public void testCountMyWords() {
        Mockito.when(phraseService.countMyWords(anyString())).thenReturn(3);
        Mockito.when(logsRepository.findById(anyLong())).thenReturn(null);
        int count = phraseManager.countMyWords(new Phrase(1, "1 2 3"));
        assertEquals(3, count);

    }

}
