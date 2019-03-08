package data.interfaces;

import domain.Subreddit;

import java.util.List;

public interface SubredditDao extends BaseDao<Subreddit> {
    List<Subreddit> getAll();

    Subreddit findByName(String name);
}
