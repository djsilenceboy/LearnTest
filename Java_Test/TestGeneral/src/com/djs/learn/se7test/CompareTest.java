
package com.djs.learn.se7test;

import java.util.ArrayList;
import java.util.Comparator;

public class CompareTest
{
	private static ArrayList<Hero> party = new ArrayList<>();

	public static void main(String[] args){
		/*
		CompareTest here = new CompareTest();
		here.buildParty();
		Collections.sort(party);
		for (Hero current : party)
			System.out.println("Name: " + current.name);
		*/
	}

	void buildParty(){
		Hero a = new Hero("Nathan", 10, 12, 17);
		party.add(a);
		Hero b = new Hero("Ken", 25, 26, 16);
		party.add(b);
		Hero c = new Hero("Jill", 10, 14, 8);
		party.add(c);
		Hero d = new Hero("Sarah", 18, 20, 15);
		party.add(d);
	}

	class Hero
	{
		public String name;
		public int str, dex, con;

		public Hero(String nIn, int sIn, int dIn, int cIn){
			name = nIn;
			str = sIn;
			dex = dIn;
			con = cIn;
		}

		public int compareTo(Object obj){
			if (obj instanceof Hero) {
				Hero vs = (Hero)obj;
				if (this.str == vs.str) return (vs.dex - this.dex);
				else return (vs.str - this.str);
			} else return -1;
		}
	}

	public static Comparator<Hero> HeroComparator = new Comparator<Hero>() {
		public int compare(Hero a, Hero b){
			return a.name.compareTo(b.name);
		}
	};

}
