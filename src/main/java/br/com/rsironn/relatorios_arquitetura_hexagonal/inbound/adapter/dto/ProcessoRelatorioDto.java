package br.com.rsironn.relatorios_arquitetura_hexagonal.inbound.adapter.dto;

import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProcessoRelatorioDto(
        Long codUsuario,
        Long codFuncionalidade,
        String filtro
) {
    public ProcessoRelatorio toProcessoRelatorio() {
        return new ProcessoRelatorio(codUsuario, codFuncionalidade, filtro);
    }
}
