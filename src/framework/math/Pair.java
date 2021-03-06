package framework.math;
/**
 *  little Class for a generic Tuple
 * @author Fin
 * 
 * @param <T> Type of first
 * @param <Z>
 */
public class Pair<T,Z> {
		private T FirstComponent ;
		private Z SecondComponent ;
		public Pair(T e, Z i){
			this.FirstComponent = e ; 
			this.SecondComponent = i ; 
		}
		public T getFirst(){
			return this.FirstComponent ; 
		}
		public Z getSecond(){
			return this.SecondComponent ; 
		}
		public void setFirst(T e){
			this.FirstComponent = e ; 
		}
		public void setSecond(Z e){
			this.SecondComponent = e ; 
		}
}
