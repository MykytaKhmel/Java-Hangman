# Java-Hangman
Class Project from COSC 121

Hangman

In this assignment you will create a hangman game while also keeping track of the user’s high scores.  I will provide a text file full of roughly 120,000 words that you can randomly pick from, which contains the full contents of the Official Scrabble Player's Dictionary, Second Edition1.  Your program will display the number of characters in the hidden word, and if the user has correctly guessed any letters, display that letter in the correct spot.  The user will have 7 guesses to figure out the word, which resets on each correctly solved word.  A correct guess does not reduce the number of guesses.  On the 7th wrong guess, the game ends, and the user is shown their score, and if they make the top 5 all-time high scores, they are asked to enter their name.  Then the high score list is displayed and the program is done.  You must also handle the input correctly, asking the user to re-enter any guesses that aren’t valid.

Assume that the randomly chosen word was house.  If the user has made 3 guesses, L, B, S the output would look like this:
Hidden Word: _ _ _ S _
Incorrect Guesses: B, L
Guesses Left: 5
Score: 10
Enter next guess: D
Sorry, there were no D’s

Hidden Word: _ _ _ S _
Incorrect Guesses: B, D, L
Guesses Left: 4
Score: 10
Enter next guess:

Please notice that the Incorrect Guesses list is in alphabetical order.

The scoring works as follows: 
•	For a correct guess, they get 10 points for each instance of the letter.
o	Eg. If the hidden word is "letter"
o	A guess of "r" gets 10 points
o	A guess of "t" gets 20 points
•	Each correct word is worth 100 points + 30 points for each remaining guess left.
