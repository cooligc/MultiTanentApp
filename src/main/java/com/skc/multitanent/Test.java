package com.skc.multitanent;

import java.util.ArrayList;
import java.util.List;

public class Test implements Pancake{
	
	@Override
	public List<String> doStuff(List<String> s) {
		s.add("9");
		return s;
	}
	
	public static void main(String[] args) {
		List<String> aaa = new ArrayList<>();
		aaa.add("1");
		List<String> abc = new Test().doStuff(aaa);
		abc.add("88");
		System.out.println(abc);
		
	}
}

interface Pancake{
	List<String> doStuff(List<String> s);
}

