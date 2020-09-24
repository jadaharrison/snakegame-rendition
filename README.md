# snakegame-rendition
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the three core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections - Instead of using collections to keep a score as mentioned in the proposal, I 
  decided to use them, specifically a LinkedList to store my snake. I was unable to do so using a
  2D array as I had planned, so I decided on using a LinkedList due to the ease of adding/removing
  from both ends, a suitable feature for snake implementation.

  2. Testing - Testing was a key concept for Snake, as it ensured that the joints of the snake were
  added or removed properly, the joints move correctly when given a specific direction, the 
  reset snake was as it should be when requested by the player.  

  3. Interface/Subtyping - I used a basic interface to hold the numerous game constants that I 
  wabted to be referentiable to all classes in the game without any hassle. I also created an 
  abstract Class circle. This was appropriate as though the circles have the same structure, they
  perform two different things. I created two subclasses, GoodCircle and BadCircle, where each 
  overrode the draw and changeSnake methods of the abstract class. For GoodCircle, the color was 
  green and it changed the snake by growing it. For BadCircle, the color was red, and it changed the
  snake by removing the last joint from the LinkedList. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Circle (with subclasses GoodCircle and BadCircle) - abstract class that defined the basics of the
  'powerup' and 'powerdown' for the snake. Subclasses GoodCircle and BadCircle grow and shorten the
  snake when appropriate via the changeSnake method. 
  
  Game - this is what runs the game. It takes all of the visual components and puts them together as 
  defined in the JFrame. It is also responsible for actually running the game, keeping it going while
  the player plays, resetting when the player has lost, and shutting it down whenever requested. 
  
  GameCourt - this is where all of the components come together and work with each other. In this
  class, I have created the LinkedList of snakes, denoted the status of the game, if it is being 
  played or not, and keeps track of the snake's direction. GameCourt is responsible for how the game
  should respond when certain interactions or events occur. Additionally, GameCourt is responsible 
  for the continual updating of the game, so the player actually seens the game change as they play. 
  
  SnakeJoint - this is a class that defines what one snake joint looks like. Its fields are the 
  x and y components of each joint, and has methods to get or set them. 

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  The biggest stumbling block I had was how to actually store the snake. At first, due to the fact
  that snake is of a grid layout, I figured this would have been the best way to store the snake's
  information. However, after a lot of trial and error, I realised that this was not the best. 
  With a new form of implementation needed, I remembered the TA help document that mentioned
  collections could be a possible way of storing data for the snake, so I looked back on recitation
  notes and javadocs and realised that regarding moving the snake, a linked list would be best. 
  
  I wanted to test the addition of snake joints similar to how it was tested during PennPals. 
  At first, my implementation involved a class Snake with an inner class SnakeJoint, but then I 
  realised that in order for me to do it like PennPals, I had to just make one class, SnakeJoint,
  then make the snake a LinkedList<SnakeJoint> in the actual GameCourt class. 
  


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I'm not sure how exactly, but I think there could be a way to consolidate the code for moveSnake 
  and changeDirection in GameCourt, as it is repetitive.  



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
How to produce random numbers within a range: 
https://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-in-java-between-range.html
https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values

Information of LinkedLists: https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
