import java.util.*;


class DataPrint{
	
	public  void dataPrint(int n,int max,int cd, int dr)
	{
      int j=0,sum=0;
      
		
		while(sum<max)
		{

		if(cd<dr && cd>0)
		{
			j=cd;
		}
		else if(cd<=0)
		{
			j=0;
		}
		else
		{
			j=dr;
		}
		
		cd=cd-dr;
		sum=sum+dr;
		
			System.out.println("channel " + n + " transmits " + j + " bits ");
			
			
			
			try{
				Thread.sleep(1200);
				
			}
			catch(Exception ie){
				System.out.println("Exception occurs ");
			}
			
			
		}	
		
	}
	
	
}

class Channels extends Thread{
	int n,max,cd,dr;
	DataPrint dp;
	
	Channels(int n,int max,int cd, int dr,DataPrint dp)
	{
		this.n=n;
		this.max=max;
		this.cd=cd;
		this.dr=dr;
		this.dp=dp;
	}
	public void run(){
		
		dp.dataPrint(n, max, cd, dr);
		
		
	}
	
}
class STDMFrequency{
	
	public static void main(String args[])
	{
		Scanner sc= new Scanner(System.in);
		Channels c[] = new Channels[10];
		
		int max;
		int channelData[]=new int[10];
		DataPrint dp=new DataPrint();
		
		
		for(int i=0;i<10;i++)
		{
			System.out.println("Enter the " + i + " channel data ");
			
			channelData[i]=sc.nextInt();
		}
		
		System.out.println("Enter the channel data rate Mbps: ");
		int dataRate=sc.nextInt();
		dataRate=dataRate/10;
		max=Arrays.stream(channelData).max().getAsInt();
		
		
		
		
		for(int i=0;i<10;i++)
		{
			
			
			c[i]=new Channels(i,max,channelData[i],dataRate,dp);
			
		}
		
			int k=0;
		for(int p=10;p>0;p--)
		{
            
			c[k].setPriority(p);
			k++;
		
		}
		
		for(int i=0;i<10;i++)
		{
			
			c[i].start();
			
		}
		
		
	}
}
