package hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hotel.service.CameraService;
import com.hotel.service.ClienteService;
import com.hotel.service.ConsumazioneService;
import com.hotel.service.DocumentoService;
import com.hotel.service.MagazzinoService;
import com.hotel.service.OrdineService;
import com.hotel.service.PrenotazioneService;
import com.hotel.service.ProdottoService;

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