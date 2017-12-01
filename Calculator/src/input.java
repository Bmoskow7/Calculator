import java.util.Scanner;

public class input {

	public static void main(String[] args) {
		
		Scanner S = new Scanner(System.in);
		String expresion = S.nextLine();
		int size = expresion.length();
		char[] temp = new char[size];
		
		for(int i = 0; i<expresion.length(); i++)
		{
			// check ascii value is valid
			if((expresion.charAt(i) >= 45 && expresion.charAt(i)<=57) || (expresion.charAt(i) >= 40 && expresion.charAt(i)<=43) || expresion.charAt(i) == 32)
				{
					int spacecount = 0;
	
					
					// checks to see if two symbols appear in a row, if so then invalid
					if((i>0) && (!(expresion.charAt(i) >= 48 && expresion.charAt(i)<=57)) && (!(expresion.charAt(i-1) >= 48 && expresion.charAt(i-1)<=57)) )
					{
						System.out.println("Invaid input: two symbls next to each other");
						break;
					}
					
					//gets rid of spaces
					if(expresion.charAt(i) == 32)
					{
						spacecount++;
						continue;
					}
					
					temp[i - spacecount] = expresion.charAt(i);
					System.out.println("this is valid");
				}
			
			else
			{
				System.out.println("Invalid input: invalid charector");
				break;
			}
		}
		
		
		
		Token tokeup[] = new Token[size];
		
		
		System.out.println(inp);
	}
	
	public float evaluate(Token[] inp)
	{
		Stack nums = new Stack(inp.length);
		Stack ops = new Stack(inp.length);
		for(int i = 0; i < inp.length; i++)
		{
			if(inp[i].type == 5)
			{
				nums.push(inp[i]);
			}
			else
			{
				float temp1;
				float temp2;
				Token dostuff;
				switch(inp[i].type)
				{
				case 1:
					ops.push(inp[i]);
					break;
				case 2:
					while(ops.peek().type != 1)
					{
						dostuff = ops.popStack();
						temp1 = nums.popStack().number;
						temp2 = nums.popStack().number;
						float result = quickMaths(dostuff, temp2, temp1);
						nums.push(result);
					}
					break;
				case 3:
					ops.push(inp[i]);
				case 4:
					ops.push(inp[i]);
				case 5:
					//not right yet
					while(ops.peek().type != 3 || ops.peek().type != 4)
					{
						dostuff = ops.popStack();
						temp1 = nums.popStack().number;
						temp2 = nums.popStack().number;
						
						ops.push(inp[i]);
					}
					break;
				case 6:
					ops.push(inp[i]);
					break;
				}
			}
		}
	}
	
	public float quickMaths(Token dostuff, float temp1, float temp2)
	{
		float total;
		switch(dostuff.type)
		{
		case 3:
			total = temp1 * temp2;
		case 4:
			total = temp1 / temp2;
		case 5:
			total = temp1 + temp2;
		case 6:
			total = temp1 - temp2;
		}
		
		return total;
	}

}
