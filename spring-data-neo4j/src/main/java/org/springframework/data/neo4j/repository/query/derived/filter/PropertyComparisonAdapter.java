/*
 * Copyright (c)  [2011-2016] "Pivotal Software, Inc." / "Neo Technology" / "Graph Aware Ltd."
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */

package org.springframework.data.neo4j.repository.query.derived.filter;

import java.util.Map;

import org.neo4j.ogm.cypher.function.FilterFunction;
import org.neo4j.ogm.cypher.function.PropertyComparison;
import org.springframework.data.neo4j.repository.query.derived.CypherFilter;

/**
 * Adapter to the OGM FilterFunction interface for a PropertyComparison.
 *
 * @author Jasper Blues
 * @see FunctionAdapter
 */
public class PropertyComparisonAdapter implements FunctionAdapter<Object> {

	private CypherFilter cypherFilter;
	private PropertyComparison propertyComparison;

	public PropertyComparisonAdapter(CypherFilter cypherFilter) {
		this.cypherFilter = cypherFilter;
		this.propertyComparison = new PropertyComparison();
	}

	public PropertyComparisonAdapter() {
		this(null);
	}

	public CypherFilter getCypherFilter() {
		return cypherFilter;
	}

	public void setCypherFilter(CypherFilter cypherFilter) {
		this.cypherFilter = cypherFilter;
	}

	@Override
	public CypherFilter cypherFilter() {
		return null;
	}

	@Override
	public FilterFunction<Object> filterFunction() {
		return new PropertyComparison(propertyComparison.getValue(), propertyComparison.getFilter());
	}

	@Override
	public int parameterCount() {
		return 1;
	}

	@Override
	public void setValueFromArgs(Map<Integer, Object> params) {
		if (cypherFilter == null) {
			throw new IllegalStateException("Can't set value from args when cypherFilter is null.");
		}
		propertyComparison.setValue(params.get(cypherFilter.getPropertyPosition()));
	}
}
