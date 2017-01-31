/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * Example:
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * Snake snake = new Snake(width, height, food);
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * |S| | |
 * | | |F|
 * snake.move("R"); -> Returns 0
 * | |S| |
 * | | |F|
 * snake.move("D"); -> Returns 0
 * | | | |
 * | |S|F|
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 * | |F| |
 * | |S|S|
 * snake.move("U"); -> Returns 1
 * | |F|S|
 * | | |S|
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * | |S|S|
 * | | |S|
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

public class SnakeGame {
	// idea: change 2-D pos into 1-D and use double-ended queue to store the position of body
	Set<Integer> set;	// stores indices of snake body, so can easily check for eating body case
	Deque<Integer> body;	// stores the current snake body in order
	int score;
	int[][] food;
	int foodIndex;
	int width;
	int height;

	public SnakeGame(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		set = new HashSet<>();
		set.add(0);	// initially at [0][0]
		body = new LinkedList<>();
		body.offerLast(0);
	}

	public int move(String direction) {
		if (score == -1) {	// game already over, then do nothing
			return -1;
		}
		// compute the pos of new head
		int row = body.peekFirst() / width;
		int col = body.peekFirst() % width;
		if (direction.equals("U")) {
			row--;
		} else if (direction.equals("D")) {
			row++;
		} else if (direction.equals("L")) {
			col--;
		} else if (direction.equals("R")) {
			col++;
		} else {
			return -1;
		}
		int newHead = row * width + col;
		set.remove(body.peekLast());	// new head can be in the old tail's position, so first remove
		// case 1: if reach the boundary or eat body, then game over
		if (row < 0 || row == height || col < 0 || col == width || set.contains(head)) {
			score = -1;
			return score;
		}
		// game not over, so add the new head
		set.add(newHead);
		body.offerFirst(newHead);
		// case 2: eat the food, then old tail doesn't change, so add it back.
		if (foodIndex < food.length && row == food[foodIndex][0] && col == food[foodIndex][1]) {
			set.add(body.peekLast());
			foodIndex++;
			score++;
			return score;
		}
		// case 3: normal move, remove tail, add new head
		body.pollLast();
		return score;
	}
}