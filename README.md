Goals:
Use this as a starting point of challenging myself for different projects.

* To do list:
  * Placing ships
    * GUI for choosing which ship to place
      * This will need to be updated whenever a boat is successfully placed
    * Logic for parsing and responding if that was a valid location
    * Tracking the placement selection
  * Logic to disable buttons on player's board when all boats have been board
  * Add listeners to opposing board for location selection
    * This creates a unique scenario where we will have to consider how to differentiate between player and npc

Tasks:
* GameController
  * Create controller 
  * Manage game state
    * Playing, winner, stopped
    * Player turn
  * Manage game:
    * Start, Stop
    * Save, Load
  * Stores
    * GUI
    * Player objects
* Players
  * Abstract Player
  * HumanPlayer
  * NPC
  * Each should have objects:
    * Incorrect guesses
    * Correct guesses
    * Ships
      * Have an enum that identifies the kinds of ships that need created and the stats about the ship
      * Have an actual boat object that 
    * Board
      * For implementation do we need to create a new class or just use an array?
  * Each should have actions:
    * Place ships 
      * This might actually need to be handled by the controller. It should be the object that tells the user if that is a valid location or not.
    * Guess Location (this may be a controller thing, as the player tell the controller his/her guess)
      * Although the computer would have to choose a place so it may need something here...
    * Guess response
* GUI
* Create a "Boat" object that will hold information regarding the pieces

* Completed:
  * Classes:
    * Controller
      * Created
      * Instance variables
        * players
          * player1 and player2 will be used to keep track of who is playing. 
          They can be set to either a player or an NPC
          * Scanner
            * Used for the base CLI game
          * Playing
            * a boolean that keeps the game going as long as there is not winner
    * Abstract player
      * Created
      * Instance variables
        * Correct guess list
        * incorrect guess list
        * Board
        * List of ships they have
    * Npc
      * Created
    * Board
      * Created
    * Ship
      * Created
    * ShipTypes
      * Created
  * GUI
    * Created a BattleshipFrame class to hold the board gui pieces
    * Created a BoardPanel class that is used for the actual battleship board
    * Within controller
      * Created a Scrollable text field that will be used to provide text output for the user