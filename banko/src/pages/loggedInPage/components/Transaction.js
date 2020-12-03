import React, { useEffect, useState, useContext } from 'react';

import { useForm } from 'react-hook-form';

import $ from 'jquery';
import styles from './Transaction.module.css';
import { Context } from '../../../Store';

function Transaction(props) {
    const [flag, setFlag] = useState(0);
    const [state, setState] = useContext(Context);

    const [jsonData, setJsonData] = useState(new Array());

    const { register, handleSubmit, errors } = useForm(); // initialize the hook

    const payTransaction = (event) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/closeTransaction',
            type: 'POST',
            dataType: 'json',
            data: "{\"transaction_id\": \"" + props.transaction_id + "\"}",
            success: function (data) {

            },
            error: function (request, status, error) {
                console.log(request.responseText);

            }

        });
    }

    if (flag == 0) {
        let jsonData = { "transaction_id": props.transaction_id };
        setJsonData(jsonData);
        setFlag(1);
    }


    return (
        <div className={styles.Transaction}>
            <p>Message: {props.content}  ${props.amount}</p>

            <p>{props.transaction_content} ${props.amount}</p>
            {(props.date_closed == "0000-00-00 00:00:00" || props.date_closed == null) ?
                <div className={styles.Transaction}>This is <b>unpaid:</b> <form onSubmit={handleSubmit(payTransaction)}>
                    <button name="event" value={jsonData} ref={register({ required: true })} > Pay Now</button>
                </form></div> : <div>This is <b>paid</b> on {props.date_closed}</div>
            }

        </div >
    )
}

export default Transaction;