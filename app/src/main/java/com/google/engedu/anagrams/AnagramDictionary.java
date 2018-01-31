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

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;

        //process list of words.
        ArrayList<String> wordList = new ArrayList<String>(); //initialize wordlist
        anagramSet = new HashSet<String>();
        anagramMap = new HashMap<String, ArrayList<String>>();
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);     //read dictionary, add to wordList
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
            wordList.remove(0); //remove the first item
        }

    }

    public boolean isGoodWord(String word, String base) {
        return true;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

/*        String sortedTargetWord = sortString(targetWord);   //sort target word
        if(anagramSet.contains(targetWord)){
            return anagramMap.get(targetWord);
        }
*/
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
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
