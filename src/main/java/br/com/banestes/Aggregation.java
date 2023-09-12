package br.com.banestes;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Aggregation {
	
	public Integer ultimaLinha;
	
	public Aggregation updateFrom(Integer ultimaLinha) {
		this.ultimaLinha = ultimaLinha;
		return this;	
	}

}
