import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

// Notas:
// Não altere o código apresentado na alinea 1
// Deve completar o código das alineas 2 e 3
// Pode comentar código para garantir que vai executando parcialmente

public class PDS2021 {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream fl = new PrintStream(new File("pds2021.txt"));
		test(System.out); // executa e escreve na consola
		fl.println(System.getProperty("user.dir"));
		test(fl); // executa e escreve no ficheiro
		fl.close();
	}

	private static void test(PrintStream out) {
		alinea1(out);
		alinea2(out);
		alinea3(out);
		
	}

	private static void alinea1(PrintStream out) {
		out.println("\nAlínea 1) ----------------------------------\n");
		BasicCamperService onwheels = new BasicCamperService();

		Cliente c1 = new Cliente("Marco Silva", "Setúbal", 32);
		Cliente c2 = new Cliente("Joana Inácio", "Viseu", 54);
		Cliente c3 = new Cliente("Carolina Marques", "Vila Real", 27);
		Cliente c4 = new Cliente("Telmo Sousa", "Coimbra", 51);

		ICamper h1 = onwheels.registaCamper(4, "MotorHome com quatro lugares, 3 camas, cozinha, mesa, WC.");
		ICamper h2 = onwheels.registaCamper(4, "Camper van com quatro lugares, 2 camas, mesa.");
		ICamper h3 = onwheels.registaCamper(2, "Camper van com dois lugares, 1 cama, cozinha, mesa, WC.");
		ICamper h4 = onwheels.registaCamper(2, "Tenda de tejadilho para duas pessoas, de montagem rápida.");

		out.println("\n--- Campers disponíveis :" );
		for (ICamper camper : onwheels.getCampersDisponiveis())
			out.println(camper);
		out.println("\n" );
		
		out.println(onwheels.registaAluguer(c1, h1));
		out.println(onwheels.registaAluguer(c2, h3));

		out.println("\n--- Campers disponíveis :" );
		for (ICamper camper : onwheels.getCampersDisponiveis())
			out.println(camper);	
		out.println("\n" );

		out.println(onwheels.terminaAluguer(c1, h1));

		out.println("\n--- Campers disponíveis :" );
		for (ICamper camper : onwheels.getCampersDisponiveis())
			out.println(camper);
		out.println("\n" );

	}


	private static void alinea2(PrintStream out) {
		out.println("\n\nAlínea 2 (exemplo de output) ----------------------------------\n");
		Camper broker = new Camper();
		Proprietario p = new Proprietario("José", "Costa", 54);

		Cliente c1 = new Cliente("Marco Silva", "Setúbal", 32);
		Cliente c2 = new Cliente("Joana Inácio", "Viseu", 54);
		Cliente c3 = new Cliente("Carolina Marques", "Vila Real", 27);
		Cliente c4 = new Cliente("Telmo Sousa", "Coimbra", 51);

		ICamper camp1 = broker.registaCamper(p, 4, "MotorHome com quatro lugares, 3 camas, cozinha, mesa, WC.");
		ICamper camp2 = broker.registaCamper(p, 4, "Camper van com quatro lugares, 2 camas, mesa.");
		ICamper camp3 = broker.registaCamper(p, 2, "Camper van com dois lugares, 1 cama, cozinha, mesa, WC.");
		ICamper camp4 = broker.registaCamper(p, 2, "Tenda de tejadilho para duas pessoas, de montagem rápida.");

		out.println(broker.registaPedido(c1, camp1));
		out.println(broker.registaPedido(c2, camp3));
		out.println(broker.registaPedido(c3, camp4));

		out.println("\n--- Campers disponíveis :");
		for(ICamper c : broker.getCampersDisponiveis()) {
			out.println(c);
		}
		out.println("\n");

		out.println(broker.aceitaPedido(c1, camp1));
		out.println(broker.rejeitaPedido(c2, camp3));

		out.println("\n--- Campers disponíveis :");
		for(ICamper c : broker.getCampersDisponiveis()) {
			out.println(c);
		}
		out.println("\n");

		out.println(broker.cancelaPedido(c3, camp4));

		out.println("\n--- Campers disponíveis :");
		for(ICamper c : broker.getCampersDisponiveis()) {
			out.println(c);
		}
		out.println("\n");

	}
	
	
	private static void alinea3(PrintStream out) {
		out.println("\n\nAlínea 3) ----------------------------------\n");
		Camper broker = new Camper();
		Proprietario p = new Proprietario("José", "Costa", 54);

		Cliente c1 = new Cliente("Marco Silva", "Setúbal", 32);
		Cliente c2 = new Cliente("Joana Inácio", "Viseu", 54);
		Cliente c3 = new Cliente("Carolina Marques", "Vila Real", 27);
		Cliente c4 = new Cliente("Telmo Sousa", "Coimbra", 51);

		ICamper camp1 = broker.registaCamper(p, 4, "MotorHome com quatro lugares, 3 camas, cozinha, mesa, WC.");
		ICamper camp2 = broker.registaCamper(p, 4, "Camper van com quatro lugares, 2 camas, mesa.");
		ICamper camp3 = broker.registaCamper(p, 2, "Camper van com dois lugares, 1 cama, cozinha, mesa, WC.");
		ICamper camp4 = broker.registaCamper(p, 2, "Tenda de tejadilho para duas pessoas, de montagem rápida.");

		out.println(broker.registaPedido(c1, camp1));
		out.println(broker.registaPedido(c2, camp3));
		out.println(broker.registaPedido(c3, camp4));

		out.println("\n--- Campers disponíveis :");
		for(ICamper c : broker.getCampersDisponiveis()) {
			out.println(c);
		}
		out.println("\n");

		out.println(broker.aceitaPedido(c1, camp1));
		out.println(broker.rejeitaPedido(c2, camp3));

		out.println("\n--- Campers disponíveis :");
		for(ICamper c : broker.getCampersDisponiveis()) {
			out.println(c);
		}
		out.println("\n");

		out.println(broker.cancelaPedido(c3, camp4));

		out.println("\n--- Campers disponíveis :");
		for(ICamper c : broker.getCampersDisponiveis()) {
			out.println(c);
		}
		out.println("\n");
	}

}