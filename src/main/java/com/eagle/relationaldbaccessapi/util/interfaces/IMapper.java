package com.eagle.relationaldbaccessapi.util.interfaces;

@FunctionalInterface
public interface IMapper<L, R> {

	public L mapObject(R responce );
}
