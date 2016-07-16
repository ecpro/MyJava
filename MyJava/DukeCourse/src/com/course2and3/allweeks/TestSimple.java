package com.course2and3.allweeks;

public class TestSimple{
        public void print() {  	
             Simple item = new Simple(3, "blue");     	
             System.out.println(item);  
            // System.out.println(item.mystery(5, "ho"));
        }  
        

        
        
        public static void main(String[] args) {
    		
        	TestSimple ts = new TestSimple();
        	ts.print();
    	}
    }