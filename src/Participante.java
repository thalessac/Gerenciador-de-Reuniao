import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Participante{
    
    private String nome;
    private int id;
    private List<LocalDateTime[]> listaDisponibilidade; //disponibilidade apenas com datetime inicio e fim
    private List<LocalDateTime> disponibilidadeVec; //disponibilidade discretizada em minutos
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Participante(){}

    public Participante (int id, String nome){
        this.nome = nome;
        this.id = id;
        this.listaDisponibilidade = new ArrayList<LocalDateTime[]>();
        this.disponibilidadeVec = new ArrayList<LocalDateTime>();
    }

    public void definirDisponibilidade(LocalDateTime inicio, LocalDateTime fim){
        LocalDateTime[] periodo = new LocalDateTime[]{ inicio, fim };
        listaDisponibilidade.add(periodo);

        LocalDateTime iterador = inicio;
        while (!iterador.equals(fim)){
            disponibilidadeVec.add(iterador);
            iterador = iterador.plusMinutes(1);
        }
        //disponibilidadeVec.add(fim);

    }

    public String getNome(){
        return this.nome;
    }

    public List<LocalDateTime> getDisponibilidade(){
        return this.disponibilidadeVec;
    }

    public void exibirDisponibilidade(){

        System.out.println();
        System.out.println("Disponibilidade de: " + this.nome);
        for (LocalDateTime[] disp : this.listaDisponibilidade)
            System.out.println("\tde " + dateTimeFormat.format(disp[0]) + " ate " + dateTimeFormat.format(disp[1]));
        
    }

}
