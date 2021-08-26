import java.util.*;
import java.time.*;

public class Teste3 {

	public static MarcadorDeReuniao marcadorDaReuniao = new MarcadorDeReuniao();
	public static LocalDate  dia02mes8de2021 =  LocalDate.of(2021, 8, 2);
    public static LocalDate  dia03mes8de2021 =  LocalDate.of(2021, 8, 3);
    public static LocalDate  dia04mes8de2021 =  LocalDate.of(2021, 8, 4);
	public static LocalTime as0horas = LocalTime.of(0, 0);
    public static LocalTime as8horas = LocalTime.of(8, 0);
    public static LocalTime as9horas = LocalTime.of(9, 0);
    public static LocalTime as10horas = LocalTime.of(10, 0);
    public static LocalTime as11horas = LocalTime.of(11, 0);
    public static LocalTime as12horas = LocalTime.of(12, 0);
    public static LocalTime as13horas = LocalTime.of(13, 0);
    public static LocalTime as14horas = LocalTime.of(14, 0);
    public static LocalTime as15horas = LocalTime.of(15, 0);
    public static LocalTime as16horas = LocalTime.of(16, 0);
    public static LocalTime as17horas = LocalTime.of(17, 0);
    public static LocalTime as18horas = LocalTime.of(18, 0);
    public static LocalTime as19horas = LocalTime.of(19, 0);
    public static LocalTime as23_59horas = LocalTime.of(23, 59);

	public static String thales = "Thales";
	public static String fred = "Fred";
	public static String bruno = "Bruno";
	public static String daniel = "Daniel";
	public static String felipe = "Felipe";
	
	public static void main(String[] args) {
		
        try {
            List<String> listaDeParticipantes = List.of(thales, fred, bruno, daniel, felipe);
		    marcadorDaReuniao.marcarReuniaoEntre(dia02mes8de2021, dia04mes8de2021, listaDeParticipantes); 

            marcadorDaReuniao.indicaDisponibilidadeDe(thales, LocalDateTime.of(dia02mes8de2021, as8horas), LocalDateTime.of(dia02mes8de2021, as12horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(thales, LocalDateTime.of(dia02mes8de2021, as13horas), LocalDateTime.of(dia02mes8de2021, as17horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(thales, LocalDateTime.of(dia03mes8de2021, as8horas), LocalDateTime.of(dia03mes8de2021, as19horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(thales, LocalDateTime.of(dia04mes8de2021, as10horas), LocalDateTime.of(dia04mes8de2021, as16horas));
			
            marcadorDaReuniao.indicaDisponibilidadeDe(fred, LocalDateTime.of(dia02mes8de2021, as14horas), LocalDateTime.of(dia02mes8de2021, as16horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(fred, LocalDateTime.of(dia03mes8de2021, as8horas), LocalDateTime.of(dia03mes8de2021, as14horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(fred, LocalDateTime.of(dia04mes8de2021, as13horas), LocalDateTime.of(dia04mes8de2021, as19horas));

            marcadorDaReuniao.indicaDisponibilidadeDe(bruno, LocalDateTime.of(dia02mes8de2021, as9horas), LocalDateTime.of(dia02mes8de2021, as15horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(bruno, LocalDateTime.of(dia03mes8de2021, as11horas), LocalDateTime.of(dia03mes8de2021, as17horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(bruno, LocalDateTime.of(dia04mes8de2021, as16horas), LocalDateTime.of(dia04mes8de2021, as18horas));

            marcadorDaReuniao.indicaDisponibilidadeDe(daniel, LocalDateTime.of(dia02mes8de2021, as10horas), LocalDateTime.of(dia02mes8de2021, as16horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(daniel, LocalDateTime.of(dia02mes8de2021, as16horas), LocalDateTime.of(dia02mes8de2021, as17horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(daniel, LocalDateTime.of(dia03mes8de2021, as9horas), LocalDateTime.of(dia03mes8de2021, as12horas));
            marcadorDaReuniao.indicaDisponibilidadeDe(daniel, LocalDateTime.of(dia04mes8de2021, as0horas), LocalDateTime.of(dia04mes8de2021, as23_59horas));

            marcadorDaReuniao.indicaDisponibilidadeDe(felipe, LocalDateTime.of(dia02mes8de2021, as0horas), LocalDateTime.of(dia04mes8de2021, as23_59horas));

            marcadorDaReuniao.mostraSobreposicao();
		}
		catch(Throwable e){
			e.printStackTrace();
	
		}
	}
}