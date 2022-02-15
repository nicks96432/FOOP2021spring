# Design Report

> Please follow the instructions in homework 1 (officially announced version on NTU COOL) to finish the report.

## Software Design

> The purpose of the design report is to help the TAs grade the software design (and bonus) part by human.  
> We hope that you can illustrate every class of your design with:
> its name
> a **one-sentence** description of its duty
> a **short** description of how it possibly interacts with other classes
> Don't elaborate too much on every class
> since we will directly inspect your code for details.

### `class Main`

Entrypoint of the program.  
It instantiates a `Game` and starts the program by calling `Game.start()`.

### `class main.Data`

Storing the card deck and the players' names.  
`Game` calls `Data.dealCards()` to deal cards to everyone, and `Try` calls `Data.readAction()` to read what the current player intends to do and convert the result to a `Combo`.

### `class main.Player`

Representing a player.  
It stores a player's name, an integer identifier, and his/her hand cards.

### `class main.Game`

Controlling the game flow.  
It uses `Data.loadData()` to get the cards and player names from `System.In`, and create `Try`s for each player in each round according to the data in `data`.

### `class main.Try`

The try and error of a player.  
If you call `Try.tryAndError()`, It will call `Data.readAction()` to get the intention of the player, convert it to a `Combo`, use `ComboValidator` and `ComboComparator` to validate it, and return it.

### `class main.card.Card`

Representing a card.  
It stores the suit and rank of a card, using `enum SuitType` and `enum RankType`.

### `enum main.card.SuitType`

Enumeration of all kinds of suits in poker.  
It is used to store the suit in a `Card`.

### `enum main.card.RankType`

Enumeration of all kinds of ranks in poker.  
It is used to store the rank in a `Card`.

### `class main.combo.Combo`

Representing a way to play cards, valid or not.  
`Try.tryAndError()` returns a `Combo` to `Game`, and `Game` removes the cards from the player accordingly.

### `class main.combo.ComboType`

Enumeration of all kinds of valid ways to play cards, including a pass.  
`ComboComparator.compare()` compares two `Combo`s according to their `ComboType`,and `ComboValidator.validate()` validates a `Combo` by the same way.

### `class main.combo.ComboComparator`

Comparing two `Combo`s according to their `ComboType`.  
`Try` uses this to check if the intended `Combo` of a player is bigger than the top play.

### `class main.combo.ComboValidator`

Validating a `Combo` according to its `ComboType`.  
`Try` uses this to check if the intended `Combo` of a player is valid.

## Bonus Design: Open-Closed Principle (OCP)

> You will either get 0 or 20 points on this bonus.
> If you can't achieve it, don't report anything here.
