=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: rmarko
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D arrays - My game used a 2D array as the key structure. This is because a 2D array resembles
  a game board. The 2D array was of type Circle, an object I created, which represented the pieces
  you place in Connect 4. A white circle represents an empty spot in the array, a red circle 
  represents a piece belonging to player 1, and a black circle represents a piece belonging to  
  player 2. The 2D array is zero-indexed and of size 7 x 6, like a typical Connect 4 board.

  2. J Unit testing - My game uses J unit testing to test the methods written in GameBoard and 
  GameCourt. I test for proper functionality, as well as correct handling of edge cases to ensure
  the game works properly.

  3. Collections - My game uses a collection to keep track of prior moves. Specifically, I use a 
  Linked List of type Circle to keep track of each move that was added to the board. This gives me
  the ability to use the Undo button, which not only removes the piece from the board, but also the 
  Circle from the list. Similarly, reset clears the whole list. 

  4. File I/O - My game uses File I/O to read and write high (or technically low) scores. The 
  Leaderboard button writes a file of usernames and the least amount of moves they took to win. The 
  file is updated every time there is a win. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The Circle class is an object representing each piece in the game. Each circle stores
  its color and x and y coordinates. If the circle is white, it represents an empty spot in
  the board, if it is red it represents Player 1, and if it is black it represents player 2.
  
  The GameBoard class is an object representing the board of the game. It is a 2D array 
  of type Circle. All of the spots in the array are initialized to a white circle, indicating 
  a completely empty board. This class also contains the logic for adding a piece to the 
  board and winning the game.
  
  The GameCourt class contains methodology for how the game is actually played. It calls on 
  functions written in the GameBoard class. 
  
  The Game class is where the game is actually run and the buttons and interface is written.
  
  The Connect4Test class is where I implemented J Unit testing to test the methods I wrote in
  GameBoard and GameCourt.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  While I initially found it a little challenging as to how the File I/O implementation would work,
  it ended up not being as difficult as I expected. The one challenge I really had was how to access 
  different variables in the different classes I had, but I ended up passing them in as arguments 
  to the function.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think my design has a very good separation of functionality. Each class is centered around a
  specific aspect of the game, which makes my design easy to understand. I also think it was helpful 
  to make the board a 2D array of type Circle, rather than integer, because they represented the 
  pieces you play with. The private state is also encapsulated well, because a user cannot change 
  any important values in while playing. If given the chance, I would maybe change some of the
  fields and methods I had in GameCourt and see if they could be better placed in GameBoard.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  I used the Javadocs for linked lists, JOptionPanes, buffered readers and writers, and the 
  java graphics library.
