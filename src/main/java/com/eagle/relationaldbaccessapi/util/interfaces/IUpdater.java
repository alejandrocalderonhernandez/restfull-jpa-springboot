package com.eagle.relationaldbaccessapi.util.interfaces;

@FunctionalInterface
public interface IUpdater<L, R> {
	public void update(L newModel, R oldModel);
}
