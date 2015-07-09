package com.portoseguro.portodrcar.constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.portoseguro.portodrcar.enums.ImportanciaLembreteEnum;
import com.portoseguro.portodrcar.enums.TipoLembreteEnum;
import com.portoseguro.portodrcar.model.Agendamento;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.model.Oficina;

public class PrototipoConstants {
	public static String USUARIO_LOGADO = "Carlos Alberto Parreira";
	public static String PLACA = "ABC1234";
	public static String MODELO = "VW Gol 1.0";
	public static int contAgendamento = 0;
	
	private static List<Lembrete> lembretes = new ArrayList<Lembrete>();
	private static List<Agendamento> agendamentos = new ArrayList<Agendamento>();
	private static List<Oficina> oficinas = new ArrayList<Oficina>();
	
	static {
		lembretes.add(new Lembrete(5, ImportanciaLembreteEnum.URGENTE, TipoLembreteEnum.MANUTENCAO, "Lubrificacao do chassi", "Os chassis dos carros novos vem lubrificados de fabrica. Sempre que substituir os sistemas de direcao e suspensao, lubrifique ambos novamente.", "sempre que substituir os sistemas de direcao", Calendar.getInstance()));
		lembretes.add(new Lembrete(4, ImportanciaLembreteEnum.URGENTE, TipoLembreteEnum.MANUTENCAO, "Verificar embreagem", "Deve ser verificado quando perceber o pedal frouxo, dificuldade de engate de marcha, perda de aceleracao e barulhos estranhos. Para os cambios automaticos, siga a orientacao do manual e fique atento a anormalidades.", "a cada 3 meses", Calendar.getInstance()));
		lembretes.add(new Lembrete(3, ImportanciaLembreteEnum.ALTA, TipoLembreteEnum.MANUTENCAO, "Verificar rodas e pneus", "Verificar a calibragem dos pneus e fazer a regulagem, conforme o manual. Os pneus e estepe deve estar em boas condicoes de uso. O rodizio de pneus deve ser feito a cada 10.000 Km rodados, garantindo economia e uma vida util maior. Ao fazer o rodizio, e importante balancear e alinhar as rodas para evitar desgaste prematuro", "a cada 10.000 Km rodados", Calendar.getInstance()));
		lembretes.add(new Lembrete(1, ImportanciaLembreteEnum.BAIXA, TipoLembreteEnum.AVISO, "Trocar óleo", "Trocar o óleo do seu carro é uma tarefa mais fácil do que parece e também pode fazer você economizar dinheiro a longo prazo. Não deve levar mais do que 45 minutos – apenas tenha certeza de seguir as instruções específicas e de segurança da montadora.", "a cada 5.000 Km rodados ou 6 meses", Calendar.getInstance()));
		lembretes.add(new Lembrete(2, ImportanciaLembreteEnum.MEDIA, TipoLembreteEnum.AVISO, "Avaliar bateria e cabos", "Suas conexoes devem estar limpas, bem apertadas e sem sinal de corrossao. Se ela tiver 3 anos ou mais, deve ser testada e trocada, se necessario. Faca revisoes periodicas do sistema eletrico do veiculo (alternador, motor de partida, regulador de tensao, cabos e terminais) em uma de nossas oficinas", "a cada 3 anos", Calendar.getInstance()));
		
		agendamentos.add(new Agendamento(++contAgendamento, "Trocar vela", "A troca de velas e a limpeza dos bicos injetores garantem o bom desempenho do veiculo, evitando perda de potencia e falhas", new Date(2014, 5, 10, 10, 30), "Oficina ALFACAR", "Rua Sebastiao Miguel da Silva, 95", "(11) 2749-7685", ImportanciaLembreteEnum.ALTA));
		agendamentos.add(new Agendamento(++contAgendamento, "Trocar filtro combustivel", "Deve ser substituida de acordo com o manual. Se a troca nao for realizada no periodo determinado, a bomba do combustivel pode ser danificada", new Date(2014, 6, 15, 15, 00), "CAPS Ipiranga", "Rua Bom Pastor, 975 - Ipiranga", "(11) 3476-0004", ImportanciaLembreteEnum.MEDIA));
		
		oficinas.add(new Oficina(1, "Oficina do Ze", "-23.490822", "-46.550899", "(11)1234-5678", "Avenida Dois 2, Guarulhoss"));
		oficinas.add(new Oficina(2, "REYCAR MULTIMARCAS", "-23.555174", "-46.622336", "(11)2347-4231", "Alameda Ribeiro da Silva 2, Sao Paulo"));
		oficinas.add(new Oficina(3, "OFICINA SEM PERITO", "-23.547556", "-46.634541", "(11)3323-3140", "Rua Glete 999, Sao Paulo"));
		oficinas.add(new Oficina(4, "CAR TURIASSU", "-23.532727", "-46.670594", "(11)4234-6584", "Praca da Se 150, Sao Paulo"));
		oficinas.add(new Oficina(5, "JC CAR", "-23.531144", "-46.654617", "(11)6546-1343", "Rua Santa Ifigenia 500, Sao Paulo"));
		oficinas.add(new Oficina(6, "AZPESI IMPORT", "-23.531300", "-46.654340", "(11)2348-9029", "Rua da Silva 1, Sao Paulo"));
		oficinas.add(new Oficina(7, "GOLDEN CAR", "-23.461958", "-46.528774", "(11)4802-4308", "Avenida Rotary 565, Guarulhos"));
	}

	public static List<Lembrete> getLembretes() {
		return lembretes;
	}

	public static void setLembretes(List<Lembrete> lembretes) {
		PrototipoConstants.lembretes = lembretes;
	}

	public static List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public static void setAgendamentos(List<Agendamento> agendamentos) {
		PrototipoConstants.agendamentos = agendamentos;
	}
	
	public static List<Oficina> getOficinas() {
		return oficinas;
	}

	public static void setOficinas(List<Oficina> oficinas) {
		PrototipoConstants.oficinas = oficinas;
	}

	public static void transformaLembreteEmAgendamento(Lembrete lembrete, Oficina oficina, Calendar dataAgendada){
		for (Lembrete l : lembretes) {
			if (l.getId().equals(lembrete.getId())){
				lembretes.remove(l);
				Agendamento novoAgendamento = new Agendamento(
					Integer.valueOf(++contAgendamento), 
					lembrete.getTitulo(), 
					lembrete.getDescricao(), 
					dataAgendada.getTime(), 
					oficina.getDescricao(), 
					oficina.getEndereco(), 
					oficina.getTelefone(),
					lembrete.getImportancia());
				agendamentos.add(novoAgendamento);
				break;
			}
		}
	}

	public static CharSequence getApresentacao() {
		return "Bem vindo, " + PrototipoConstants.USUARIO_LOGADO.split(" ")[0] + "! (" + MODELO + ")";
	}
}
