package domain.interfaces;

import domain.User;

public interface Judgeable {
   int getUpvotes();
   int getDownvotes();
   User getCreator();

    /***
     * calculates the score of the item using the up and down-votes
     * @return an integer representing the score.
     */
   int getScore();
}
