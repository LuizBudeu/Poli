package br.ime.usp.mac321.ep1.ex3;

import br.ime.usp.mac321.ep1.ex1.*;
import br.ime.usp.mac321.ep1.ex2.*;

class EventoNormal extends Evento {

	private String a;
	private String d;

	public EventoNormal(long eventTime, String a, String d) {
		super(eventTime);
		this.a = a;
		this.d = d;
	}

	@Override
	public void acao() {
		// System.out.print(a);

	}

	@Override
	public String descricao() {
		return d;
	}

}

public class Simulador1 extends Controlador {

	public String simula() {
		String tudo = "0 min\n";
		
		System.out.print("0 min\n");

		Medico m = new Medico(10, null, 0);
		Evento e = new EventoNormal(0, "", "");
		insere(e);
		tudo += e.descricao();
		
		e = new EventoNormal(0, "", "Médico criado\n");
		insere(e);
		tudo += e.descricao();

		Paciente p = new Paciente(36.5, 110000, 5, 1111, 0);
		e = new EventoNormal(0, "", "Paciente criado\n");
		insere(e);
		tudo += e.descricao();

		e = new EventoNormal(0, "", "Médico consulta temperatura: " + p.getTemperaturaBasal()
				+ "\nMédico consulta concentração: " + p.getConcentracaoPAC() + "\n");
		insere(e);
		tudo += e.descricao();

		e = new EventoNormal(50, "", "5 min\n");
		insere(e);
		tudo += e.descricao();

		e = new EventoNormal(50, "", "Paciente inicia surto infeccioso\n");
		insere(e);
		tudo += e.descricao();

		for (int i = 10; p.taVivo(); i += 10) {
			e = new EventoNormal(i * 10, "", i + " min\n");
			insere(e);
			tudo += e.descricao();

			e = new EventoNormal(0, "", "Médico consulta temperatura: " + p.getTemp(0)
					+ "\nMédico consulta concentração: " + p.getConcentracaoPAC() + "\n");
			insere(e);
			tudo += e.descricao();

			if (i == 60) {
				p.mata();
			}
		}

		e = new EventoNormal(70, "", "70 min\n");
		insere(e);
		tudo += e.descricao();

		e = new EventoNormal(70, "", "Médico verifica óbito do paciente\nSimulação terminada\n");
		insere(e);
		tudo += e.descricao();

		return tudo;
	}
}
