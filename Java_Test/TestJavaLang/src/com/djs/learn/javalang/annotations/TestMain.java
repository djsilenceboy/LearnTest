
package com.djs.learn.javalang.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestMain
{
	public void test1(Class cls){
		System.out.println("[Class] Name = " + cls.getName());
		System.out.println("[Class][Annotations] Count = " + cls.getAnnotations().length);
		System.out.println("[Class][Annotations] WorkInProgress = " + cls.isAnnotationPresent(WorkInProgress.class));
		System.out.println("[Class][Annotations] Task = " + cls.isAnnotationPresent(Task.class));

		for (Annotation item : cls.getAnnotations()) {
			System.out.println("[Class] Annotation = " + item);
		}

		for (Method item : cls.getDeclaredMethods()) {
			System.out.println("[Class] Method = " + item);

			for (Annotation item2 : item.getAnnotations()) {
				System.out.println("[Class][Method] Annotation = " + item2);
			}
		}

		for (Field item : cls.getDeclaredFields()) {
			System.out.println("[Class] Field = " + item);

			for (Annotation item2 : item.getAnnotations()) {
				System.out.println("[Class][Field] Annotation = " + item2);
			}
		}
	}

	public static void main(String[] args){
		TestMain testMain = new TestMain();

		System.out.println("================================================================================");
		testMain.test1(Theater.class);
		System.out.println("================================================================================");
		testMain.test1(Theater2.class);
		System.out.println("================================================================================");
	}
}
