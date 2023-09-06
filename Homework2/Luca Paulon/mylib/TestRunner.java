import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
	public static void main(String[] args)
	{
		/**Esecuzione dell'intera suite di test. Si visualizza sullo schermo il numero di test: falliti,
		 * eseguiti, ignorati poi il tempo di esecuzione e se l'esecuzione ha avuto successo oppure no,
		 * ovvero se tutti i test hanno dato esito positivo.*/
		Result result =JUnitCore.runClasses(TestSuite.class);
		for(Failure failure: result.getFailures())
			System.out.println(failure.toString());

		System.out.println();
		System.out.println("Successful: "+result.wasSuccessful()+"\n");
		System.out.println("Numero di test eseguiti: "+result.getRunCount());
		System.out.println("Numero di test falliti: "+result.getFailureCount());
		System.out.println("Numero di test ignorati: "+result.getIgnoreCount());
		System.out.println("Tempo di esecuzione della suite: "+result.getRunTime()+"ms");
		
	}
}