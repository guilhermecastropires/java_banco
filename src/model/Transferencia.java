package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Transferencia {
    private double valor;
    private int idDestinatario;
    private int idPagante;
    private int idTranferencia;
    private LocalDateTime dateTime;

    public Transferencia(double valor, int idDestinatario, int idPagante, LocalDateTime dateTime, int idTransferencias) {
        this.valor = valor;
        this.idDestinatario = idDestinatario;
        this.idPagante = idPagante;
        this.dateTime = dateTime;
        this.idTranferencia = idTransferencias;
    }


    public int getIdPagante() {
        return idPagante;
    }

    public String toString() {
        return "ID Da transferencia: "
                + idTranferencia
                + String.format("%n")
                + "Valor: R$ "
                + String.format("%.2f%n", valor)
                + "ID do destinatario: "
                + idDestinatario
                + String.format("%n")
                + "ID do Pagante: "
                + idPagante
                + String.format("%n")
                + "Data e Hora: "
                + dateTime;
    }
}
