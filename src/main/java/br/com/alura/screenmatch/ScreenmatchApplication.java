package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("http://www.omdbapi.com/?t=gilmore-girls&apikey=b2b2bdea");
		ConverteDados conversor = new ConverteDados();
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);
		json = consumoAPI.obterDados("http://www.omdbapi.com/?t=gilmore-girls&Season=1&Episode=2&apikey=b2b2bdea");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoAPI.obterDados("http://www.omdbapi.com/?t=gilmore-girls&Season=" + i + "&apikey=b2b2bdea");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			System.out.println(dadosTemporada);
		}
	}
}
