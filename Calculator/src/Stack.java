
public class Stack {
	public int position = 0;
	Token store[];
	Token temp[];
	public Stack(int size){
		temp = new Token[size];
	}
	
	public Token popStack()
	{
		return temp[--position];
	}
	
	public void push(Token input)
	{
		temp[position++] = input;
	}
	
	public Token peek()
	{
		return temp[position-1];
	}
}
