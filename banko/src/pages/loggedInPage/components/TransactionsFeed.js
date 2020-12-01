import React from 'react';

import styles from './TransactionsFeed.module.css';


function TransactionsFeed() {

    const transactions = new Array();

    //write java for this
    useEffect(() => {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/getTransactions',
            type: 'get',
            dataType: 'json',
            data: { user_id: context.user_id },
            success: function (data) {

                var i = 0;

                $.each(data, function (key) {

                    transactions[i] = key;
                    i++;

                });

            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
    })
    return (
        <div className={styles.TransactionsFeed}>

            <div className={styles.Transactions}>
                {transactions.map(transaction => (
                    <Transaction content={transaction[0]} amount={transaction[1]} transaction_id={transactions[2]} isPaid={transactions[3]} />
                ))}
            </div>
        </div>
    )
}

export default TransactionsFeed;