package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.usecase;

import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Relatorio01UseCase implements RelatorioUseCaseI {

    @Override
    public void gerar(ProcessoRelatorio processoRelatorio) {
        log.info("Inicio relatorio 01 - {}", processoRelatorio.getCodProcesso());
        log.info("Fim relatorio 01 - {}", processoRelatorio.getCodProcesso());
    }

    @Override
    public Long getIdRelatorio() {
        return 1L;
    }
}
