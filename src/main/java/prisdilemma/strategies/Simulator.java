package prisdilemma.strategies;
/**
 * Simulator of the prisoners dilemma game.
 */
public class Simulator {

	/** Numbers that determine the gains in fitness depending on the
	 * outcome of the match.
	 */
	private static final int a = 4; //3
	private static final int b = 2; //5
	private static final int c = 6; //4
	private static final int d = 0; //2
	
	/**Rules of the match:
	 *            Cooperate    Defect
	 * Cooperate    (a, a)     (b, c)
	 * Defect       (c, b)     (d, d)
	 * 
	 * 
	 */
	public static void match (Strategy s1, Strategy s2) {
		Act a1 = s1.execute();
		Act a2 = s2.execute();
		
		int change1;
		int change2;
		
		if (a1 == Act.COOPORATE && a2 == Act.COOPORATE) {
			change1 = change2 = a;
		}
		else if (a1 == Act.COOPORATE && a2 == Act.DEFECT) {
			change1 = b;
			change2 = c;
		}
		else if (a1 == Act.DEFECT && a2 == Act.COOPORATE) {
			change1 = c;
			change2 = b;
		}
		else {
			change1 = change2 = d;
		}
		s1.increaseFitnesBy(change1);
		s2.increaseFitnesBy(change2);
		
		s1.acknowledgeOpponentMove(a2);
		s2.acknowledgeOpponentMove(a1);
	}
}
