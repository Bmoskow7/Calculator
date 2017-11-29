
public class Stack {
	int position = 0;
	Token store[];
	public Stack(int size){
		Token store[] = new Token[size];
	}
	
	public Token pop()
	{
		return store[--position];
	}
	
	public void push(Token input)
	{
		store[position++] = input;
	}
}
