import java.time.*;

public class Reserva{

    private Sala sala; 
    private LocalDateTime dataInicial; 
    private LocalDateTime dataFinal;
    private boolean confirmada;

    public Reserva(){};

    public Reserva(Sala sala, LocalDateTime dataInicial, LocalDateTime dataFinal){
        this.sala = sala;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.confirmada = true;
    }

    public void setConfirmada(boolean confirmada){
        this.confirmada = confirmada;
    }

    public Sala getSala(){
        return sala;
    }

    public LocalDateTime[] getPeriodoReserva(){
        LocalDateTime[] periodo = new LocalDateTime[]{ this.dataInicial, this.dataFinal };
        return periodo;
    }

    public LocalDateTime getInicio(){
        return this.dataInicial;
    }

    public LocalDateTime getFim(){
        return this.dataFinal;
    }

    public boolean getConfirmacao(){
        return this.confirmada;
    }
    
}