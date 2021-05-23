package com.proiectjava2.proiectjavaii.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Dictionary
{
    private final Set<String> wordsSet;

    public Dictionary() throws IOException
    {
        Path path = Paths.get("C:\\Users\\40757\\Desktop\\Sapt12\\ProiectJavaII\\src\\main\\java\\com\\proiectjava2\\proiectjavaii\\model\\words.txt");
        byte[] readBytes = Files.readAllBytes(path);
        String wordListContents = new String(readBytes, StandardCharsets.UTF_8);
        String[] words = wordListContents.split("\r\n");
        for (int index = 0; index < words.length; index++) {
            words[index] = words[index].toLowerCase();
        }
        wordsSet = new HashSet<>();
        Collections.addAll(wordsSet, words);
    }

    public boolean contains(String word)
    {
        return wordsSet.contains(word.toLowerCase());
    }
}
