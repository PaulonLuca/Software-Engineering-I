import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Mappa 
{
	private int m;	//coniene la larghezza della mappa
	private int n;	//contiene l'altezza della mappa
	private Cella[][] mappa; //contiene le celle della mappa
	private List<Pezzo> pezzi; //contiene solo i pezzi le cui coordinate sono valide
	private int countOrchi; //conteggia il totale degli orchi sulla mappa
	private int countElfi; //conteggia il totale degli elfi sulla mappa
	private int countNani;	//conteggia il totale dei nani sulla mappa
	private int countNotInOrchi; //conteggia il totale degli orchi FUORI mappa
	private int countNotInElfi; //conteggia il totale degli elfi FUORI mappa
	private int countNotInNani;	//conteggia il totale dei nani FUORI mappa
	
	public Mappa(int M, int N)
	{
		n=N;
		m=M;
		mappa=new Cella[m][n];
		pezzi=new ArrayList<Pezzo>();
		inizializzaCelle();	
	}
	
	//si creano le celle della matrice
	private void inizializzaCelle()
	{
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				Cella c=new Cella(i,j);
				mappa[i][j]=c;
			}		
		}
	}
	
	//lettura del file di input e posizionamento dei pezzi sulla mappa
	void init() throws MaximumCellSizeException, FileNotFoundException, IOException, NumberFormatException
	{
		BufferedReader reader= new BufferedReader(new FileReader("pezzi.txt"));
		String lineX="";
		String lineY="";
		String lineTipo="";
		boolean finito=false;
		boolean emptyFile=true; //vero se il file è vuoto e non ci sono dati
			
		while(!finito) //lettura fino a fine file
		{
			lineX=reader.readLine(); 	//lettura della coordinata x
			lineY=reader.readLine();	//lettura della coordinata y
			lineTipo=reader.readLine(); //lettua del tipo
			
			if(lineX==null && lineY==null && lineTipo==null)//se file finito
			{
				finito=true;
				if(emptyFile)//se il file è vuoto si ha un errore nel formato del file
					throw new InvalidFileFormatException();
			}
				
			else
			{
				emptyFile=false; //il file non è vuoto
				Pezzo p=null;
				int x=Integer.parseInt(lineX);
				int y=Integer.parseInt(lineY);
			
				//se il tipo è uno di quelli validi controllo le coordinate
				if(lineTipo.equals("Orco") || lineTipo.equals("Nano") || lineTipo.equals("Elfo"))
				{
					if(x<m && y<n)//se le coordinate sono tra quelle ammesse nella mappa
					{
						switch(lineTipo)//creazione di un pezzo del tipo corrispondente
						{
							case "Orco": p=new Orco(x,y); countOrchi++; break;
							case "Nano": Nano n=new Nano(x,y);
										 if(mappa[x][y].getLuogo()==TipoCella.montagna)//se è un nano in montagna 											 
											n.modificaAttDifMontagna();				   //modifica dei valori di attacco e difesa									 
											p=n;
											countNani++;
											break;
							case "Elfo": Elfo e=new Elfo(x,y);
								 		 if(mappa[x][y].getLuogo()==TipoCella.bosco)//se è un elfo nel bosco
								 			e.modificaAttDifBosco();				//modifica dei valori di attacco e difesa
								 			p=e;
								 			countElfi++;
								 			break;
						}
						//si aggiunge il pezzo alla lista ed lo si posiziona sulla cella
						pezzi.add(p);
						mappa[x][y].addPezzo(p);						
						}
						else
						{
							//tipo valido ma coordinate fuori mappa, si conteggia il tutto diviso per tipologia
							switch(lineTipo)
							{
								case "Orco": countNotInOrchi++;break;
								case "Nano": countNotInNani++;break;
								case "Elfo": countNotInElfi++;break;
							}
						}							
					}
					else
					{
						//il tipo è errato si segnala un errore nel file
						if(lineTipo.equals(""))
							throw new InvalidFileFormatException();
						throw new InvalidTypeException();
					}
				}			
			}//fine while
		reader.close();
	}
	
	//metodo usato per testare se la mappa viene caricata in modo corretto
	/*public void printMap()
	{
		System.out.println("Risultato");
		Iterator<Pezzo> iter=pezzi.iterator();
		while(iter.hasNext())
		{
			Pezzo pz=iter.next();
			if(pz instanceof Orco)
				System.out.println("Orco");
			else
				if(pz instanceof Elfo)
					System.out.println("Elfo");
				else
					if(pz instanceof Nano)
						System.out.println("Nano");
			System.out.println("X: "+pz.getX()+" Y: "+pz.getY()+" "+"Attacco:" + pz.getAttacco()+" "+"Difesa: "+ pz.getDifesa());
			System.out.println("Tipo cella: "+mappa[pz.getX()][pz.getY()].getLuogo());		
		}
		System.out.println("Fine!!");
	}*/
	
	//stampa il totale dei pezzi presenti sulla mapppa divisi per tipologia
	public void printNumeroPezzi()
	{
		System.out.println("\nSulla mappa sono presenti complessivamente: ");
		System.out.println("Elfi:"+countElfi+"  Nani:"+countNani+ "  Orchi:"+countOrchi+"\n");
		System.out.println("I pezzi di tipo valido ma fuori mappa sono in totale "+(countNotInOrchi+countNotInElfi+countNotInNani)+" così suddivisi:");
		System.out.println("Numero di Orchi fuori mappa:"+ countNotInOrchi);
		System.out.println("Numero di Elfi fuori mappa:"+ countNotInElfi);
		System.out.println("Numero di Nani fuori mappa:"+ countNotInNani);
	}
	
	//indivisua la cella o le celle con il maggior numero di pezzi dello stesso tipo
	public void maggioreNumeroPezziStessoTipo()
	{
		int max=0;
		List<Cella> maxType=new ArrayList<Cella>(); //lista contenente la lista di celle per le quali è verificata
													//la proprietà richiesta
		for(int i=0;i<m;i++)//iterazione sulle righe
		{
			for(int j=0;j<n;j++)//iterazione sulle colonne
			{
				if(!mappa[i][j].isEmpty())//se ci sono dei pezzi sulla cella si controlla altrimenti si prosegue
				{
					int maxValueCella= mappa[i][j].findMaxType(); //tra i tipi validi sulla cella si ritorna 
					if(maxValueCella>max)						  //quello maggiormente presente
					{
						maxType.clear();			//si svuota la lista se si trova un nuovo massimo
						max=maxValueCella;			//aggiornamento del valore massimo
						maxType.add(mappa[i][j]);	//aggiunta alla lista
					}
					else
						if(maxValueCella==max) //in caso di celle con lo stesso massimo numero di pezzi si conteggiano
							maxType.add(mappa[i][j]);
				}
			}
		}
		System.out.println("Le celle con il maggior numero di pezzi dello stesso tipo sono: ");
		printList(maxType);
		System.out.println("I pezzi della stessa tipologia su ogni cella sono: "+max);
	}
	
	//Stampa una lista di celle mettendo in evidenza coordinata X e coordinata y
	private void printList(List<Cella> celle)
	{
		Iterator<Cella> iter=celle.iterator();
		while(iter.hasNext())
		{
			Cella cl=iter.next();
			System.out.println("Cella-> X:"+cl.getX()+"  Y:"+cl.getY());			
		}		
	}
	
	public void maggioreDifesa(Temporalita temporalita)
	{
		Iterator<Pezzo> iter=pezzi.iterator();
		Pezzo max=pezzi.get(0);//inizializzo il massimo con il primo pezzo della lista ma senza progredire con l'iteratore
		if(max instanceof Orco)//nel caso in cui sia un orco riclacolo i valori di attacco e difesa
			max=gestisciOrco(max,temporalita);
		
		List<Cella> celleStessaDif=new ArrayList<Cella>(); //lista che contiene le celle dove sono posizionati
		while(iter.hasNext())							   //pezzi con la stessa difesa
		{
			Pezzo cur=iter.next();
			//se il pezzo corrente è un orco bisogna modificare i suoi valori di attacco e difesa
			if(cur instanceof Orco)
				cur=gestisciOrco(cur,temporalita);
			
			if(cur.getDifesa()>max.getDifesa())
			{
				celleStessaDif.clear(); //se si trova un pezzo con difesa più elavata si cancella la lista delle celle
				max=cur;
				celleStessaDif.add(mappa[cur.getX()][cur.getY()]); //si aggiunge la cella nella lista delle celle
			}
			else //in cado di parità si verifica se una cella è già stata inserita precedentemente
				if(cur.getDifesa()==max.getDifesa() && !giaInserita(celleStessaDif,mappa[cur.getX()][cur.getY()]))//in caso di parità
					celleStessaDif.add(mappa[cur.getX()][cur.getY()]); //si aggiunge la cella nella lista delle celle				
		}
		System.out.println("Le celle con la maggiore difesa di "+temporalita+" sono:");
		printList(celleStessaDif);
	}
	
	public void maggioreAttacco(Temporalita temporalita)
	{
		Iterator<Pezzo> iter=pezzi.iterator();
		Pezzo max=pezzi.get(0);//inizializzo il massimo con il primo pezzo della lista ma senza progredire con l'iteratore		
		if(max instanceof Orco)
			max=gestisciOrco(max,temporalita);
		
		List<Cella> celleStessoAtt=new ArrayList<Cella>();
		while(iter.hasNext())
		{
			Pezzo cur=iter.next();
			//se il pezzo corrente è un orco bisogna modificare i suoi valori di attacco e difesa
			if(cur instanceof Orco)
				cur=gestisciOrco(cur,temporalita);
			
			if(cur.getAttacco()>max.getAttacco())
			{
				celleStessoAtt.clear();
				max=cur;
				celleStessoAtt.add(mappa[cur.getX()][cur.getY()]); //si aggiunge la cella nella lista delle celle
			}
			else //in cado di parità si verifica se una cella è già stata inserita precedentemente
				if(cur.getAttacco()==max.getAttacco() && !giaInserita(celleStessoAtt,mappa[cur.getX()][cur.getY()]))//in caso di parità
					celleStessoAtt.add(mappa[cur.getX()][cur.getY()]); //si aggiunge la cella nella lista delle celle				
		}
		System.out.println("Le celle con il maggiore attacco di "+temporalita+" sono:");
		printList(celleStessoAtt);
	}
	
	//metodo per gestire i valori di attacco e difesa del tipo orco quuando la temporalità è giorno o notte
	//ritorna un nuovo oggetto Orco i cui valori di attacco e difesa sono modificati ad hoc per avitare
	//modifiche permanenti dei dati iniziali 
	private Pezzo gestisciOrco(Pezzo cur, Temporalita temporalita)
	{
		Orco o=new Orco(cur.getX(),cur.getY());
		if(temporalita==Temporalita.giorno)
			o.decrementaAttDifGiorno();
		else //è sicuramente notte
			o.incrementaAttDifNotte();
		return o;
	}
	
	//verifica se tra le celle nella lista si è già inserita una cella con le medesime coordinate
	//in caso affermarivo allora non dovrà essere nuovamente inserita
	private boolean giaInserita(List<Cella> celle, Cella c)
	{
		Iterator<Cella> iter=celle.iterator();
		while(iter.hasNext())
		{
			Cella cl=iter.next();
			if(c.equals(cl))	
				return true;
		}
		return false;
	}
}
