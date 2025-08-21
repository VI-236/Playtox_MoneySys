package org.VI__236.services;

import org.VI__236.models.Account;
import org.apache.logging.log4j.*;

import java.util.List;
import java.util.concurrent.*;

public class Starter {

    private static final Logger logger = LogManager.getLogger(Starter.class);
    private final int numOfAccounts;
    private MoneyTransactions moneyTransactions = new MoneyTransactions();
    private AccountsCreator accountCreator = new AccountsCreator();
    private AccountIdGenerator accountIdGenerator = new AccountIdGenerator();
    private RandomAccounts randomAccounts = new RandomAccounts();

    public Starter(){
        numOfAccounts = 3 + (int)(Math.random() * 10);
        logger.info("Начало работы приложения");
        logger.info("В конструктор класса Starter не было передано параметров, приложение начнёт работу " +
                "со случайным количеством аккаунтов");
    }

    public Starter(int numOfAccounts){
        this.numOfAccounts = numOfAccounts;
        logger.info("Начало работы приложения");
    }


    public void starter(){
        logger.debug("Будет создано {} аккаунтов", numOfAccounts);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Account> accountsList = accountCreator.creator(accountIdGenerator.accountsIdGenerator(numOfAccounts));
        int beginingMoneyInSys = 0;
        int endingMoneyInSys = 0;

        for(int b = 0; b < accountsList.size(); b++){
            logger.info("ID аккаунта: {} Сумма на счету: {}",
                    accountsList.get(b).getId(),
                    accountsList.get(b).getMoney());
            beginingMoneyInSys += accountsList.get(b).getMoney();
        }
        logger.info("Всего денег в системе до начал операций: {}", beginingMoneyInSys);

        for (int a = 0; a < 30; a++){
            final int operationNumber = a;

            int sleepTime = 1000 + (int)(Math.random() * 1001);

            executor.submit(() -> {
                try {
                    Thread.sleep(sleepTime);

                    int[] inAndOutAccounts = randomAccounts.randomAccounts(numOfAccounts);
                    int transactionSum = (int)(Math.random() * 10000);

                    logger.debug("Номер операции: {}", operationNumber);
                    logger.debug("Задержка потока: {} мс", sleepTime);
                    logger.debug("Сумма транзакции: {}", transactionSum);
                    logger.debug("Счёт списания: {}", accountsList.get(inAndOutAccounts[0]).getId());
                    logger.debug("Состояние счёта списания: {}", accountsList.get(inAndOutAccounts[0]).getMoney());
                    logger.debug("Счёт зачисления: {}", accountsList.get(inAndOutAccounts[1]).getId());
                    logger.debug("Состояние счёта зачисления: {}", accountsList.get(inAndOutAccounts[1]).getMoney());

                    try {
                        moneyTransactions.moneyTransactions(accountsList.get(inAndOutAccounts[0]),
                                accountsList.get(inAndOutAccounts[1]),
                                transactionSum);

                        logger.debug("Транзакция успешно выполнена");
                        logger.debug("Состояние счёта списания после: {}", accountsList.get(inAndOutAccounts[0]).getMoney());
                        logger.debug("Состояние счёта зачисления после: {}", accountsList.get(inAndOutAccounts[1]).getMoney());

                    } catch (Exception e) {
                        logger.error("Ошибка при выполнении транзакции: {}", e.getMessage(), e);
                    }

                    logger.debug("Операция {} завершена потоком {}", operationNumber, Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    logger.error("Поток был прерван: {}", e.getMessage());
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();


        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                logger.warn("Не все задачи завершились в течение отведенного времени");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.error("Ожидание завершения потоков было прервано: {}", e.getMessage());
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        for(int b = 0; b < accountsList.size(); b++){
            logger.info("ID аккаунта: {} Сумма на счету: {}",
                    accountsList.get(b).getId(),
                    accountsList.get(b).getMoney());
            endingMoneyInSys += accountsList.get(b).getMoney();
        }

        logger.info("Всего денег в системе после операций: {}", endingMoneyInSys);

        if (beginingMoneyInSys != endingMoneyInSys) {
            logger.warn("Обнаружено несоответствие сумм! До транзакций: {}, после: {}", beginingMoneyInSys, endingMoneyInSys);
        } else {
            logger.info("Баланс системы сохранен корректно");
        }

        logger.info("Завершение работы приложения");
    }
}