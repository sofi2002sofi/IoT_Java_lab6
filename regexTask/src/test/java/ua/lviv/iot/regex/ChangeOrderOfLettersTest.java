package ua.lviv.iot.regex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangeOrderOfLettersTest {

	private ChangeOrderOfLetters changeOrderOfLetter;

	private ChangeOrderOfLettersTest changeOrderTest;

	private String textToBeProcesed;

	@BeforeEach
	public void setUp() {
		changeOrderOfLetter = new ChangeOrderOfLetters();
		changeOrderTest = new ChangeOrderOfLettersTest();
	}

	public String inputTextToBeProcesed() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the sentences to be processed");
		textToBeProcesed = sc.nextLine();

		return textToBeProcesed;
	}

	@Test
	public void findInterrogetiveSentencesTest() {
		List<String> foundInterrogetiveSentences = changeOrderOfLetter
				.findInterrogetiveSentences(changeOrderTest.inputTextToBeProcesed());
		String foundSentences = "";
		for (String foundSentence : foundInterrogetiveSentences) {
			foundSentences += foundSentence;
		}
		assertEquals("Who killed Erica?" + "What happened when the wheel was invented?"
				+ "Is the most important purpose in life to find happiness?", foundSentences);
	}

	@Test
	public void findMiddleWordsTest() {
		List<String> foundMiddleWords = changeOrderOfLetter.findMiddleWords(
				changeOrderOfLetter.findInterrogetiveSentences(changeOrderTest.inputTextToBeProcesed()));
		String foundWords = "";
		for (String foundWord : foundMiddleWords) {
			foundWords += foundWord;
		}
		assertEquals("killed" + "the" + "purpose" + "in", foundWords);
	}

	@Test
	public void reverseOrderOfLettersTest() {
		List<String> reversedMiddleWords = changeOrderOfLetter
				.reverseOrderOfLetters(changeOrderOfLetter.findMiddleWords(
						changeOrderOfLetter.findInterrogetiveSentences(changeOrderTest.inputTextToBeProcesed())));
		String reversedWords = "";
		for (String reversedWord : reversedMiddleWords) {
			reversedWords += reversedWord;
		}
		assertEquals("dellik" +  "eht"  + "esoprup" + "ni", reversedWords);
	}
}
