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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private HashSet<String> anagramSet;
    private HashMap<String, ArrayList<String>> anagramMap;
    private ArrayList<String> wordList;

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        //process list of words.
        wordList = new ArrayList<String>(); //initialize wordlist
        anagramSet = new HashSet<String>();
        anagramMap = new HashMap<String, ArrayList<String>>();
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);     //read dictionary, add to wordList
            anagramSet.add(word);
        }

        for(int i = 0; i < wordList.size(); i++){
            String initialString = wordList.get(i);
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
        System.out.println("WORD SIZE" + anagramMap.size());
    }

    public boolean isGoodWord(String word, String base) {
        String key = sortString(base);
        ArrayList<String> correctWords = new ArrayList<String>();
        correctWords = anagramMap.get(key);

        for(int i = 0; i < correctWords.size(); i++){
            if(correctWords.get(i) == word)
                return true;
        }
        return false;
    }

    //doesn't check if targetWord is valid word, just returns anagrams associated with it
    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        String sortedTargetWord = sortString(targetWord).toLowerCase();   //sort target word
        if(anagramMap.containsKey(sortedTargetWord)){
            result = anagramMap.get(sortedTargetWord);  //get anagrams
        }

        return result;
    }

    //takes string, sorts char by alphabetical order
    private String sortString(String word){
        char wordCharArray[] = word.toLowerCase().toCharArray();  //turn to char array, sort using util.sort
        Arrays.sort(wordCharArray);
        return new String(wordCharArray);
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String biggerWord = word;
        for(int i = 0; i < 26; i++){
            biggerWord = word + Character.toString((char)('a'+i));    //add letters from alphabet to
            result.addAll(getAnagrams(biggerWord));
        }
        return result;
    }

    public String pickGoodStarterWord() {
        return "STOP";
    }
}
