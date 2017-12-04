import java.util.Scanner;


public class input3 
{

		public static void main(String[] args) {
			boolean isbroke = false;
			String expression = "";
			
			while(!(expression.equals("x")))
			{
				isbroke = false;
				int periodcount = 0;
				isbroke = false;
				System.out.println("Please input an expression");
				Scanner S = new Scanner(System.in);
				String s = S.nextLine();
				expression = s.replaceAll("\\s+","");
				//System.out.println(expression);
				periodcount = 0;
				for(int i = 0; i<expression.length(); i++)
				{
					/*if(!(mystring.matches("^[0-9]*\\.?[0-9]*$"))) 
					{
						
					}*/
					
					if(expression.charAt(i)=='.')
					{
						periodcount++;
					}
					
					
					// check ascii value is valid
					if((expression.charAt(i) >= 45 && expression.charAt(i)<=57) || (expression.charAt(i) >= 40 && expression.charAt(i)<=43) || expression.charAt(i) == 32)
						{
							
							// checks to see if two symbols appear in a row, if so then invalid
							if((i>0) && (!(expression.charAt(i) >= 48 && expression.charAt(i)<=57)) && (!(expression.charAt(i-1) >= 48 && expression.charAt(i-1)<=57)) )
							{
								System.out.println("Invalid input: two symbols next to each other");
								//isbroke = true;
								break;
							
							}
							
						}
					else
					{
						System.out.println("Invalid input: invalid charector");
						isbroke = true;
						break;
						
					}
				}
			if(isbroke)
			{
				continue;
			}
			int size = expression.length();
			int start = 0;
			
			Token[] tempo = new Token[size-periodcount];
			int count = 0;
			for(int i = 0; i < size; i++)
			{
					if(Character.isDigit(expression.charAt(i))||expression.charAt(i)=='.')
					{
						start = i;
						while (i<size-1 && (expression.charAt(i+1)=='.' || (expression.charAt(i+1) >= '0' && expression.charAt(i+1) <= '9')))
						{
							i++;
						}
						tempo[count] = new Token(Float.parseFloat(expression.substring(start, i+1)));
						count++;
					}
					else
					{
						tempo[count] = new Token(expression.charAt(i));
						count++;
					}
				}
			Token[] tokeUp = new Token[count];
			for(int j=0;j<count;j++)
			{
				tokeUp[j]=tempo[j];
			}
			
			
			
			System.out.println(evaluate(tokeUp));
			}
			System.out.println("Thank you for using our program");
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
						//if open bracket put on stack
						ops.push(inp[i]);
						break;
					case 2:
						//close bracket you go untill you reach open bracket, see coments for
						//when the whole thing is evaluated for details inside the loop
						while((ops.peek().type) != 1 && (ops.position != 0))
						{
							if((ops.position != 0) && (ops.peek().type==3 || ops.peek().type==4))
							{
								Token temp[] = new Token[inp.length];
								int count = 0;
								
								//at this point there will only be multiplication and division followed by 
								//addition and subtraction so evaluate the two seperatley
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
							
							
							if((ops.position != 0) && (ops.peek().type==5 || ops.peek().type==6))
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
							
						}
						ops.popStack();
						break;
					case 3:
					case 4:
						//if multiplication or division just put it on the stack
						ops.push(inp[i]);
						break;
					case 5:
					case 6:
						//if adition or subtraction check to see if there was something of higher precedence
						Token temp[] = new Token[inp.length];
						int count = 0;
						
						//if there is then evaluate it all
						while((ops.position != 0) && (ops.peek().type == 3 || ops.peek().type == 4))
						{		
							if(count == 0)
							{
								temp[count++] = nums.popStack(); 
							}
							temp[count++] = ops.popStack();
							temp[count++] = nums.popStack();
						}
						
						
						//this next bit makes it so its done from left to right, not right to left
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
					    
					    //put the + or - on the stack
						ops.push(inp[i]);
						break;
					}
				}
			}
			
			//all tokens from the array in the stack so evaluate what is left
			while(ops.position != 0)
			{
				if((ops.position != 0) && (ops.peek().type==3 || ops.peek().type==4))
				{
					Token temp[] = new Token[inp.length];
					int count = 0;
					
					//at this point there will only be multiplication and division followed by 
					//addition and subtraction so evaluate the two seperatley
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
				
				
				if((ops.position != 0) && (ops.peek().type==5 || ops.peek().type==6))
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
				
				//System.out.println(ops.position);
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
		
		

}