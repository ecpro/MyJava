import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FlashCardTest {

	public static void main(String[] args) throws IOException,CardNotFoundException {
		try{
			FlashCard [] fc=new FlashCard[5];
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
				fc[0]=new FlashCard("java","oops?","yes");
				fc[1]=new FlashCard("c++","pure opps","no");
				fc[2]=new FlashCard("c","oops","no");
				fc[3]=new FlashCard("python","oops","yes");
				fc[4]=new FlashCard("haskell","oops","don't know");
				
				FlashCardData fcd=new FlashCardData();
				fcd.init(fc);
				
				// searching for subjects
			System.out.println("Enter the subject");
			FlashCard fc2=fcd.searchCard(br.readLine());
			if(fc2!=null)
			{
				System.out.println("question: "+fc2.getQuestion()+"\n"+"answer: "+fc2.getAnswer());
			}
			else
			{
				System.out.println("bakchodi mat kar.. jyada padh liye hai be");
				
			}
		

		}
		catch(CardNotFoundException e)
		{
			System.out.println(e);
		}

		}
		
}
