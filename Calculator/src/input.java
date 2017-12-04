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
		
		int start = 0;
		Token[] tokeUp = new Token[size];
		int count = 0;
		for(int i = 0; i < size; i++)
		{
			if(Character.isDigit(expresion.charAt(i)))
			{
				start = i;
				while (i<size-1 && (expresion.charAt(i+1)=='.' || (expresion.charAt(i+1) >= '0' && expresion.charAt(i+1) <= '9')))
				{
					i++;
				}
				tokeUp[count] = new Token(Float.parseFloat(expresion.substring(start, i+1)));
				count++;
			}
			else
			{
				tokeUp[count] = new Token(expresion.charAt(i));
				count++;
			}
		}
		
		
		Token[] inp = new Token[7];
		inp[0] = new Token((float) 4.0);
		inp[1] = new Token('-');
		inp[2] = new Token((float) 5.0);
		inp[3] = new Token('*');
		inp[4] = new Token((float) 6.0);
		inp[5] = new Token('-');
		inp[6] = new Token((float) 2.0);
	
		
		
		System.out.println(evaluate(inp));
	}
	
	public static float evaluate(Token[] inp)
	{
		// create a number stack and an operator stack
		Stack nums = new Stack(inp.length + 1);
		Stack ops = new Stack(inp.length + 1);
		
		
		//loop through the given stack
		for(int i = 0; i < inp.length; i++)
		{
			if(inp[i].type == 7)
			{
				//if a number put on stack
				nums.push(inp[i]);
			}
			else
			{
				//varibles used for passing to quickMaths
				float temp1;
				float temp2;
				Token dostuff;
				
				//check witch type of operator
				switch(inp[i].type)
				{
				case 1:
					ops.push(inp[i]);
					break;
				case 2:
					while((ops.peek().type) != 1 && (ops.position != 0))
					{
						//pops stack until a open bracket is reached
						dostuff = ops.popStack();
						temp1 = nums.popStack().number;
						temp2 = nums.popStack().number;
						Token result = quickMaths(dostuff, temp2, temp1);
						
						nums.push(result);
					}
					ops.popStack();
					break;
				case 3:
					ops.push(inp[i]);
					break;
				case 4:
					ops.push(inp[i]);
					break;
				case 5:
				case 6:
					Token temp[] = new Token[inp.length];
					int count = 0;
					
					while((ops.position != 0) && (ops.peek().type == 3 || ops.peek().type == 4))
					{		
						if(count == 0)
						{
							temp[count++] = nums.popStack(); 
						}
						temp[count++] = ops.popStack();
						temp[count++] = nums.popStack();
					}
					
					Token temps[] = new Token[count];
					for(int j = 0; j<count; j++)
					{
						temps[j] = temp[j];
					}
					    
					Token tmp;
				    for (int j = 0; j < temps.length / 2; j++) 
				    {
				        tmp = temps[j];
				        temps[j] = temps[temps.length - 1 - j];
				        temps[temps.length - 1 - j] = tmp;
				    }
					
				    for (int j = 1; j < temps.length; j = j +2) 
				    {
				    	temps[j+1] = quickMaths(temps[j], temps[j-1].number, temps[j+1].number);
				    	if(j == temps.length-2)
				    	{
				    		nums.push(temps[j+1]);
				    	}
				    }
				    
					ops.push(inp[i]);
					break;
					
			
				}
			}
		}
		
		
		
		while(ops.position != 0)
		{
			if(ops.peek().type==3 || ops.peek().type==4)
			{
				Token temp[] = new Token[inp.length];
				int count = 0;
				
				while((ops.position != 0) && (ops.peek().type == 3 || ops.peek().type == 4))
				{		
					if(count == 0)
					{
						temp[count++] = nums.popStack(); 
					}
					temp[count++] = ops.popStack();
					temp[count++] = nums.popStack();
				}
				
				Token temps[] = new Token[count];
				for(int j = 0; j<count; j++)
				{
					temps[j] = temp[j];
				}
				    
				Token tmp;
			    for (int j = 0; j < temps.length / 2; j++) 
			    {
			        tmp = temps[j];
			        temps[j] = temps[temps.length - 1 - j];
			        temps[temps.length - 1 - j] = tmp;
			    }
				
			    for (int j = 1; j < temps.length; j = j +2) 
			    {
			    	temps[j+1] = quickMaths(temps[j], temps[j-1].number, temps[j+1].number);
			    	if(j == temps.length-2)
			    	{
			    		nums.push(temps[j+1]);
			    	}
			    }
			    
			}
			
			
			if(ops.peek().type==5 || ops.peek().type==6)
			{
				Token temp[] = new Token[inp.length];
				int count = 0;
				
				while((ops.position != 0) && (ops.peek().type == 5 || ops.peek().type == 6))
				{		
					if(count == 0)
					{
						temp[count++] = nums.popStack(); 
					}
					temp[count++] = ops.popStack();
					temp[count++] = nums.popStack();
				}
				
				Token temps[] = new Token[count];
				for(int j = 0; j<count; j++)
				{
					temps[j] = temp[j];
				}
				    
				Token tmp;
			    for (int j = 0; j < temps.length / 2; j++) 
			    {
			        tmp = temps[j];
			        temps[j] = temps[temps.length - 1 - j];
			        temps[temps.length - 1 - j] = tmp;
			    }
				
			    for (int j = 1; j < temps.length; j = j +2) 
			    {
			    	temps[j+1] = quickMaths(temps[j], temps[j-1].number, temps[j+1].number);
			    	if(j == temps.length-2)
			    	{
			    		nums.push(temps[j+1]);
			    	}
			    }
			    
			
			}
			
			System.out.println(ops.position);
		}
		
		return nums.popStack().number;
	}
	
	public static Token quickMaths(Token dostuff, float temp1, float temp2)
	{
		float total = 0;
		switch(dostuff.type)
		{
		case 3:
			total = temp1 * temp2;
			break;
		case 4:
			total = temp1 / temp2;
			break;
		case 5:
			total = temp1 + temp2;
			break;
		case 6:
			total = temp1 - temp2;
			break;
		}
		
		Token temp = new Token (total); 
		return temp;
	}
	
	public static Token[] postfix()
	{
		
	}

}
