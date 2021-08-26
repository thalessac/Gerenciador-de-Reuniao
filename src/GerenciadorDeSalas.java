import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class GerenciadorDeSalas {

    private List<Sala> listaDeSalas;
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public GerenciadorDeSalas(){
        this.listaDeSalas = new ArrayList<Sala>();
    }

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String observacao) throws Exception{
        
        for (Sala sala : this.listaDeSalas){
            if (sala.getNome().equals(nome))
                throw new Exception("Sala com o nome " + nome + " ja existe");
        }
      
        Sala sala = new Sala(nome, capacidadeMaxima, observacao);
        this.listaDeSalas.add(sala);
    }

    public void removeSalaChamada(String nomeDaSala) throws Exception{
        for (Sala sala : listaDeSalas){
            if(sala.getNome().equals(nomeDaSala)){
                this.listaDeSalas.remove(sala);
                System.out.println("Sala " + nomeDaSala + " excluida com sucesso!");
                return;
            }
        }
        throw new Exception("Sala com o nome " + nomeDaSala + " nao encontrada");
    }

    public List<Sala> listaDeSalas(){
        return listaDeSalas;
    }

    public void adicionaSala(Sala novaSala) throws Exception{
        
        for (Sala sala : this.listaDeSalas){
            if (sala.getNome().equals(novaSala.getNome()))
                throw new Exception("Sala com o nome " + novaSala.getNome() + " ja existe");
        }
      
        this.listaDeSalas.add(novaSala);
            
    }


    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception{
              
            if (dataInicial.isAfter(dataFinal))
                throw new Exception("Data inicial precisa ser anterior a data final");

            for (Sala sala : listaDeSalas){
                if(sala.getNome().equals(nomeDaSala)){
                    if (sala.checarDisponibilidade(dataInicial, dataFinal)){
                        Reserva reserva = new Reserva(sala, dataInicial, dataFinal);
                        sala.adicionarReserva(reserva);
                        System.out.println("\nReserva efetuada com sucesso! Confira os detalhes:");
                        System.out.println("Sala: " + nomeDaSala);
                        System.out.println("Horario: " + dateTimeFormat.format(dataInicial) + " - " + dateTimeFormat.format(dataFinal));
                        System.out.println("Local: " + sala.getLocal());
                        System.out.println("Capacidade: " + sala.getCapacidade());
                        
                        return reserva;
                    }
                    else {
                        throw new Exception("Sala ja reservada nesse horario");
                    }
                }
            }
            throw new Exception("Sala inexistente");
    }

    public void cancelaReserva(Reserva cancelada){
        //cancelada.setConfirmada(false);
        
        for (Sala sala : this.listaDeSalas){
            if (sala.getNome().equals(cancelada.getSala().getNome())){
                sala.removerReserva(cancelada);
                return;
            }
                
        }

        System.out.println("Reserva nao encontrada");

    }

    public void imprimeReservasDaSala(String nomeSala) throws Exception{
                
        for (Sala sala : listaDeSalas){
            if(sala.getNome().equals(nomeSala)){
                sala.exibirReservas();
                return;
            }
        }
        throw new Exception("Sala nao encontrada");
    }

    public Collection<Reserva> reservasParaSala(String nomeSala){
        for (Sala sala : listaDeSalas){
            if(sala.getNome().equals(nomeSala)){
                return sala.getListaDeReserva();
            }
        }
        return null;
    }

    public void imprimeListaDeSala(){
        for (Sala sala : listaDeSalas){
            System.out.println(sala.getNome());
        }
    }

}