package ua.lviv.iot.regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeOrderOfLetters {

	public static int numberOfWords;

	List<String> interrogetiveSentences = new LinkedList<String>();
	List<String> middleWords = new LinkedList<String>();
	List<String> reversedWords = new LinkedList<String>();

	public List<String> findInterrogetiveSentences(String text) {

		String regex = "\\b\\p{Upper}[^.!?]*[?]+[.!]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			interrogetiveSentences.add(matcher.group().toString());
		}

		return interrogetiveSentences;
	}

	public static int countNumberOfWordsInSentence(String sentence) {

		numberOfWords = 0;
		if (sentence.length() != 0) {
			numberOfWords++;
			for (int i = 0; i < sentence.length(); i++) {
				if (sentence.charAt(i) == ' ') {
					numberOfWords++;
				}
			}
		}
		return numberOfWords;
	}

	public List<String> findMiddleWords(List<String> interrogetiveSentences) {

		for (String interrogetiveSentence : interrogetiveSentences) {

			countNumberOfWordsInSentence(interrogetiveSentence);

			if ((numberOfWords % 2) == 1) {

				int countOfSpacesToMiddleWord = (int) (numberOfWords / 2);
				Pattern patternForOddNumberofWords = Pattern
						.compile("^((\\w+)[-']?(\\w+)[,-]?\\s){" + countOfSpacesToMiddleWord + "}(\\w+)[-']?(\\w+)\\b");
				Matcher matcherForOddNumberofWords = patternForOddNumberofWords.matcher(interrogetiveSentence);

				while (matcherForOddNumberofWords.find()) {
					Pattern secondPatternForOddNumberofWords = Pattern.compile("\\b(\\w+)[-']?(\\w+)$");
					Matcher secondMatcherForOddNumberofWords = secondPatternForOddNumberofWords
							.matcher(matcherForOddNumberofWords.group().toString());
					while (secondMatcherForOddNumberofWords.find()) {
						middleWords.add(secondMatcherForOddNumberofWords.group().toString());
					}
				}
			}

			else {
				int countOfSpacesToMiddleWord = (numberOfWords / 2 - 1);
				Pattern patternForEvenNumberOfWords = Pattern.compile(
						"^((\\w+)[-']?(\\w+)[,-]?\\s){" + countOfSpacesToMiddleWord + "}((\\w+)[-']?(\\w+)\\b\\s){2}");
				Matcher matcherForEvenNumberOfWords = patternForEvenNumberOfWords.matcher(interrogetiveSentence);

				while (matcherForEvenNumberOfWords.find()) {
					Pattern secondPatternForEvenNumberOfWords = Pattern.compile("(\\b(\\w+)[-']?(\\w+)\\s){2}$");
					Matcher secondMatcherForEvenNumberOfWords = secondPatternForEvenNumberOfWords
							.matcher(matcherForEvenNumberOfWords.group().toString());
					while (secondMatcherForEvenNumberOfWords.find()) {
						Pattern thirdPatternForEvenNumberOfWords = Pattern.compile("\\b(\\w+)[-']?(\\w+)\\b");
						Matcher thirdMatcherForEvenNumberOfWords = thirdPatternForEvenNumberOfWords
								.matcher(secondMatcherForEvenNumberOfWords.group().toString());
						while (thirdMatcherForEvenNumberOfWords.find()) {
							middleWords.add(thirdMatcherForEvenNumberOfWords.group().toString());
						}
					}
				}
			}
		}
		return middleWords;
	}

	public List<String> reverseOrderOfLetters(List<String> middleWords) {
		for (String middleWord : middleWords) {
			StringBuilder wordsInTheMiddle = new StringBuilder();

			wordsInTheMiddle.append(middleWord);
			wordsInTheMiddle = wordsInTheMiddle.reverse();

			reversedWords.add(wordsInTheMiddle.toString());

		}
		System.out.println(reversedWords);
		return reversedWords;
	}
}
