
public class FlashCard extends Card{
	
	FlashCard(String subject,String question,String answer)
	{
		super.setAnswer(answer);
		super.setSubject(subject);
		super.setQuestion(question);
	}
	
	void displayCard()
	{
		System.out.println("subject: "+super.getSubject());
		System.out.println("QUESTION:"+super.getQuestion());
		System.out.println("answer: "+super.getAnswer());
	}
}
