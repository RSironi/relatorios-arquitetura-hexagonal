package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessoRelatorio {
    private Long codProcesso;
    private Long codUsuario;
    private Long codFuncionalidade;
    private String filtro;
    private StatusProcessoRelatorioEnum status;
    private LocalDateTime inicioProcessamento;
    private LocalDateTime fimProcessamento;
    private String observacao;

    public ProcessoRelatorio(Long codUsuario, Long codFuncionalidade, String filtro) {
        this.codUsuario = codUsuario;
        this.codFuncionalidade = codFuncionalidade;
        this.filtro = filtro;
        this.status = StatusProcessoRelatorioEnum.NA_FILA;
    }
}
