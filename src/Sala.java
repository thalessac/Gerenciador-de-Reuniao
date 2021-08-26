import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Sala{
    private String nome;
    private String local;
    private int capacidadeMaxima;
    private String observacao;
    private Collection<Reserva> listaDeReservas;
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public Sala(){
        this.listaDeReservas = new ArrayList<Reserva>();
    }

    public Sala(String nome, int capacidadeMaxima, String observacao){
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.observacao = observacao;
        this.listaDeReservas = new ArrayList<Reserva>();
    }   

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLocal(){
        return this.local;
    }

    public void setLocal(String local){
        this.local = local;
    }

    public int getCapacidade(){
        return this.capacidadeMaxima;
    }

    public void setCapacidade(int capacidadeMaxima){
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getObservacoes(){
        return this.observacao;
    }

    public void setObservacoes(String observacao){
        this.observacao = observacao;
    }

    public Collection<Reserva> getListaDeReserva(){
        return this.listaDeReservas;
    }

    public void adicionarReserva(Reserva reserva){
        this.listaDeReservas.add(reserva);
    }

    public void removerReserva(Reserva reserva){
        if(this.listaDeReservas.remove(reserva)){
            System.out.println("Reserva em " + reserva.getSala().getNome() + " as " + reserva.getInicio() + " cancelada com sucesso");
            reserva = null;
            return;
        }
        System.out.println("Reserva nao encontrada");
    }

    public void exibirReservas(){

        if (this.listaDeReservas.size()==0){
            System.out.println("Não ha reservas para a sala " + this.nome + "\n");
            return;
        }

        System.out.println("\nReservas em: " + this.nome);
        for (Reserva reserva : this.listaDeReservas){
            LocalDateTime[] periodo = reserva.getPeriodoReserva();
            if (reserva.getConfirmacao()==true)
                System.out.printf("\tde %s ate %s\n", dateTimeFormat.format(periodo[0]), dateTimeFormat.format(periodo[1]));
        }

        System.out.println();
    }

    public boolean checarDisponibilidade(LocalDateTime inicio, LocalDateTime fim){
        for (Reserva reserva : this.listaDeReservas){
            if ((inicio.isBefore(reserva.getInicio()) && fim.isAfter(reserva.getInicio())) ||
                (inicio.isAfter(reserva.getInicio()) && fim.isBefore(reserva.getFim())) ||
                (inicio.isBefore(reserva.getFim()) && fim.isAfter(reserva.getFim())) ||
                (inicio.isBefore(reserva.getInicio()) && fim.isAfter(reserva.getFim())) ||
                inicio.isEqual(reserva.getInicio()))
                    return false;
        }
        return true;
    }

}