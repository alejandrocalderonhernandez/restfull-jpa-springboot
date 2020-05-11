package com.eagle.relationaldbaccessapi.util.interfaces;

import java.util.List;

public interface ISimpleCrud<T> {

	public T  insert(T dto);
	public T update(T dto, Long id);
	public T findById(Long id);
	public List<T> findAll();
	public boolean deleteById(Long id);
}
