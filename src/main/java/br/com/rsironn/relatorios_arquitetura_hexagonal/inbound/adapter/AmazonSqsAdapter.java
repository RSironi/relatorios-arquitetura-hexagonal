package br.com.rsironn.relatorios_arquitetura_hexagonal.inbound.adapter;

import br.com.rsironn.relatorios_arquitetura_hexagonal.inbound.adapter.dto.ProcessoRelatorioDto;
import br.com.rsironn.relatorios_arquitetura_hexagonal.inbound.port.RelatorioPort;
import br.com.rsironn.relatorios_arquitetura_hexagonal.domain.model.processoRelatorio.ProcessoRelatorio;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AmazonSqsAdapter {
    private final RelatorioPort relatorioPort;

    public AmazonSqsAdapter(RelatorioPort relatorioPort) {
        this.relatorioPort = relatorioPort;
    }

    @SqsListener(value = "relatorio-fila")
    public void listen(ProcessoRelatorioDto processoRelatorioDto) {
        log.info("Mensagem recebida: {}", processoRelatorioDto);
        ProcessoRelatorio processoRelatorio = processoRelatorioDto.toProcessoRelatorio();
        relatorioPort.criarProcessoRelatorio(processoRelatorio);
    }
}
