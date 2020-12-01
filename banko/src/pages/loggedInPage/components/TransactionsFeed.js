import React, { useContext, useEffect } from 'react';
import $ from 'jquery'
import Transaction from './Transaction'
import styles from './TransactionsFeed.module.css';
import { Context } from '../../../Store';


function TransactionsFeed() {

    const transactions = new Array();
    const [state, useState] = useContext(Context);

    //write java for this
    useEffect(() => {
        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/getTransactions',
            type: 'get',
            dataType: 'json',
            //im assuming instead of context.user_id you mean state.id for the global Id variable?
            //data: { user_id: context.user_id },
            data: { user_id: state.id },
            success: function (data) {

                var i = 0;
                console.log(data);

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
                <p>We are mapping zero transactions atm.</p>
                {transactions.map(transaction => (
                    <Transaction content={transaction[0]} amount={transaction[1]} transaction_id={transactions[2]} isPaid={transactions[3]} />
                ))}
            </div>
        </div>
    )
}

export default TransactionsFeed;