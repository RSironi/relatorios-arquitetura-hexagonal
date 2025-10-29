package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum StatusProcessoRelatorioEnum {
    NA_FILA(1),
    EM_PROCESSAMENTO(2),
    PROCESSADO(3),
    ERRO(4);

    private final Integer codStatus;

    StatusProcessoRelatorioEnum(Integer codStatus) {
        this.codStatus = codStatus;
    }

    public static StatusProcessoRelatorioEnum getEnumByCod(Integer codStatus) {
        return Arrays.stream(StatusProcessoRelatorioEnum.values())
                .filter(status -> Objects.equals(status.getCodStatus(), codStatus))
                .findFirst().
                orElseThrow(() -> new IllegalArgumentException("Código de status inválido ou inexistente"));
    }
}
