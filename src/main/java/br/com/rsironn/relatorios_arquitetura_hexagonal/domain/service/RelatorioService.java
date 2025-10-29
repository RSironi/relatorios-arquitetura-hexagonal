package br.com.rsironn.relatorios_arquitetura_hexagonal.domain.service;

import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;
import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.StatusProcessoRelatorioEnum;
import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.usecase.RelatorioUseCaseI;
import br.com.rsironn.relatorios_arquitetura_hexagonal.inbound.port.RelatorioPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RelatorioService implements RelatorioPort {

    private final Map<Long, RelatorioUseCaseI> mapRelatorios;

    public RelatorioService(List<RelatorioUseCaseI> relatorios) {
        this.mapRelatorios = relatorios.stream().collect(Collectors.toMap(RelatorioUseCaseI::getIdRelatorio, Function.identity()));
    }

    @Override
    public void criarProcessoRelatorio(ProcessoRelatorio processoRelatorio) {
        // Podemos validar ou criar persistencia com BD antes de processar.

        processoRelatorio.setCodProcesso((long) (10 + new Random().nextInt(90))); // Simulando a geração de um código de processo.
        try {
            gerarRelatorio(processoRelatorio);
        } catch (Exception e) {
            processoRelatorio.setStatus(StatusProcessoRelatorioEnum.ERRO);
            processoRelatorio.setObservacao(e.getMessage());
            log.error("Erro ao processar {}: {}", processoRelatorio, e.getMessage(), e);
            throw e;
        }
    }

    private void gerarRelatorio(ProcessoRelatorio processoRelatorio){
        log.info("inicio - ProcessoRelatorio codigo {}", processoRelatorio.getCodProcesso());

        processoRelatorio.setInicioProcessamento(LocalDateTime.now());
        mapRelatorios.get(processoRelatorio.getCodFuncionalidade()).gerar(processoRelatorio);

        processoRelatorio.setFimProcessamento(LocalDateTime.now());
        processoRelatorio.setStatus(StatusProcessoRelatorioEnum.PROCESSADO);
        log.info("fim - gerando ProcessoRelatorio codigo {} - saida: {}", processoRelatorio.getCodProcesso(), processoRelatorio);
    }
}
