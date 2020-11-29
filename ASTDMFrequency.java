package mcwc2;

import java.util.Arrays;
import java.util.Scanner;

class DataPrint{
	static float dataRate;
	static int noChannels;
	public void dataPrint(int n,int max,int cd, float dr,int noc)
	{
      int j=0,sum=0;
      long startTime = System.currentTimeMillis();
      dataRate=dr;
      noChannels=noc;
		
		while(sum<max)
		{

		if(cd<dataRate && cd>0)
		{
			j=cd;
		}
		else if(cd<=0)
		{
			j=0;
			dataRate=(dataRate*10)/(noChannels-1);
			Thread.currentThread().stop();
		}
		else
		{
			j=(int)dataRate;
		}
		
		cd=cd-(int)dataRate;
		sum=sum+(int)dataRate;
		
			System.out.println("channel " + n + " transmits " + j + " bits ");
			
			long endTime = System.currentTimeMillis();
			
			long duration = (endTime - startTime + 10);
			
			//System.out.println(duration);
			
			try{
				
			     Thread.sleep(1000);
				//Thread.currentThread().join(9000);
				
			}
			catch(InterruptedException ie){
				System.out.println("Exception occurs ");
			}
			
			
		}	
	}
}

class Channels implements Runnable{
	int n,max,cd,noc;
	float dr;
	DataPrint dp;
	
	Channels(int n,int max,int cd, float dr,DataPrint dp,int noc)
	{
		this.n=n;
		this.max=max;
		this.cd=cd;
		this.dr=dr;
		this.dp=dp;
		this.noc=noc;
	}
	public void run(){
		
		dp.dataPrint(n, max, cd, dr,noc);
		
		
	}
}
class ASTDMFrequency{
	
	public static void main(String args[])
	{
		Scanner sc= new Scanner(System.in);
		Channels c[] = new Channels[10];
		Thread t[]=new Thread[10];
		int max;
		int channelData[]=new int[10];
		DataPrint dp=new DataPrint();
		
		for(int i=0;i<10;i++)
		{
			System.out.println("Enter the " + i + " channel data ");
			
			channelData[i]=sc.nextInt();
		}
		
		System.out.println("Enter the channel data rate Mbps: ");
		float dataRate=sc.nextFloat();
		dataRate=dataRate/10;
		max=Arrays.stream(channelData).max().getAsInt();
		
		
		
		
		for(int i=0;i<10;i++)
		{
			
			
			c[i]=new Channels(i,max,channelData[i],dataRate,dp,10);
			
			
			t[i]= new Thread(c[i]);
			
		}
		
			int k=0;
		for(int p=10;p>0;p--)
		{
            
			t[k].setPriority(p);
			k++;
		
		}
		
		for(int i=0;i<10;i++)
		{
			
			
			t[i].start();
			
		}
		
	}
}
