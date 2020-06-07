package com.eagle.relationaldbaccessapi.util.interfaces.functional;

@FunctionalInterface
public interface IBuilder<L,R> {
	public L build(R model);
}
