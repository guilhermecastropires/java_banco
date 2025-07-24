package main;

import model.Conta;
import model.Transferencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        ArrayList<Conta> contas = new ArrayList<>();
        ArrayList<Transferencia> transferencias = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int indiceLogado = -1;
        int idTransferencias = 0;

        while (true) {
            while (indiceLogado == -1) {
                System.out.println();
                System.out.println("Bem Vindo ao Bank Master Pro, seu banco digital!");
                System.out.println("1 - Criar conta");
                System.out.println("2 - Fazer login");
                System.out.print("Escolha uma das opçoes acima: ");
                int escolha1 = sc.nextInt();
                sc.nextLine();

                switch (escolha1) {
                    case 1:
                        boolean senhaCorreta = false;
                        do {
                            System.out.println();
                            System.out.print("Digite seu nome completo: ");
                            String nome = sc.nextLine();
                            System.out.print("Digite seu CPF: ");
                            Long cpf = sc.nextLong();
                            sc.nextLine();
                            System.out.println("Digite sua senha:");
                            String senha1 = sc.nextLine();
                            System.out.println("Repita sua senha:");
                            String senha2 = sc.nextLine();

                            if (senha1.equals(senha2)) {
                                senhaCorreta = true;
                                Conta conta = new Conta(nome, cpf, senha1);
                                contas.add(conta);
                                System.out.println("Conta Criada com sucesso!");
                            } else {
                                System.out.println("As senhas nao coincidem");
                            }
                        } while (!senhaCorreta);
                        break;

                    case 2:
                        boolean logado = false;
                        do {
                            System.out.print("Digite seu CPF: ");
                            Long cpf = sc.nextLong();
                            sc.nextLine();
                            System.out.println("Digite sua senha:");
                            String senha1 = sc.nextLine();

                            while (!logado) {
                                for (int i = 0; i < contas.size(); i++) {
                                   if (contas.get(i).getCpf().equals(cpf)) {
                                       if (contas.get(i).getSenha().equals(senha1)) {
                                           System.out.println("Logado com sucesso!");
                                           indiceLogado = i;
                                           logado = true;
                                           break;
                                       }
                                   }
                                }

                                if (logado) {
                                    break;
                                } else {
                                    System.out.println("Tente novamente.");
                                } break;
                            }
                        } while (!logado);
                        break;

                    default:
                        System.out.println("Escolha uma opção valida!");
                        break;
                }
            }

            while (indiceLogado != -1) {
                System.out.println();
                System.out.println("Bem Vindo a sua conta, " + contas.get(indiceLogado).getNome() );
                System.out.println("1 - Informações da conta");
                System.out.println("2 - Consultar saldo");
                System.out.println("3 - Depositar");
                System.out.println("4 - Sacar");
                System.out.println("5 - Transferir");
                System.out.println("6 - Listar Minhas Transferencias");
                System.out.println("7 - Sair da conta");
                System.out.println("8 - Excluir conta");
                System.out.print("Escolha uma das opçoes acima: ");
                int opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Informações da conta:");
                        System.out.println("Nome: " + contas.get(indiceLogado).getNome());
                        System.out.println("CPF: " + contas.get(indiceLogado).getCpf());
                        System.out.println("Id: " + contas.get(indiceLogado).getId());
                        break;

                    case 2:
                        System.out.println("O Seu saldo atual é: R$ " + contas.get(indiceLogado).consultarSaldo());
                        break;

                    case 3:
                        System.out.print("Digite o valor que deseja depositar: ");
                        double valorDeposito = sc.nextDouble();

                        if (contas.get(indiceLogado).podeDepositar(valorDeposito)) {
                            contas.get(indiceLogado).depositar(valorDeposito);
                            System.out.println("Deposito efetuado com sucesso!");
                        } else {
                            System.out.println("Deposito não efetuado.");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o valor que deseja sacar: R$ ");
                        double valorSaque = sc.nextDouble();
                        if (contas.get(indiceLogado).podeSacar(valorSaque)) {
                            contas.get(indiceLogado).sacar(valorSaque);
                            System.out.println("Saque efetuado com sucesso!");
                        } else {
                            System.out.println("Erro no saque!");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o id da conta que deseja transferir: ");
                        int id = sc.nextInt();
                        System.out.print("Digite o valor que deseja transferir: ");
                        double valorTransferencia = sc.nextDouble();
                        if (contas.get(indiceLogado).podeTransferir(id, valorTransferencia, contas)) {
                            contas.get(indiceLogado).transferir(id, valorTransferencia, contas, transferencias, idTransferencias++);
                            System.out.println("Transferencia efetuada. ");
                        } else {
                            System.out.println("Erro na transferencia.");
                        }
                        break;

                    case 6:
                        System.out.println("Suas Transferencias: ");
                        boolean encontrou = false;

                        for (int i = 0; i < idTransferencias; i++) {
                            System.out.println();
                            if (transferencias.get(i).getIdPagante() == contas.get(indiceLogado).getId() ) {
                                System.out.println(transferencias.get(i));
                                encontrou = true;
                            }
                        }
                        if (!encontrou) {
                            System.out.println("Nenhuma transferência no seu nome.");
                        }
                        break;

                    case 7:
                        System.out.println("Saindo da conta...");
                        indiceLogado = -1;
                        break;

                    case 8:
                        System.out.println("Realmente deseja excluir a conta?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");
                        System.out.print("Confirme a escolha: ");
                        int opcaoExcluir = sc.nextInt();
                        switch (opcaoExcluir) {
                            case 1:
                                contas.remove(indiceLogado);
                                System.out.println("Conta Excluida");
                                indiceLogado = -1;
                                break;

                            case 2:
                                System.out.println("Operação cancelada");
                                break;
                            default:
                                System.out.println("Escolha uma opção valida.");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Escolha uma opção valida.");
                        break;
                }


            }
        }
    }
}

