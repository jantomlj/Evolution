package prisdilemma.strategies;

public class Strategies {

	/**
	 * Tit for tat is a strategy which starts with being nice and then copies it's opponent's
	 * moves.
	 */
	public static class TitForTat extends Strategy {

		private Act lastOpponentsMove;

		public TitForTat(Integer id) {
			super(id);
			name = "Tit for tat";
		}

		@Override
		public Act execute() {
			if (lastOpponentsMove == null) {
				return Act.COOPORATE;
			}
			// tit for tat reversed ?? works funny
			return lastOpponentsMove == Act.COOPORATE ? Act.DEFECT : Act.COOPORATE;
		}

		@Override
		public void acknowledgeOpponentMove(Act act) {
			lastOpponentsMove = act;
		}

	}

	/**
	 * Names speaks for itself
	 */
	public static class AlwaysCooperate extends Strategy {

		public AlwaysCooperate(Integer id) {
			super(id);
			name = "Always cooperate";
		}

		@Override
		public Act execute() {
			return Act.COOPORATE;
		}

		@Override
		public void acknowledgeOpponentMove(Act act) {
		}

	}

	/**
	 * Names speaks for itself
	 */
	public static class AlwaysDefect extends Strategy {

		public AlwaysDefect(Integer id) {
			super(id);
			name = "Always defect";
		}

		@Override
		Act execute() {
			return Act.DEFECT;
		}

		@Override
		void acknowledgeOpponentMove(Act act) {
		}
	}


}
