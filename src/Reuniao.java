import java.time.*;
import java.util.*;

public class Reuniao{
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Collection<String> listaDeParticipantes;

    public Reuniao(){}
    
    public Reuniao(LocalDate dataInicio, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.listaDeParticipantes = listaDeParticipantes;
    }

    public LocalDate getIncio(){
        return this.dataInicio;
    }

    public void setInicio(LocalDate dataInicio){
        this.dataInicio = dataInicio;
    }

    public LocalDate getFim(){
        return this.dataFinal;
    }

    public void setFim(LocalDate dataFinal){
        this.dataFinal = dataFinal;
    }

    public Collection<String> getListaDeParticipantes(){
        return this.listaDeParticipantes;
    }
}