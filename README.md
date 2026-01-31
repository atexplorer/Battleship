Goals:
Use this as a starting point of challenging myself for different projects.

Tasks:
* Create GameController
  * Manage game state
    * Playing, winner, stopped
    * Player turn
  * Manage game:
    * Start, Stop
    * Save, Load
  * Stores
    * GUI
    * Player objects
* Create abstraction of player (implementations: Player NPC)
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
    * Guess Location
    * Guess response
* GUI
  * 
* Create a "Boat" object that will hold information regarding the pieces