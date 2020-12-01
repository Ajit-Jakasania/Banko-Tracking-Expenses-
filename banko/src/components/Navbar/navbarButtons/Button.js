import React from 'react';
import App from '../../../App';


import styles from './Button.module.css';

function Button({ text, link }) {
    return (
        <div className={styles.Button}>
            <a className={styles.active} href={link}>{text}</a>

        </ div>
    );
}

export default Button;
