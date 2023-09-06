//Luca Paulon Matricola: 1187028

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principale 
{
	public static void main(String[] args) 
	{
		try
		{
			Scanner console=new Scanner(System.in);
			int M=0;
			int N=0;
			//gestione delle lettura da standard input
			do
			{
				System.out.println("Inserire le dimensioni della mappa:");
				System.out.print("Numero di righe (M): ");
				M=console.nextInt();
				System.out.print("Numero di colonne (N): ");
				N=console.nextInt();
				
				if(verificaDimensioneTabella(M,N))
				{
					System.out.flush(); //pulizia schermo
					System.out.println("\nErrore nell'inserimento delle dimensioni della mappa\n");
					System.out.println("La dimensione massima consentita è 500x500\n");
				}									

			}while(verificaDimensioneTabella(M,N));
			console.close();
			
			Mappa map=new Mappa(M,N);	//creazione della mappa MxN
			map.init();					//inizializzazione della mappa con posizionamento dei pezzi
			//map.printMap();				//metodo di test
			map.printNumeroPezzi();		//stampa del numero di pezzi presenti divisi per tipologia
										//e stampa del numero di pezzi con ccordinate fuori mappa
			System.out.println();
			map.maggioreNumeroPezziStessoTipo();	//stampa celle in cui si ha il maggior numero di pezzi
													//dello stesso tipo
			System.out.println();
			map.maggioreDifesa(Temporalita.giorno);	//stampa delle celle con maggiore difesa di giorno
			
			System.out.println();
			map.maggioreDifesa(Temporalita.notte); //stampa delle celle con maggiore difesa di notte
			
			System.out.println();
			map.maggioreAttacco(Temporalita.giorno); //stampa delle celle con maggiore attacco di giorno
			
			System.out.println();
			map.maggioreAttacco(Temporalita.notte); //stampa delle celle con maggiore attacco di notte
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Il file non è presente. Inserire un file di nome: pezzi.txt");
			System.exit(1);
		}
		catch(IOException fnfe)
		{	
			System.out.println("Errore di input/output");
			System.exit(1);
		}
		catch(MaximumCellSizeException mcse)
		{
			System.out.println("Attenzione si è superata la capienza massima di 5 pezzi in una singola cella!!!");
			System.exit(1);
		}
		catch(NumberFormatException | InvalidFileFormatException ffe)//file format exception
		{	
			System.out.println("Errore di formato nel file");
			System.exit(1);
		}
		catch(InvalidTypeException ite)
		{	
			System.out.println("Attenzione nel file è presente un pezzo il cui tipo non è tra quelli predefiniti");
			System.exit(1);
		}
		catch(InputMismatchException ime)
		{	
			System.out.println("Formato dell'input errato");
			System.exit(1);
		}
	}
	
	//verifica la validità dei valori di m ed n, permette di proseguire solo se m ed n sono corretti
	public static boolean verificaDimensioneTabella(int m, int n)
	{
		return (m<=0 || n<=0) || m>500 || n>500;//basta che sia vera una delle condizioni
	}
}
