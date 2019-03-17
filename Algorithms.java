
import java.util.*;
public class Sample1 {

public static void main(String[] args) {
	int i,j;

	int cost[][]=new int[10][10];

	Scanner in=new Scanner(System.in);

	while(true){
		
		System.out.println("Which Algorithm would you like to test?");
		System.out.println("1.Dijkstras Algorithm \n2.Prims Algorithm \n3.FLoyds Algorithm \n4. Exit");
		int choice=in.nextInt();
		if(choice==4)
			System.exit(0);
		System.out.println("\nEnter no. of nodes");
		int n=in.nextInt();
		System.out.println("\nEnter a postive cost matrix for an undirected graph:");
		for(i=1;i<=n;i++)
		{
			for(j=1;j<=n;j++)
				{
					cost[i][j]=in.nextInt();
				}
		}
		System.out.println("\nThe entered cost matrix is:\n");
		for(i=1;i<=n;i++)
		{
				for(j=1;j<=n;j++)
				{
					System.out.print(cost[i][j]+"\t");
				}
				System.out.println();
		}

		switch(choice)
		{
		case 1:
				int dist[]=new int[10];
				int visited[]=new int[10];
				int path[]=new int[10];
				System.out.println("\nDIJKSTRAS ALGORITHM");
				System.out.println("Enter source:");
				int sv=in.nextInt();
				Dij(cost,dist,sv,n,path,visited);
				printPath(sv,n,dist,path,visited);
				System.out.println("-----------");
			
				break;
			
			case 2: 
				System.out.println("\nPRIMS ALGORITHM");
				int mincost=0;
				System.out.println("MST edges and cost are:");
				mincost=Prims(cost,n,mincost);
				System.out.println("MST cost is:"+mincost);
				System.out.println("----------");
				break;


			case 3: 
					System.out.println("\nFLOYDS ALGORITHM");
					Floyd(cost,n);
					System.out.println("All pair shortest path matrix");
					for(i=1;i<=n;i++)
					{
						for(j=1;j<=n;j++)
						{
							System.out.print(cost[i][j]+"\t");
							
						}
						System.out.println();
					}
					break;
				
				default: System.out.println("Enter a Valid Option");

		}

	}

}





static void Dij(int cost[][],int dist[],int sv,int n,int path[],int visited[])
{
	int count=2,min,v=0;
	for(int i=1;i<=n;i++)
	{
		visited[i]=0;
		dist[i]=cost[sv][i];
		if(cost[sv][i]==999)
			path[i]=0;
		else
			path[i]=sv;
					
	}
	visited[sv]=1;
	while(count<=n)
	{
		min=999;
		for(int w=1;w<=n;w++)
				
			if((dist[w]<min)&&(visited[w]==0))
			{
				min=dist[w];
				v=w;
			}
		visited[v]=1;
		count++;
		for(int w=1;w<=n;w++)
		{
			if((dist[w]>dist[v]+cost[v][w]))
			{
				dist[w]=dist[v]+cost[v][w];
				path[w]=v;
			}
		}
	}
}
static void printPath(int sv,int n,int dist[],int path[],int visited[])
{
	for(int w=1;w<=n;w++)
	{
		if(visited[w]==1&&w!=sv)
		{
			System.out.print("Shortest distance between ");
			System.out.println(sv+"->"+w+" is "+dist[w]);
			int t=path[w];
			System.out.print("The path is");
			System.out.print(" "+w);
			while(t!=sv)
			{
				System.out.print("<->"+t);
				t=path[t];
			}
			System.out.println("<->"+sv);
			}
		}
}

static int Prims(int cost[][],int n,int mincost)
{
	int nearV[]=new int[10],t[][]=new int[10][3],u=0,i,j,k;
	for(i=2;i<=n;i++)
		nearV[i]=1;
	nearV[1]=0;
	for(i=1;i<n;i++)
	{
		int min=999;
		for(j=1;j<=n;j++)
		{
			if(nearV[j]!=0&&cost[j][nearV[j]]<min)
			{
				min=cost[j][nearV[j]];
				u=j;
							
			}
		}
		t[i][1]=u;
		t[i][2]=nearV[u];
		mincost+=min;
		nearV[u]=0;
		for(k=1;k<=n;k++)
		{
			if(nearV[k]!=0&&cost[k][nearV[k]]>cost[k][u])
				nearV[k]=u;
		}
		System.out.print(i+")Min edge is ("+t[i][1]);
		System.out.println(","+t[i][2]+") and cost is "+min);
	}
	return mincost;
}


static void Floyd(int cost[][],int n)
{
	int i,j,k;
	for(k=1;k<=n;k++)
	{
		for(i=1;i<=n;i++)
		{
			for(j=1;j<=n;j++)
			{
				cost[i][j]=min(cost[i][j],cost[i][k]+cost[k][j]);
			}
		}
	}
}
static int min(int a,int b)
{
	if(a>b)
		return b;
	else
		return a;
}

}


