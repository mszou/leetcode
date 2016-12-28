/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * Example:
 * Twitter twitter = new Twitter();
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */


public class Twitter {
	// OO Design (a Tweet class, a User class)
	// idea: HashMap + PriorityQueue (heap)
	private static int timeStamp = 0;  // used for time order

	private Map<Integer, User> userMap;	// userMap<userId, user>, easy to find whether this user exists

	private class Tweet {
		public int id;
		public int time;
		public Tweet next;    // point to next Tweet
		public Tweet(int id) {	// constructor
			this.id = id;
			time = timeStamp++;
			next = null;	// link to next Tweet to save time when execute getNewsFeed(userId)
		}
	}

	public class User {
		public int id;
		public Set<Integer> following;	// userIds of this user's following
		public Tweet tweet_head;
		public User(int id) {	// constructor
			this.id = id;
			following = new HashSet<>();
			follow(id);	// follow himself so that he can see his own tweets
			tweet_head = null;
		}

		public void follow(int id) {
			following.add(id);
		}

		public void unfollow(int id) {
			if (id != this.id) {	// according to one testcase, user cannot unfollow himself
				following.remove(id);
			}
		}

		// every time user posts a new tweet, add it to the head of tweet list.
		public void post(int id) {
			Tweet t = new Tweet(id);
			t.next = tweet_head;
			tweet_head = t;
		}
	}

    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
        	User u = new User(userId);
        	userMap.put(userId, u);
        }
        userMap.get(userId).post(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    // 1. get all tweets lists from one user including himself and all users he followed.
    // 2. add all heads into a max heap. Everytime we poll a tweet with largest timestamp from the heap, then we add its next tweet into the heap
    // So after adding all heads we only need to add at most 9 tweets inro this heap before we get the 10 most recent tweets
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();
        if (!userMap.containsKey(userId)) {
        	return res;
        }
        Set<Integer> users = userMap.get(userId).following;
        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>((a, b) -> (b.time - a.time));
        for (int user : users) {
        	Tweet t = userMap.get(user).tweet_head;
        	// very important! Never add null to the heap!
        	if (t != null) {
        		q.add(t);
        	}
        }
        int n = 0;
        while (!q.isEmpty() && n < 10) {
        	Tweet t = q.poll();
        	res.add(t.id);
        	n++;
        	if (t.next != null) {
        		q.add(t.next);
        	}
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
        	User u1 = new User(followerId);
        	userMap.put(followerId, u1);
        }
        if (!userMap.containsKey(followeeId)) {
        	User u2 = new User(followeeId);
        	userMap.put(followeeId, u2);
        }
        userMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
        	User u1 = new User(followerId);
        	userMap.put(followerId, u1);
        }
        if (!userMap.containsKey(followeeId)) {
        	User u2 = new User(followeeId);
        	userMap.put(followeeId, u2);
        }
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */