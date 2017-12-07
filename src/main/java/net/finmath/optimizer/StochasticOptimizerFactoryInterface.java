/*
 * (c) Copyright Christian P. Fries, Germany. All rights reserved. Contact: email@christianfries.com.
 *
 * Created on 29.05.2015
 */

package net.finmath.optimizer;

import net.finmath.optimizer.StochasticOptimizerInterface.ObjectiveFunction;
import net.finmath.stochastic.RandomVariableInterface;

/**
 * @author Christian Fries
 *
 */
public interface StochasticOptimizerFactoryInterface {

	public StochasticOptimizerInterface getOptimizer(ObjectiveFunction objectiveFunction, RandomVariableInterface[] initialParameters, RandomVariableInterface[] targetValues);

	public StochasticOptimizerInterface getOptimizer(ObjectiveFunction objectiveFunction, RandomVariableInterface[] initialParameters, RandomVariableInterface[] lowerBound, RandomVariableInterface[] upperBound, RandomVariableInterface[] targetValues);

	public StochasticOptimizerInterface getOptimizer(ObjectiveFunction objectiveFunction, RandomVariableInterface[] initialParameters, RandomVariableInterface[] lowerBound, RandomVariableInterface[] upperBound, RandomVariableInterface[] parameterStep, RandomVariableInterface[] targetValues);

}
