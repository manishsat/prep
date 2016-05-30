package my.prep.practice;

public class Result<T,K,Z> {
	public T a;
	public K b;
	public Z c;
	public Result(T a, K b) {
		this.a = a;
		this.b = b;
	}
	
	public Result(T a, K b, Z c) {
		this.a = a;
		this.b = b;
		this.c= c;
	}
	
}
