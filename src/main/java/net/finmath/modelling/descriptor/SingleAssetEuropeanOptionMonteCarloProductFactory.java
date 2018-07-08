/*
 * (c) Copyright Christian P. Fries, Germany. Contact: email@christian-fries.de.
 *
 * Created on 09.02.2018
 */

package net.finmath.modelling.descriptor;

import net.finmath.modelling.DescribedProduct;
import net.finmath.modelling.ProductFactory;

/**
 * @author Christian Fries
 */
public class SingleAssetEuropeanOptionMonteCarloProductFactory implements ProductFactory<SingleAssetEuropeanOptionProductDescriptor> {

	/**
	 * Create factory.
	 */
	public SingleAssetEuropeanOptionMonteCarloProductFactory() {
	}

	@Override
	public DescribedProduct<SingleAssetEuropeanOptionProductDescriptor> getProductFromDescription(SingleAssetEuropeanOptionProductDescriptor descriptor) {

		DescribedProduct<SingleAssetEuropeanOptionProductDescriptor> product = new net.finmath.montecarlo.assetderivativevaluation.products.EuropeanOption(descriptor);
		return product;
	}
}
