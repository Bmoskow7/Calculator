
public class Token {
	public int type;
	public float number;
	public Token(float input)
	{
		type = 7;
		number = input;
	}
	
	public Token(char operator)
	{
		
		switch(operator)
		{
			case '(':
				type = 1;
			case ')':
				type = 2;
				break;
			case '*':
				type = 3;
				break;
			case '/':
				type = 4;
				break;
			case '+':
				type = 5;
				break;
			case '-':
				type = 6;
				break;
			
				
		}
		
		
	}
}
