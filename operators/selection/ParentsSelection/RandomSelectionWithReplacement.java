package operators.selection.ParentsSelection;

import java.util.HashMap;

import core.Population;
import operators.selection.Selection;
import util.Random;

public class RandomSelectionWithReplacement extends Selection{
	public RandomSelectionWithReplacement(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	/**
	 * Performs the operation
	 *
	 * @param object
	 *            Object representing a SolutionSet.
	 * @return an object representing an array with the selected parents
	 */
	public Object execute(Object object) {
		Population population = (Population) object;
		int perm;

		perm = Random.nextIntII(0, population.size() - 1);

		return perm;
	} // Execute


}
