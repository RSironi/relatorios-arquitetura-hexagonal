package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.usecase;

import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Relatorio02UseCase implements RelatorioUseCaseI{
    @Override
    public void gerar(ProcessoRelatorio processoRelatorio) {
        log.info("Inicio relatorio 02 - {}", processoRelatorio.getCodProcesso());
        log.info("Fim relatorio 02 - {}", processoRelatorio.getCodProcesso());
    }

    @Override
    public Long getIdRelatorio() {
        return 2L;
    }
}