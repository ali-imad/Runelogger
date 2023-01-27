# Petunia

## Description

You are a lone warrior who has a cat (Petunia). Unfortunately, Petunia is prone to fleas, and so every day you must feed
your cat because she is not allowed to leave the cave you reside in. To do this, you must leave the cave every day and
enter a dungeon labyrinth that changes every time you visit in layout. You hunt monsters and turn their
insides/loot into meals for Petunia. Every morning Petunia gives you equippable loot she found in the cave, that only
she can access because of the very tight crevasses separating you from where that loot was, based on how good the meal
was. You wake up gaining xp from the previous night and level up your warrior until you are either tired of fighting or
the strongest warrior in the labyrinth.

This game will be modelled after a traditional roguelike (Brogue/Dungeon Crawl Stone Soup/etc), and thus
the [mechanics are fairly well established from other games in the genre.](http://pcg.wikidot.com/genre:roguelikes) One
core mechanism is reliance on [procedural generation](https://en.wikipedia.org/wiki/Procedural_generation) (essentially,
randomness with rules) to create unique runs with every new game, which will also be utilized heavily in Petunia to
generate events, loot, and maps.

It is meant to be used by fans of the Roguelike genre, or anyone wanting to play something that does not have a long
lasting persistent game, but still has some sense of progression.

This project is of interest to me as I have made a rudimentary games prior to knowledge of object-oriented programming,
 but it will provide me with a chance to apply this knowledge to a project with well-defined mechanics. My only real 
concern  is figuring out how to effectively unit test procedural generation, but I'm not entirely clear on testing 
requirements yet.

## User Stories

- As a user, I should be able to make a new game with a unique generation (i.e. no two worlds are exactly the same
  except by incredible chance)
- As a user, I should be able to move around.
- As a user, I should be able to attack enemies with melee (side by side) combat
- As a user, I should be able to cast a special ability (i.e. a Powerful Attack) separate from my main attack that
  consumes some [resource](https://en.wikipedia.org/wiki/Magic_(game_terminology))
- As a user, I should be able to regenerate the personal ability resource passively over time
- As a user, I should be able to take damage from enemies
- As a user, I should be able to gain experience, eventually levelling up my character
- As a user, I should be able to level up my attributes to my selection when I have levelled up
- As a user, I should be able to leave my game and have it save my session
- As a user, I should be able to lose the game and "retire" with
  Petunia, [clearing my session](http://www.roguebasin.com/index.php/Permadeath)
- As a user, I should be able to continue my previous session
- As a user, I should be able to start a new session, wiping out my old session
- As a user, I should be able to see enemies drop things when they die,
- As a user, I should be able to pick up Ingredients from enemies I have defeated
- As a user, I should be able to pick up some items that are usable, which grant me beneficial/detrimental effects
- As a user, I should be able to access a Backpack that stores a limited number of items
- As a user, I should be able to remove items from my Backpack, freeing up space
- As a user, I should be able to upgrade the capacity of my Backpack with use of some item
- As a user, I should be able to pick up objects from enemies and put them in my Backpack
- As a user, I should be able to mix my loot into a pot, producing a procedurally generated Meal
- As a user, I should be able to feed the Meal to Petunia in exchange for armour or weaponry
- As a user, I should be able to make different Meals with different Ingredients (quantity and type)
- As a user, I should be able to make the same Meals using the same recipe (same Ingredients and quantity)

