package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.usecase;

import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;

public interface RelatorioUseCaseI {

    void gerar(ProcessoRelatorio processoRelatorio);

    Long getIdRelatorio();
}

