package services;

import com.scottrbrtsn.wordcount.services.impl.PhraseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class PhraseServiceTest {

    @InjectMocks
    private PhraseService phraseService;

    @Test
    public void testCountMyWords() {
        int test = phraseService.countMyWords("1 2 3");
        assertEquals(3, test);
    }

    @Test
    public void testCountMyWordsEmpty() {
        int test = phraseService.countMyWords("");
        assertEquals(0, test);
    }
    @Test
    public void testCountMyWordsWhiteSpace() {
        int test = phraseService.countMyWords("  ");
        assertEquals(0, test);
    }

    @Test
    public void testCountMyWordsOnlyPunctuation() {
        int test = phraseService.countMyWords(" . ");
        assertEquals(0, test);
    }

    @Test
    public void testCountMyWordsPunctuationAndNumber() {
        int test = phraseService.countMyWords(" . 1 ");
        assertEquals(1, test);
    }

}
