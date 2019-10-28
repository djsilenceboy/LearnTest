
package com.djs.learn.javalang.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestStreamDishMenu
{
	static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, DishType.MEAT), new Dish("beef", false, 700, DishType.MEAT),
	                                       new Dish("chicken", false, 400, DishType.MEAT), new Dish("french fries", true, 530, DishType.OTHER),
	                                       new Dish("rice", true, 350, DishType.OTHER), new Dish("season fruit", true, 120, DishType.OTHER),
	                                       new Dish("pizza", true, 550, DishType.OTHER), new Dish("prawns", false, 300, DishType.FISH),
	                                       new Dish("salmon", false, 450, DishType.FISH));

	private List<Dish> filter1(){
		List<Dish> filteredDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
		System.out.println("vegetarian dishes = " + filteredDishes);
		return filteredDishes;
	}

	private List<Dish> filter2(){
		List<Dish> filteredDishes = menu.stream().filter(dish -> dish.getCalories() < 350).collect(Collectors.toList());
		System.out.println("Low calories dishes = " + filteredDishes);
		return filteredDishes;
	}

	private Integer summingInt(){
		int summingInt = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
		System.out.println("summingInt = " + summingInt);
		return summingInt;
	}

	private IntSummaryStatistics summarizingInt(){
		IntSummaryStatistics summarizingInt = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println("summarizingInt = " + summarizingInt);
		return summarizingInt;
	}

	private Map<DishType, List<Dish>> groupby1(){
		Map<DishType, List<Dish>> groupedDishes = menu.stream().collect(Collectors.groupingBy(Dish::getType));
		System.out.println("grouped by type dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<CaloricLevel, List<Dish>> groupby2(){
		Map<CaloricLevel, List<Dish>> groupedDishes = menu.stream().collect(Collectors.groupingBy(dish -> {
			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
			else return CaloricLevel.FAT;
		}));
		System.out.println("grouped by level dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<DishType, Map<CaloricLevel, List<Dish>>> groupby3(){
		Map<DishType, Map<CaloricLevel, List<Dish>>> groupedDishes = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
			else return CaloricLevel.FAT;
		})));
		System.out.println("grouped by type and level dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<DishType, Long> groupby4a(){
		Map<DishType, Long> groupedDishes = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
		System.out.println("grouped by type and counting dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<DishType, Integer> groupby4b(){
		Map<DishType, Integer> groupedDishes = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
		System.out.println("grouped by type and sum dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<DishType, Optional<Dish>> groupby5a(){
		Map<DishType, Optional<Dish>> groupedDishes = menu.stream()
		        .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
		System.out.println("grouped by type and max calory dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<DishType, Dish> groupby5b(){
		Map<DishType, Dish> groupedDishes = menu.stream().collect(Collectors
		        .groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println("grouped by type and max calory dishes = " + groupedDishes);
		return groupedDishes;
	}

	private Map<Boolean, List<Dish>> partitioningby1(){
		Map<Boolean, List<Dish>> partitionedDishes = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
		System.out.println("partitioning by vegetarian dishes = " + partitionedDishes);
		return partitionedDishes;
	}

	private Map<Boolean, Map<DishType, List<Dish>>> partitioningby2a(){
		Map<Boolean, Map<DishType, List<Dish>>> partitionedDishes = menu.stream()
		        .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
		System.out.println("partitioning by vegetarian and type dishes = " + partitionedDishes);
		return partitionedDishes;
	}

	private Map<Boolean, Dish> partitioningby2b(){
		Map<Boolean, Dish> partitionedDishes = menu.stream().collect(Collectors
		        .partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println("partitioning by vegetarian and type dishes = " + partitionedDishes);
		return partitionedDishes;
	}

	private List<Dish> collector1(){
		List<Dish> dishes = menu.stream().collect(ArrayList::new, List::add, List::addAll);
		System.out.println("steam to list dishes = " + dishes);
		return dishes;
	}

	private void testFilter(Supplier s){
		long startTime = System.currentTimeMillis();
		s.get();
		long stopTime = System.currentTimeMillis();
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	private void showMenu(List<Dish> menu){
		System.out.println("Menu = " + menu);
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		TestStreamDishMenu app = new TestStreamDishMenu();

		app.showMenu(menu);

		app.testFilter(app::filter1);
		app.testFilter(app::filter2);
		app.testFilter(app::summingInt);
		app.testFilter(app::summarizingInt);
		app.testFilter(app::groupby1);
		app.testFilter(app::groupby2);
		app.testFilter(app::groupby3);
		app.testFilter(app::groupby4a);
		app.testFilter(app::groupby4b);
		app.testFilter(app::groupby5a);
		app.testFilter(app::groupby5b);
		app.testFilter(app::partitioningby1);
		app.testFilter(app::partitioningby2a);
		app.testFilter(app::partitioningby2b);
		app.testFilter(app::collector1);
	}

	static class Dish
	{
		private final String name;
		private final boolean vegetarian;
		private final int calories;
		private final DishType type;

		public Dish(String name, boolean vegetarian, int calories, DishType type){
			this.name = name;
			this.vegetarian = vegetarian;
			this.calories = calories;
			this.type = type;
		}

		public String getName(){
			return name;
		}

		public boolean isVegetarian(){
			return vegetarian;
		}

		public int getCalories(){
			return calories;
		}

		public DishType getType(){
			return type;
		}

		@Override
		public String toString(){
			return "<" + name + ", " + vegetarian + ", " + calories + ", " + type + ">";
		}
	}

	static enum DishType
	{
		MEAT, FISH, OTHER
	}

	static enum CaloricLevel
	{
		DIET, NORMAL, FAT
	}
}
