package com.yarenty.list;


import java.util.ArrayList;

public class GenericsListTest {

	/* [1]
     * public class GenericsListTest {
	    public <T> void setArrayList(ArrayList<T> arrayList, T t){}
	    public <T> void setArrayList(ArrayList arrayList, Object obj){}
	    public <T extends HashMap<K,V>> void setArrayList(ArrayList<T>	arrayList){}
	    }
	 */

    public <T> void setArrayList(ArrayList<T> arrayList, T t) {
    }

    //public <T> void setArrayList(ArrayList arrayList, Object obj){}
//	    
//	public <T extends HashMap<K,V>> void setArrayList(ArrayList<HashMap<K,V>> array){
//		//return null;
//	}
	
	/*
	NO 1) You cant have three methods named setArrayList. Remove the last method named setArrayList.
	2) Type parameter T cannot extend HashMap<K,V>.
	NO 3) If some of the methods are generic, the class must be generic.
	4) Remove one of the first two methods and add the type parameters K and V to the third method.
	*/
}
