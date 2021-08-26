import java.io.Console;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LeituraESaidaTerminal{
    
    public LocalDate[] lerPeriodoReuniao(){

        Console c = System.console();

        String data_inicio_s = c.readLine("Insira a data de inicio (DD/MM/YYYY): ");
        String data_fim_s = c.readLine("Insira a data final (DD/MM/YYYY): ");

        //convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data_inicio = LocalDate.parse(data_inicio_s, formatter);
        LocalDate data_fim = LocalDate.parse(data_fim_s, formatter);

        LocalDate[] periodo = new LocalDate[]{ data_inicio, data_fim };

        return periodo;
        
    }

    public Collection<String> leituraParticipantes(){
        
        Console c = System.console();
        int id = 1;
        String nome;
        Collection<String> listaParticipantes = new ArrayList<String>();

        while (true){
            nome = c.readLine("Digite o nome do(a) participante %d ('q' para sair): ", id);
            if(nome.equals("q")) break;

            listaParticipantes.add(nome);
            id++;
        }

        return listaParticipantes;

    }

    public void lerDisponibilidade(MarcadorDeReuniao marcadorDeReuniao, Participante participante){
        
        Console c = System.console();
        String inicio_s;
        String fim_s;

        System.out.println("\nBem-vindo(a), " + participante.getNome() + "!\nInsira suas disponibilidades:");

        while (true){
            inicio_s = c.readLine("Inicio da disponibilidade (DD/MM/YYYY hh:mm) ('q' para sair): ");
            if(inicio_s.equals("q")) break;
            fim_s = c.readLine("Fim da disponibilidade (DD/MM/YYYY hh:mm): ");

            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime inicio = LocalDateTime.parse(inicio_s, formatter);
                LocalDateTime fim = LocalDateTime.parse(fim_s, formatter);

                try{
                    marcadorDeReuniao.indicaDisponibilidadeDe(participante.getNome(), inicio, fim);
                }
                catch(Throwable e){
                    e.printStackTrace();
                }
            }
            catch(Throwable e){
                e.printStackTrace();
            }

            
           
        }

    }

    public String[] lerSalaNova(){

        Console c = System.console();
        
        String nome = c.readLine("Insira o nome da sala: ");
        String capacidade = c.readLine("Insira a capacidade da sala: ");
        String descricao = c.readLine("Insira a descricao da sala: ");
        String local = c.readLine("Insira o local da sala: ");

        String[] salaNova = new String[]{ nome, capacidade, descricao, local };

        return salaNova;
        
    }

    public String[] lerReservaNova(){

        Console c = System.console();
        
        String nomeDaSala = c.readLine("Insira o nome da sala: ");
        String dataInicio = c.readLine("Insira a data e horario de INICIO da reserva (DD/MM/YYYY hh:mm)) : ");
        String dataFim = c.readLine("Insira a data e horario FINAL da reserva (DD/MM/YYYY hh:mm)) : ");
        
        String[] reservaNova = new String[]{ nomeDaSala, dataInicio, dataFim };

        return reservaNova;
        

    }
    

    public String opcoesParte2(){
        Console c = System.console();
        System.out.println("\n-----------------------------------");
        System.out.println("Menu:\n1 - Cadastrar nova sala\n2 - Remover sala\n3 - Exibir salas\n4 - Reservar uma sala\n5 - Exibir reservas de uma sala\nDigite 'q' para encerrar");
        String escolha = c.readLine("\nEscolha uma opcao: ");
        System.out.println("\n-----------------------------------");
        return escolha;
    }


}