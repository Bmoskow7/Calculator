import java.util.Scanner;

public class input {

	public static void main(String[] args) {
		
		Scanner S = new Scanner(System.in);
		String s = S.nextLine();
		String expresion = s.replaceAll("\\s+","");
		System.out.println(expresion);
		int periodcount = 0;
		for(int i = 0; i<expresion.length(); i++)
		{
			if(!(mystring.matches("^[0-9]*\\.?[0-9]*$"))) 
			{
				
			}
			
			if(expresion.charAt(i)=='.')
			{
				periodcount++;
			}
			
			
			// check ascii value is valid
			if((expresion.charAt(i) >= 45 && expresion.charAt(i)<=57) || (expresion.charAt(i) >= 40 && expresion.charAt(i)<=43) || expresion.charAt(i) == 32)
				{
					
					// checks to see if two symbols appear in a row, if so then invalid
					if((i>0) && (!(expresion.charAt(i) >= 48 && expresion.charAt(i)<=57)) && (!(expresion.charAt(i-1) >= 48 && expresion.charAt(i-1)<=57)) )
					{
						System.out.println("Invalid input: two symbls next to each other");
						break;
					}
					
					//gets rid of spaces
					else if(expresion.charAt(i) == 32)
					{
						//continue;
					}
					
					System.out.println("this is valid");
				}
			else
			{
				System.out.println("Invalid input: invalid charector");
				break;
			}
		}
		int size = expresion.length() - periodcount;
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
		System.out.println("period:" + periodcount);
		for(int i=0;i<tokeUp.length-2;i++)
		{
			System.out.print(tokeUp[i].type);
		}
}
}