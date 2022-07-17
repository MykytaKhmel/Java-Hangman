package Basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
// array list creation
		int score = 0;
		int guesses = 7;
		Scanner input = new Scanner(System.in);
		ArrayList<String> dictionaryList = new ArrayList<String>();
		ArrayList<Highscorer> highscorer = new ArrayList<Highscorer>();
// dictionary reading in
		try {
			File dictionaryFile = new File("res/dictionary.txt");
			Scanner dictionaryReader = new Scanner(dictionaryFile);
			while (dictionaryReader.hasNext()) {
				String temp = dictionaryReader.next();
				dictionaryList.add(temp);

			}
			dictionaryReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found!");
		}
//high score reading in
		try {
			File highscoreFile = new File("res/highscore.txt");
			Scanner highscoreReader = new Scanner(highscoreFile);
			while (highscoreReader.hasNext()) {
				Highscorer temp = new Highscorer(highscoreReader.next(), highscoreReader.nextInt());
				highscorer.add(temp);
			}
			highscoreReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("Highscore file not found!");
		}
//game start, main loop
		while (guesses > 0) {
			int rand = (int) (Math.random() * 120000);
			String theWord = dictionaryList.get(rand - 1);
			theWord = theWord.toUpperCase();
			System.out.println(theWord);
			ArrayList<Character> wordAsList = new ArrayList<Character>();
			ArrayList<Character> wordMasked = new ArrayList<Character>();
			ArrayList<Character> incorrectGuesses = new ArrayList<Character>();

			for (int i = 0; i < theWord.length(); i++) {
				wordAsList.add(theWord.charAt(i));
				wordMasked.add('_');
			}

			guesses = 7;
			boolean notWon = true;
//per word loop
			while (guesses > 0 && notWon) {

				System.out.println();
				System.out.print("Hidden Word: ");
				for (int i = 0; i < theWord.length(); i++) {
					System.out.print(wordMasked.get(i));
					if (i < theWord.length() - 1) {
						System.out.print(" ");
					}
				}
				String incorrectGuessesShown = "";
				for (int i = 0; i < incorrectGuesses.size(); i++) {
					incorrectGuessesShown += incorrectGuesses.get(i);
					if (i < incorrectGuesses.size() - 1) {
						incorrectGuessesShown += ", ";
					}
				}

				System.out.println();

				System.out.println("Incorrect Guesses: " + incorrectGuessesShown);

				System.out.println("Guesses Left: " + guesses);

				System.out.println("Score: " + score);

				System.out.print("Enter next guess: ");
				String unfilteredGuess = "";
				char guess = ' ';

				boolean wrongInput = true;
				while (wrongInput) {
					wrongInput = false;

					unfilteredGuess = input.next();

					if (unfilteredGuess.length() > 1 || unfilteredGuess.isBlank()) {
						wrongInput = true;
						System.out.println("Please enter a single letter!");
					} else {
						guess = unfilteredGuess.charAt(0);
					}
					if (Character.isLetter(guess)) {
						guess = Character.toUpperCase(guess);
					} else {
						if (!wrongInput) {
							wrongInput = true;
							System.out.println("Please enter a single letter!");
						}
					}

					for (int i = 0; i < theWord.length(); i++) {
						if (Character.compare(guess, wordMasked.get(i)) == 0) {
							if (!wrongInput) {
								wrongInput = true;

								System.out.println("This letter has already been used!");
								System.out.println("Please enter a single letter!");

							}
						}

					}

					for (int i = 0; i < incorrectGuesses.size(); i++) {
						if (Character.compare(guess, incorrectGuesses.get(i)) == 0) {
							if (!wrongInput) {
								wrongInput = true;

								System.out.println("This letter has already been used!");
								System.out.println("Please enter a single letter!");

							}
						}

					}

				}

				boolean found = false;
				notWon = true;
				int underScores = 0;

				for (int i = 0; i < theWord.length(); i++) {
					if (Character.compare(guess, wordAsList.get(i)) == 0) {
						wordMasked.set(i, wordAsList.get(i));
						found = true;
						score += 10;
					}
					if (Character.compare(wordMasked.get(i), '_') == 0) {
						underScores++;
					}
				}

				if (underScores == 0) {
					notWon = false;
					score += 100 + guesses * 30;
				}

				if (!found) {
					guesses--;
					System.out.println("Sorry, there were no " + guess + "'s");
					incorrectGuesses.add(guess);
					Collections.sort(incorrectGuesses);
				}

			}
// end of per word loop

		}
// high score resolution
		boolean highScore = false;
		int betterThen = 0;
		String highName = "";
		for (int i = 0; i < highscorer.size(); i++) {
			if (score > highscorer.get(i).getScore() && !highScore) {
				highScore = true;
				betterThen = i;
			}
		}

		if (highScore) {
			System.out.println("Congradulations, you got a Highscore!");
			System.out.println("Please enter your name:");
			highName = input.next();
			Highscorer replacement = new Highscorer(highName, score);
			highscorer.add(betterThen, replacement);
		}
		System.out.println("Highscores:");
		for (int i = 0; i < 5; i++) {
			System.out.println((i + 1) + " " + highscorer.get(i).getName() + " - " + highscorer.get(i).getScore());
		}

		FileWriter fw;
		try {
			fw = new FileWriter("res/highscore.txt");
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < 5; i++) {
				pw.print(highscorer.get(i));
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Highscore file not found!");
		}
		input.close();

	}
}
