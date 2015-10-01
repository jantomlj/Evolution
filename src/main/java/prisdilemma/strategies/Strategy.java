package prisdilemma.strategies;

/**
 * Abstract class for the strategies
 */
public abstract class Strategy {

	protected String name = null;
	protected Integer id;

	// setting default fitness
	private Integer fitness = 1;

	public Strategy(Integer id) {
		this.id = id;
	}

	/**
	 * Execute the next step of the strategy. Depending on the strategy, return your next act.
	 */
	abstract Act execute();

	/**
	 * Find out what did the opponent do. This method must be called after each call of execute
	 * method.
	 */
	abstract void acknowledgeOpponentMove(Act act);

	String getName() {
		return name;
	}

	Integer getId() {
		return id;
	}

	/**
	 * Method invoked by the class that simulates the game.
	 */
	void increaseFitnesBy(Integer change) {
		fitness += change;
	}
	
	Integer getFitness () {
		return fitness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Strategy other = (Strategy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
