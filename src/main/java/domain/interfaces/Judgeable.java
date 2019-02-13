package domain.interfaces;

import domain.Profile;

public interface Judgeable {
   int getUpvotes();
   int getDownvotes();
   Profile getCreator();

    /***
     * calculates the score of the item using the up and down-votes
     * @return an integer representing the score.
     */
   int getScore();
}
