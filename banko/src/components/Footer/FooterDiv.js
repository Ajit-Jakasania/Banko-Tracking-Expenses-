import React from 'react';

import styles from './FooterDiv.module.css'


function FooterDiv({ text }) {
    return (
        <div className={styles.FooterDiv}>
            <a>{text}</a>
        </div>
    );
}

export default FooterDiv;
