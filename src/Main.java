import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filepath = "src\\words.txt";
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
            return;
        } catch (IOException e) {
            System.out.println("Something went wrong");
            return;
        }

        if (words.isEmpty()) {
            System.out.println("No words found in file.");
            return;
        }

        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));  // or use "pizza"

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordstate = new ArrayList<>();
        int wrongguesses = 0;

        for (int i = 0; i < word.length(); i++) {
            wordstate.add('_');
        }

        System.out.println("Welcome to Java Hangman!");

        while (wrongguesses < 6) {
            System.out.println(gethangmanart(wrongguesses));

            System.out.print("Word: ");
            for (char c : wordstate) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                System.out.println("Correct guess!");
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordstate.set(i, guess);
                    }
                }
                if (!wordstate.contains('_')) {
                    System.out.println(gethangmanart(wrongguesses));
                    System.out.println("YOU WIN!");
                    System.out.println("The word was: " + word);
                    break;
                }
            } else {
                wrongguesses++;
                System.out.println("Wrong guess!");
            }
        }

        if (wrongguesses >= 6) {
            System.out.println(gethangmanart(wrongguesses));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);
        }

        scanner.close();
    }

    static String gethangmanart(int wrongguesses) {
        return switch (wrongguesses) {
            case 0 -> """
                    
                    
                    """;
            case 1 -> """
                      o
                      
                      
                    """;
            case 2 -> """
                      o
                      |
                      
                    """;
            case 3 -> """
                      o
                     /|
                      
                    """;
            case 4 -> """
                      o
                     /|\\
                      
                    """;
            case 5 -> """
                      o
                     /|\\
                     /
                    """;
            case 6 -> """
                      o
                     /|\\
                     / \\
                    """;
            default -> "";
        };
    }
}
