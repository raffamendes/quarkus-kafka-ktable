package br.com.banestes;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutomaticProducer {
	
	private Random random = new Random();
	
	private List<Arquivo> arquivos = List.of(
			new Arquivo("arquivo1",1),
			new Arquivo("arquivo2",1),
			new Arquivo("arquivo3",1),
			new Arquivo("arquivo4",1),
			new Arquivo("arquivo5",1),
			new Arquivo("arquivo6",1),
			new Arquivo("arquivo7",1),
			new Arquivo("arquivo8",1));
	
	
	@Outgoing("auto-producer")
	public Multi<Record<String, Integer>> generate(){
		return Multi.createFrom().ticks().every(Duration.ofMillis(10000))
				.onOverflow().drop()
				.map(tick -> {
					Arquivo a = arquivos.get(random.nextInt(arquivos.size()));
					a.linha = random.nextInt(1, 10000);
					return Record.of(a.fileName, a.linha);
				});
	}
			
	
	
}
