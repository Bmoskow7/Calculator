
public class Token {
	int type;
	float number;
	public Token(float input)
	{
		type = 4;
		number = input;
	}
	
	public Token(String operator)
	{
		
		switch(operator)
		{
			case "(":
				type = 1;
				
			case "*":
				type = 2;
				break;
			case "/":
				type = 2;
				break;
			case "+":
				type = 3;
				break;
			case "-":
				type = 3;
				break;
			case ")":
				type = 4;
				break;
				
		}
		
		
	}
}
