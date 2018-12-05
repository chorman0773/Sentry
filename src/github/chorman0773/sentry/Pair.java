package github.chorman0773.sentry;

import java.io.Serializable;

public class Pair<T, U> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833731567532645785L;
	public T first;
	public U second;
	public Pair(T k, U v){
		first = k;
		second = v;
	}
	
	public static <T extends Comparable<T>,U extends Comparable<U>>
		int compare(Pair<T,U> p1,Pair<T,U> p2) {
			int ret;
			if((ret = p1.first.compareTo(p2.first))!=0)
				return ret;
			else
				return p1.second.compareTo(p2.second);
		}

}
