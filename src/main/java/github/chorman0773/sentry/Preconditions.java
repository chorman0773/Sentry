package github.chorman0773.sentry;

import java.util.Map;


/**
 * Class which Declares methods to enforce various argument preconditions.
 * If the Precondition holds, the Argument is returned, otherwise an exception is thrown
 * @author Connor
 */
public interface Preconditions {
	/**
	 * Asserts that the parameter is not null. 
	 * @param in the parameter to check
	 * @return the parameter
	 * @throws NullPointerException if the parameter is null.
	 */
	public static <T> T assertNonNull(T in) throws NullPointerException {
		if(in==null)
			throw new NullPointerException("Value passed to assertNonNull is null");
		return in;
	}
	
	/**
	 * Asserts that a given CharSequence is not empty (length()!=0).
	 * @param seq the parameter to check
	 * @return the parameter
	 * @throws IllegalArgumentException if the CharSequence is empty.
	 */
	public static <T extends CharSequence> T assertNonEmpty(T seq) throws IllegalArgumentException {
		if(seq.length()==0)
			throw new IllegalArgumentException("Passed CharSequence is empty");
		return seq;
	}
	
	/**
	 * Asserts that the Given Iterable is Not Empty
	 * @param iter the parameter to check
	 * @return the parameter
	 * @throws IllegalArgumentException if the iterable is empty.
	 */
	public static <E,T extends Iterable<? extends E>> T assertNonEmpty(T iter) throws IllegalArgumentException{
		if(!iter.iterator().hasNext())
			throw new IllegalArgumentException("Passed Iterable is empty");
		return iter;
	}
	
	/**
	 * Asserts that a Given Map is not empty
	 * @param map the parameter to check
	 * @return the parameter
	 * @throws IllegalArgumentException if the Map is empty
	 */
	public static <K,V,T extends Map<? extends K,? extends V>> T assertNonEmpty(T map) throws IllegalArgumentException {
		if(map.isEmpty())
			throw new IllegalArgumentException("Passed Map is empty");
		return map;
	}
	
	/**
	 * Asserts that a given parameter is a subclass of the given class
	 * @param o the parameter to check
	 * @param clazz the target class
	 * @return the parameter, casted to T
	 * @throws ClassCastException if o is not an instance of clazz
	 */
	public static <U,T extends U> T assertType(U o, Class<? extends T> clazz) throws ClassCastException {
		if(o==null)
			return null;
		if(!clazz.isInstance(o))
			throw new ClassCastException("Passed value is not of the type "+clazz.getTypeName());
		return clazz.cast(o);
	}


}
