package Conexiones.scheduler;

import Conexiones.controller.AdminAuthController;
import Conexiones.service.ReservaService;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservaScheduler {

	private static final Logger log = Logger.getLogger(AdminAuthController.class.getName());

	@Autowired
	private ReservaService reservaService;

	// Todos los días a las 00:05
	@Scheduled(cron = "0 5 0 * * *")
	public void actualizarReservasEnCurso() {

		reservaService.actualizarReservasEnCurso();
		reservaService.finalizarReservasVencidas();
		log.info("Scheduler ejecutado: Reservas en curso actualizadas y reservas vencidas finalizadas.");
	}
}
