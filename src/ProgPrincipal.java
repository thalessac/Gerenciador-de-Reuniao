import java.io.Console;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ProgPrincipal{

    public static void parte1() {
            
            System.out.println("===========================\nPARTE 1: Definicao de dias e participantes\n===========================\n");
            
            //Instanciar Marcador de Reuniao
            MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao();
            LeituraESaidaTerminal c = new LeituraESaidaTerminal();
        
            //Ler datas da reuniao pelo teclado
            try{
                LocalDate periodo[] = c.lerPeriodoReuniao();
                LocalDate dataInicial = periodo[0];
                LocalDate dataFinal = periodo[1];

                 //Ler lista de participantes
                Collection<String> listaParticipantes = new ArrayList<String>();
                listaParticipantes.addAll(c.leituraParticipantes());

                //Marcar reuniao
                marcadorDeReuniao.marcarReuniaoEntre(dataInicial, dataFinal, listaParticipantes);
                
                //Leitura das disponibilidades pelo teclado
                for (Participante participante : marcadorDeReuniao.getListaDeParticipantesCompleta()) {
                    c.lerDisponibilidade(marcadorDeReuniao,participante);
                }

                //Ver sobreposicao
                marcadorDeReuniao.mostraSobreposicao();
            }
            catch(Throwable e){
                e.printStackTrace();
            }
            

           

        }

    public static void parte2() {
        
        GerenciadorDeSalas gerenciadorDeSalas = new GerenciadorDeSalas();
        LeituraESaidaTerminal c = new LeituraESaidaTerminal();

        System.out.println("\n===========================\nPARTE 2: Reserva da sala\n===========================\n");

        String escolha = c.opcoesParte2();

        while (!escolha.equals("q")){

            if (escolha.equals("1")){
                String[] salaNova = new String[3];
                System.out.println("\nCadastramento de nova sala");
                salaNova = c.lerSalaNova();
                try{
                    gerenciadorDeSalas.adicionaSalaChamada(salaNova[0], Integer.parseInt(salaNova[1]), salaNova[2]);
                    
                }
                catch(Throwable e){
                    e.printStackTrace();
                }
                
            }    

            else if (escolha.equals("2")){
                System.out.println("\nExclusao de sala: ");
                System.out.println("\nSalas disponiveis para exclusao: ");
                gerenciadorDeSalas.imprimeListaDeSala();
                System.out.println();
                Console c2 = System.console();
                String sala = c2.readLine("Digite o nome da sala a ser excluida: ");
                try {
                    gerenciadorDeSalas.removeSalaChamada(sala);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (escolha.equals("3")){
                System.out.println("\nExibcao de salas: ");
                gerenciadorDeSalas.imprimeListaDeSala();
            }

            else if (escolha.equals("4")){ //reservar sala

                String[] novaReserva = c.lerReservaNova();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime dataInicial = LocalDateTime.parse(novaReserva[1], formatter);
                LocalDateTime dataFinal = LocalDateTime.parse(novaReserva[2], formatter);
                try {
                    gerenciadorDeSalas.reservaSalaChamada(novaReserva[0], dataInicial, dataFinal);
                } catch (Exception e) {
                    System.out.println("Sala nao existe ou ja ocupada.");
                    e.printStackTrace();
                }

            }

            else if (escolha.equals("5")){ //exibir reservas da sala
                System.out.println("\nExibcao de reservas da sala: ");
                Console c2 = System.console();
                String nomeSala = c2.readLine("Insira o nome da sala: ");
                try {
                    gerenciadorDeSalas.imprimeReservasDaSala(nomeSala);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
            escolha = c.opcoesParte2();
        }
        

    }

    public static void main(String [] args){
            parte1();
            parte2();
        }
}