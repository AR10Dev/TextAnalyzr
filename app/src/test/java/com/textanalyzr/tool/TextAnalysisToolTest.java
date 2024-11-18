package com.textanalyzr.tool;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TextAnalysisToolTest {

  @Test
  void testCountWords() throws Exception {
    Method method = TextAnalysisTool.class.getDeclaredMethod("countWords", String.class);
    method.setAccessible(true);
    assertEquals(5, method.invoke(null, "This is a test sentence"));
    // assertEquals(0, method.invoke(null, ""));
    assertEquals(1, method.invoke(null, "word"));
  }

  @Test
  void testBuildCharFrequencyMap() throws Exception {
    Method method = TextAnalysisTool.class.getDeclaredMethod("buildCharFrequencyMap", String.class);
    method.setAccessible(true);
    Map<Character, Integer> frequencyMap = (Map<Character, Integer>) method.invoke(null, "hello");
    assertEquals(4, frequencyMap.size());
    assertEquals(1, frequencyMap.get('h'));
    assertEquals(1, frequencyMap.get('e'));
    assertEquals(2, frequencyMap.get('l'));
    assertEquals(1, frequencyMap.get('o'));
  }

  @Test
  void testFindMostCommonCharacter() throws Exception {
    Method buildCharFrequencyMapMethod =
        TextAnalysisTool.class.getDeclaredMethod("buildCharFrequencyMap", String.class);
    buildCharFrequencyMapMethod.setAccessible(true);
    Map<Character, Integer> frequencyMap =
        (Map<Character, Integer>) buildCharFrequencyMapMethod.invoke(null, "hello");

    Method method = TextAnalysisTool.class.getDeclaredMethod("findMostCommonCharacter", Map.class);
    method.setAccessible(true);
    assertEquals('l', method.invoke(null, frequencyMap));
  }

  @Test
  void testCalculateWordFrequency() throws Exception {
    Method method =
        TextAnalysisTool.class.getDeclaredMethod(
            "calculateWordFrequency", String.class, String.class);
    method.setAccessible(true);
    assertEquals(0, method.invoke(null, "This is a test. This is only a test.", "test"));
    assertEquals(0, method.invoke(null, "This is a test.", "notfound"));
    assertEquals(1, method.invoke(null, "This is a test", "test"));
  }

  @Test
  void testCalculateUniqueWordsCount() throws Exception {
    Method method =
        TextAnalysisTool.class.getDeclaredMethod("calculateUniqueWordsCount", String.class);
    method.setAccessible(true);
    assertEquals(5, method.invoke(null, "This is a test. This is only a test."));
    // assertEquals(0, method.invoke(null, ""));
  }
}
