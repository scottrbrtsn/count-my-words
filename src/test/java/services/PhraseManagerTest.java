package services;

import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.managers.impl.PhraseManager;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import com.scottrbrtsn.wordcount.ras.ITotalsRepository;
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
    private IPhraseRepository phraseRepository;

    @Mock
    private ITotalsRepository totalsRepository;

    @Mock
    private PhraseService phraseService;

    @InjectMocks
    private PhraseManager phraseManager;

    @Test
    public void testCountMyWords_firstCountAndNoTotals_returnsNewTotal() {

        Mockito.when(phraseService.countMyWords(anyString())).thenReturn(3);
        Mockito.when(phraseRepository.findById(anyLong())).thenReturn(null);
        Mockito.when(totalsRepository.findById(anyString())).thenReturn(null);

        Phrase phrase = new Phrase();
        phrase.setId(1);
        phrase.setPhrase("1 2 3");

        int count = phraseManager.countMyWords(phrase);
        assertEquals(3, count);

    }

}
