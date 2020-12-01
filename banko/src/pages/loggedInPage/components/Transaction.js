import React, { useEffect } from 'react';

import $ from 'jquery';

import styles from './Transaction.module.css';
import { useEffect } from 'react';

function Transaction(props) {
    return (
        <div className={styles.Group}>

            <p>This bill is for {props.content} for {props.amount}</p>

            {props.isPaid ? <p>Paid!</p> : <button>Pay transaction</button>}
            <button>Pay bill</button>

        </div>
    )
}

export default Transaction;