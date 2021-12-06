package faggiorosso;

import it.faggiorosso.service.CameraService;
import it.faggiorosso.service.ClienteService;
import it.faggiorosso.service.ConsumazioneService;
import it.faggiorosso.service.DocumentoService;
import it.faggiorosso.service.MagazzinoService;
import it.faggiorosso.service.OrdineService;
import it.faggiorosso.service.PrenotazioneService;
import it.faggiorosso.service.ProdottoService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

	private CameraService cameraService = new CameraService();
	private ClienteService clienteService = new ClienteService();
	private ConsumazioneService consumazioneService = new ConsumazioneService();
	private DocumentoService documentoService = new DocumentoService();
	private MagazzinoService magazzinoService = new MagazzinoService();
	private OrdineService ordineService = new OrdineService();
	private PrenotazioneService prenotazioneService = new PrenotazioneService();
	private ProdottoService prodottoService = new ProdottoService();
	
	
	public void cameraServiceTests() {
		
	}
}