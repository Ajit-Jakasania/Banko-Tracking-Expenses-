import React from 'react';

import styles from './FooterDiv.module.css'


function FooterDiv(props) {
    return (
        <div className={styles.FooterDiv}>
            <a href={props.gitLink}><i className="fab fa-github" ></i>{props.text}</a>
        </div>
    );
}

export default FooterDiv;
