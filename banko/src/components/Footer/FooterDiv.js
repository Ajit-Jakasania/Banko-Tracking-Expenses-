import React from 'react';

import styles from './FooterDiv.module.css'


function FooterDiv({ text }) {
    return (
        <div className={styles.FooterDiv}>
            <a><i class="fab fa-github"></i>{text}</a>
        </div>
    );
}

export default FooterDiv;
