import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class MarcadorDeReuniao {

    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Collection<String> listaDeParticipantes;
    private List<Participante> listaDeParticipantesCompleta;
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public MarcadorDeReuniao() {
        this.listaDeParticipantes = new ArrayList<String>();
        this.listaDeParticipantesCompleta = new ArrayList<Participante>();
    }

    LocalDate getDataInicialReuniao(){
        return this.dataInicial;
    }

    LocalDate getDataFinalReuniao(){
        return this.dataFinal;
    }

    Collection <String> getListaDeParticipantes(){
        return this.listaDeParticipantes;
    }

    Collection <Participante> getListaDeParticipantesCompleta(){
        return this.listaDeParticipantesCompleta;
    }
       
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        
        if (dataInicial.isAfter(dataFinal)){
            System.out.println("Data inicial posterior a data final");
            return;
        }

        Reuniao reuniao = new Reuniao(dataInicial, dataFinal, listaDeParticipantes);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.listaDeParticipantes = listaDeParticipantes;
        int id = 1;
        for (String part : this.listaDeParticipantes){
            Participante participante = new Participante(id, part);
            this.listaDeParticipantesCompleta.add(participante);
            id++;
        }

    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) throws Exception{
            
        if (inicio.isAfter(fim))
            throw new Exception("Data de inicio deve ser anterior a data de fim");
        
        if(fim.isBefore(this.dataInicial.atStartOfDay()) || inicio.isAfter(this.dataFinal.plusDays(1).atStartOfDay()))
            throw new Exception("Disponibilidade fora do periodo da reuniao");
        
        //Ajustar disponibilidade para caber dentro do periodo da reuniao se necessario
        if (inicio.isAfter(this.dataInicial.atStartOfDay()) && inicio.isBefore(this.dataFinal.plusDays(1).atStartOfDay()) && fim.isAfter(this.dataFinal.plusDays(1).atStartOfDay())){
            fim = this.dataFinal.plusDays(1).atStartOfDay();
        }

        if (inicio.isBefore(this.dataInicial.atStartOfDay()) && fim.isAfter(this.dataInicial.atStartOfDay()) && fim.isBefore(this.dataFinal.plusDays(1).atStartOfDay())){
            inicio = this.dataInicial.atStartOfDay();
        }

        if (inicio.isBefore(this.dataInicial.atStartOfDay()) && fim.isAfter(this.dataFinal.atStartOfDay().plusDays(1))){
            inicio = this.dataInicial.atStartOfDay();
            fim = this.dataFinal.plusDays(1).atStartOfDay();
        }

        //Registrar disponibilidade
        
        for (Participante part : this.listaDeParticipantesCompleta) {
            if (part.getNome().equals(participante)){
                part.definirDisponibilidade(inicio, fim);
                return;
            }
        }

        throw new Exception("Participante nao encontrado na lista de participantes");
    }

    public void mostraSobreposicao(){
        System.out.println("\n=======================\nRELATORIO MARCADOR DE REUNIAO\n=======================\n");
        System.out.println("Periodo disponivel para marcar a reuniao: de " + this.dataInicial + " ate " + this.dataFinal);

        Participante first = this.listaDeParticipantesCompleta.iterator().next();
        List<LocalDateTime> sobreposicao = new ArrayList<LocalDateTime>(); 
        sobreposicao.addAll(first.getDisponibilidade());
   
        //Interseccao de todos os horarios disponiveis de todos os participantes discretizados por minuto  

        for(Participante participante : this.listaDeParticipantesCompleta) {    
            participante.exibirDisponibilidade();
            sobreposicao.retainAll(participante.getDisponibilidade());
        }

        if (sobreposicao.size() <= 1){
            System.out.println("\nNao ha horario disponivel para que todos participem da reuniao ao mesmo tempo\n");
            return;
        }

        //Organizar a impressao do relatorio de sobreposicao 
        System.out.println("\nHorarios disponiveis para a reuniao: \n");
        LocalDateTime lower_bound = sobreposicao.get(0);
        LocalDateTime upper_bound;
        
        System.out.printf("  - de " + dateTimeFormat.format(lower_bound) + " ate " );
        for(int i = 1; i<sobreposicao.size()-1; i++) {
            
            if (!sobreposicao.get(i+1).isEqual(sobreposicao.get(i).plusMinutes(1))){
                upper_bound = sobreposicao.get(i);
                System.out.printf(dateTimeFormat.format(upper_bound.plusMinutes(1)) + "\n");
                lower_bound = sobreposicao.get(i+1);   
                System.out.printf("  - de " + dateTimeFormat.format(lower_bound) + " ate " );
            }   
         }
         upper_bound = sobreposicao.get(sobreposicao.size()-1);
         System.out.printf(dateTimeFormat.format(upper_bound.plusMinutes(1)) + "\n\n");   

    }

}

