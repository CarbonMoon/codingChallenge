/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList;
    private HashMap<String, ArrayList<String>> anagramMap;
    private HashSet<String> anagramSet;



    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordList  = new ArrayList<String>();//initialize
        anagramSet = new HashSet<String>();
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            anagramSet.add(word);
        }
        while(wordList.size() > 0){
            String initialString = wordList.get(0);
            //gets array with list of anagrams
            ArrayList<String> anagramList = anagramMap.get(sortString(initialString));
            if(anagramList == null){    //empty list, create new array and add word to it
                anagramList = new ArrayList<String>();
                anagramList.add(initialString);
            }else{  //add word to array
                anagramList.add(initialString);
            }
            anagramMap.put(sortString(initialString), anagramList);    //puts the new arrayList into alphabetSet
        }


    }

    public boolean isGoodWord(String word, String base) {

        String key = sortString(base);
        ArrayList<String> correctWords = new ArrayList<String>();
        correctWords = anagramMap.get(key);
        for (int i = 0; i<correctWords.size();i++){
            if(correctWords.get(i) == word){
                return true;
            }
        }
        return true;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        String sortedTargetWord = sortString(targetWord);   //sort target word
        if(anagramMap.containsKey(sortedTargetWord)){
            return anagramMap.get(sortedTargetWord);
        }

        return result;
    }

    //takes string, sorts char by alphabetical order
    private String sortString(String word){
        char wordCharArray[] = word.toCharArray();  //turn to char array, sort using util.sort
        Arrays.sort(wordCharArray);
        return new String(wordCharArray);
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String biggerWord = word;

        for(int i = 0; i< 26;i++){
            biggerWord = word + Character.toString((char)('a'+i));
            result.addAll(getAnagrams(biggerWord));
        }
        return result;
    }

    public String pickGoodStarterWord() {
        Random rand = new Random();
        int n = rand.nextInt(wordList.size()) + 0;

        int i = n - 1;
        while (i<wordList.size())
        {
            if (getAnagramsWithOneMoreLetter(wordList.get(n)).size() >= 5)
                return wordList.get(n);
            i++;

            if (n >= wordList.size())
                n = 0;
        }
        return null;
    }
}
