package br.unipar.central.services;

import br.unipar.central.model.Transacao;
import br.unipar.central.model.Conta;
import br.unipar.central.repositories.TransacaoDAO;
import java.sql.SQLException;
import java.util.List;

public class TransacaoService {

    private TransacaoDAO transacaoDAO;
    private ContaService contaService;

    public TransacaoService() {
        this.transacaoDAO = new TransacaoDAO();
        this.contaService = new ContaService();
    }

    public List<Transacao> findAll() throws SQLException {
        return transacaoDAO.findAll();
    }

    public Transacao findById(int id) throws SQLException {
        return transacaoDAO.findById(id);
    }

    public void deposit(Conta conta, double valor) throws SQLException {

        conta.setSaldo(conta.getSaldo() + valor);
        contaService.updateConta(conta);
    }

    public void withdraw(Conta conta, double valor) throws SQLException {

        if (conta.getSaldo() < valor) {
            throw new SQLException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        contaService.updateConta(conta);
    }

    public void transfer(Conta contaOrigem, Conta contaDestino, double valor) throws SQLException {

        if (contaOrigem.getSaldo() < valor) {
            throw new SQLException("Saldo insuficiente na conta de origem");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        contaService.updateConta(contaOrigem);
        contaService.updateConta(contaDestino);
    }

    public void insert(Transacao transacao) throws SQLException {

        Conta contaOrigem = transacao.getContaOrigem();
        if (contaOrigem.getSaldo() < transacao.getValor()) {
            throw new SQLException("Saldo insuficiente na conta de origem");
        }


        contaOrigem.setSaldo(contaOrigem.getSaldo() - transacao.getValor());
        contaService.updateConta(contaOrigem);
        transacaoDAO.insert(transacao);
    }

    public void update(Transacao transacao) throws SQLException {

        Conta contaOrigem = transacao.getContaOrigem();
        if (contaOrigem.getSaldo() < transacao.getValor()) {
            throw new SQLException("Saldo insuficiente na conta de origem");
        }


        contaOrigem.setSaldo(contaOrigem.getSaldo() - transacao.getValor());
        contaService.updateConta(contaOrigem);
        transacaoDAO.update(transacao);
    }

    public void delete(int id) throws SQLException {

        Transacao transacao = transacaoDAO.findById(id);

        if (transacao != null) {

            Conta contaOrigem = contaService.getContaById(transacao.getContaOrigem().getId());

            if (contaOrigem != null) {

                double novoSaldo = contaOrigem.getSaldo() + transacao.getValor();
                contaOrigem.setSaldo(novoSaldo);


                contaService.updateConta(contaOrigem);
            }
        }


        transacaoDAO.delete(id);
    }


    public List<Transacao> findTransactionsByAccountId(int accountId) throws SQLException {
        return transacaoDAO.findTransactionsByAccountId(accountId);
    }

    public double calculateAccountBalance(Conta conta) throws SQLException {
        List<Transacao> transacoes = findTransactionsByAccountId(conta.getId());
        double saldo = 0;
        for (Transacao transacao : transacoes) {
            saldo += transacao.getValor();
        }
        return saldo;
    }
}