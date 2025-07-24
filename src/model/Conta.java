package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conta {
    private int id;
    private String nome;
    private Long cpf;
    private double saldo;
    private String senha;
    private static int proximoId = 0;

    public Conta() {
    }

    public Conta(String nome, Long cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        proximoId++;
        this.id = proximoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id
                + ", " + nome
                + ", " + cpf
                + ", " + saldo;
    }

    public String consultarSaldo() {
        return String.format("%.2f", saldo);
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean podeDepositar(double valor) {
        if (valor < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public boolean podeSacar(double valor) {
        if (valor > saldo || valor < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void transferir(int idDestinatario, double valor, ArrayList<Conta> contas, ArrayList<Transferencia> transferencias, int idTransferencia) {
        sacar(valor);
        contas.get(idDestinatario - 1).depositar(valor);
        Transferencia transferencia = new Transferencia(valor, idDestinatario, id, LocalDateTime.now(), idTransferencia);
        transferencias.add(transferencia);
    }

    public boolean podeTransferir(int idDestinatario, double valor, ArrayList<Conta> contas) {
        if (valor > saldo || idDestinatario <= 0 || id == idDestinatario || idDestinatario > proximoId || !consultarId(idDestinatario, contas)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean consultarId(int id, ArrayList<Conta> contas) {
        for (int i = 0; i < contas.size(); i++) {
            if (id == contas.get(i).getId()) {
                return true;
            }
        }
        return false;
    }
}




