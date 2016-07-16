
public class FlashCardData implements CardSearchable{
	FlashCard []  fc=new FlashCard[5];
	
	void init(FlashCard fc[])
	{
		for(int i=0;i<5;i++)
		{
			this.fc[i]=new FlashCard(fc[i].getSubject(),fc[i].getQuestion(),fc[i].getAnswer());
		}
	}
	
	@SuppressWarnings("unused")
	public FlashCard searchCard(String str) throws CardNotFoundException
	{
		for(int i=0;i<5;i++)
		{
			if(fc[i].getSubject().compareTo(str)==0)
			{
				return fc[i];
			}
			else throw new CardNotFoundException();
	}
		return null;
	}
	
}
