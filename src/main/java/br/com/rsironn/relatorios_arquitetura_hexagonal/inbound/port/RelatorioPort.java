package br.com.rsironn.relatorios_arquitetura_hexagonal.inbound.port;

import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;

public interface RelatorioPort {
    void criarProcessoRelatorio(ProcessoRelatorio processoRelatorio);
}