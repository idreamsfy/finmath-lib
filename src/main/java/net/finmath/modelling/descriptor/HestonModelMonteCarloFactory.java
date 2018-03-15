/*
 * (c) Copyright Christian P. Fries, Germany. Contact: email@christian-fries.de.
 *
 * Created on 09.02.2018
 */

package net.finmath.modelling.descriptor;

import net.finmath.modelling.Model;
import net.finmath.modelling.ModelFactory;
import net.finmath.modelling.Product;
import net.finmath.modelling.ProductDescriptor;
import net.finmath.modelling.SingleAssetProductDescriptor;
import net.finmath.montecarlo.AbstractRandomVariableFactory;
import net.finmath.montecarlo.IndependentIncrementsInterface;
import net.finmath.montecarlo.assetderivativevaluation.HestonModel.Scheme;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.model.AbstractModelInterface;
import net.finmath.montecarlo.process.AbstractProcessInterface;
import net.finmath.montecarlo.process.ProcessEulerScheme;

/**
 * @author Christian Fries
 */
public class HestonModelMonteCarloFactory implements ModelFactory<HestonModelDescriptor> {

	private final net.finmath.montecarlo.assetderivativevaluation.HestonModel.Scheme scheme;
	private final AbstractRandomVariableFactory randomVariableFactory;
	private final IndependentIncrementsInterface brownianMotion;


	public HestonModelMonteCarloFactory(Scheme scheme, AbstractRandomVariableFactory randomVariableFactory,
			IndependentIncrementsInterface brownianMotion) {
		super();
		this.scheme = scheme;
		this.randomVariableFactory = randomVariableFactory;
		this.brownianMotion = brownianMotion;
	}


	@Override
	public Model<HestonModelDescriptor> getModelFromDescriptor(HestonModelDescriptor modelDescriptor) {
		class HestonMonteCarloModel extends MonteCarloAssetModel implements Model<HestonModelDescriptor> {

			final SingleAssetMonteCarloProductFactory productFactory = new SingleAssetMonteCarloProductFactory();
			
			/**
			 * @param model
			 * @param process
			 */
			public HestonMonteCarloModel(AbstractModelInterface model, AbstractProcessInterface process) {
				super(model, process);
			}
			
			@Override
			public HestonModelDescriptor getDescriptor() {
				return modelDescriptor;
			}

			@Override
			public Product<? extends ProductDescriptor> getProductFromDesciptor(ProductDescriptor productDescriptor) {
				if(productDescriptor instanceof SingleAssetProductDescriptor) {
					return productFactory.getProductFromDescription((SingleAssetProductDescriptor) productDescriptor);
				}
				else {
					String name = modelDescriptor.name();
					throw new IllegalArgumentException("Unsupported product type " + name);
				}
			}	
		};

		return new HestonMonteCarloModel(
				new net.finmath.montecarlo.assetderivativevaluation.HestonModel(modelDescriptor, scheme, randomVariableFactory), 
				new ProcessEulerScheme(brownianMotion)
				);
	}

}
