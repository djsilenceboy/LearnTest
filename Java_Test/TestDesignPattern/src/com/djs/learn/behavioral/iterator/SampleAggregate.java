
package com.djs.learn.behavioral.iterator;

import java.util.ArrayList;

public class SampleAggregate
{
	ArrayList<String> data = new ArrayList<String>();
	int iteratorCount = 0;
	ArrayList<SampleIterator> iterators = new ArrayList<SampleIterator>();

	public void add(String item){
		violateIterators();

		data.add(item);
	}

	public void remove(String item){
		violateIterators();

		data.remove(item);
	}

	public String get(int index){
		return data.get(index);
	}

	public int size(){
		return data.size();
	}

	public SampleIterator createIterator(){
		SampleIterator iterator = new SampleIterator(iteratorCount++, this);
		iterators.add(iterator);

		return iterator;
	}

	private void violateIterators(){
		System.out.println("Aggregate: Violate iterators");

		for (SampleIterator iterator : iterators) {
			iterator.setViolated(true);
		}

		iterators.clear();
	}
}
