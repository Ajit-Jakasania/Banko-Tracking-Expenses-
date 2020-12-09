import React, { useContext, useEffect, useState } from 'react';

import $ from 'jquery';
import Transaction from './Transaction';
import styles from './TransactionsFeed.module.css';
import { Context } from '../../../Store';


function TransactionsFeed() {

    const [transactions, setTransactions] = useState(new Array());
    const [state, setState] = useContext(Context);
    const [flag, setFlag] = useState(0);

    //write java for this

    function getTransactions() {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/getTransactions',
            type: 'get',
            dataType: 'json',
            data: { user_id: state.id },
            success: function (data) {

                setTransactions(data);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });
    }
    if (flag == 0) {
        getTransactions();
        setFlag(1);
    }


    if (flag == 1) {
        return (
            <div className={styles.TransactionsFeed}>

                <div className={styles.Transactions}>
                    {transactions.map(transaction => (

                        <Transaction amount={transaction.amount} transaction_id={transaction.transaction_id} content={transaction.transaction_content} date_closed={transaction.date_closed} />
                    ))}
                </div>
            </div>
        )
    } else {
        return (<p>no transactions!</p>)
    }

}

export default TransactionsFeed;