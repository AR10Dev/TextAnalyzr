package com.textanalyzr.tool;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TextAnalysisTool {
  /** Scanner for reading user input. */
  private static final Scanner SCANNER = new Scanner(System.in);

  /**
   * The main method that drives the program. It prompts the user for a non-empty paragraph and
   * analyzes it.
   *
   * @param args Command line arguments (not used).
   */
  public static void main(String[] args) {
    try {
      String inputText = promptForNonEmptyText("Enter a paragraph:");
      if (inputText.isEmpty()) {
        printError("No input text provided. Exiting.");
        return;
      }

      analyzeText(inputText);
    } catch (Exception e) {
      handleException(e);
    } finally {
      SCANNER.close();
    }
  }

  /**
   * Analyzes the given text and prints out various statistics about it.
   *
   * @param inputText The text to analyze.
   */
  private static void analyzeText(String inputText) {
    printFormatted("Total number of characters: %d", inputText.length());
    printFormatted("Total number of words: %d", countWords(inputText));

    Map<Character, Integer> charFrequencyMap = buildCharFrequencyMap(inputText);
    if (charFrequencyMap.isEmpty()) {
      printError("Text contains no characters to analyze. Exiting.");
      return;
    }

    char mostCommonChar = findMostCommonCharacter(charFrequencyMap);
    printFormatted("Most common character: %c", mostCommonChar);

    char inputChar = promptForCharacter("Enter a character to find its frequency:");
    printFormatted("Frequency of '%c': %d", inputChar, charFrequencyMap.getOrDefault(inputChar, 0));

    String inputWord =
        promptForWord("Enter a word to find its frequency (alphabetic characters only):");
    printFormatted(
        "Frequency of \"%s\": %d", inputWord, calculateWordFrequency(inputText, inputWord));

    printFormatted("Number of unique words: %d", calculateUniqueWordsCount(inputText));
  }

  /**
   * Counts the number of words in the given text.
   *
   * @param text The text to count words in.
   * @return The number of words.
   */
  private static int countWords(String text) {
    return text.split("\\s+").length;
  }

  /**
   * Builds a frequency map of characters in the given text.
   *
   * @param text The text to analyze.
   * @return A map of character frequencies.
   */
  private static Map<Character, Integer> buildCharFrequencyMap(String text) {
    return text.toLowerCase()
        .chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
  }

  /**
   * Finds the most common character in the given frequency map.
   *
   * @param charFrequencyMap The map of character frequencies.
   * @return The most common character.
   */
  private static char findMostCommonCharacter(Map<Character, Integer> charFrequencyMap) {
    return Collections.max(charFrequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
  }

  /**
   * Calculates the frequency of a given word in the text.
   *
   * @param text The text to analyze.
   * @param inputWord The word to find the frequency of.
   * @return The frequency of the word.
   */
  private static int calculateWordFrequency(String text, String inputWord) {
    return (int)
        Arrays.stream(text.split("\\s+")).filter(word -> word.equalsIgnoreCase(inputWord)).count();
  }

  /**
   * Calculates the number of unique words in the text.
   *
   * @param text The text to analyze.
   * @return The number of unique words.
   */
  private static int calculateUniqueWordsCount(String text) {
    return (int) Arrays.stream(text.split("\\s+")).map(String::toLowerCase).distinct().count();
  }

  /**
   * Prompts the user for non-empty text input.
   *
   * @param prompt The prompt to display to the user.
   * @return The non-empty text input from the user.
   */
  private static String promptForNonEmptyText(String prompt) {
    System.out.println(prompt);
    String inputText;
    while (true) {
      inputText = SCANNER.nextLine().trim();
      if (!inputText.isEmpty()) {
        return inputText;
      } else {
        printError("Input cannot be empty. Please try again:");
      }
    }
  }

  /**
   * Prompts the user for a single character input.
   *
   * @param prompt The prompt to display to the user.
   * @return The character input from the user.
   */
  private static char promptForCharacter(String prompt) {
    return promptForInput(
            prompt,
            input -> input.length() == 1 && Character.isLetter(input.charAt(0)),
            "Invalid input. Please enter a single alphabetic character:")
        .charAt(0);
  }

  /**
   * Prompts the user for a word input consisting of alphabetic characters only.
   *
   * @param prompt The prompt to display to the user.
   * @return The word input from the user.
   */
  private static String promptForWord(String prompt) {
    return promptForInput(
        prompt,
        input -> input.matches("[a-zA-Z]+"),
        "Invalid input. Please enter alphabetic characters only:");
  }

  /**
   * Prompts the user for input that satisfies a given condition.
   *
   * @param prompt The prompt to display to the user.
   * @param condition The condition that the input must satisfy.
   * @param errorMessage The error message to display if the input does not satisfy the condition.
   * @return The user input that satisfies the condition.
   */
  private static String promptForInput(
      String prompt, Predicate<String> condition, String errorMessage) {
    System.out.println(prompt);
    String input;
    while (true) {
      input = SCANNER.nextLine().trim().toLowerCase();
      if (condition.test(input)) {
        return input;
      } else {
        System.out.println(errorMessage);
      }
    }
  }

  /**
   * Prints formatted text to the console.
   *
   * @param format The format string.
   * @param args Arguments referenced by the format specifiers in the format string.
   */
  private static void printFormatted(String format, Object... args) {
    System.out.println(String.format(format, args));
  }

  /**
   * Prints an error message to the console.
   *
   * @param message The error message.
   */
  private static void printError(String message) {
    System.err.println("Error: " + message);
  }

  /**
   * Handles exceptions that occur during the execution of the program.
   *
   * @param e The exception that occurred.
   */
  private static void handleException(Exception e) {
    if (e instanceof InputMismatchException || e instanceof IllegalArgumentException) {
      printError("Invalid input provided. " + e.getMessage());
    } else if (e instanceof NoSuchElementException) {
      printError("No input found. Exiting.");
    } else if (e instanceof IllegalStateException) {
      printError("Scanner closed unexpectedly. Exiting.");
    } else {
      printError("An unexpected error occurred. Exiting.");
    }
  }
}
