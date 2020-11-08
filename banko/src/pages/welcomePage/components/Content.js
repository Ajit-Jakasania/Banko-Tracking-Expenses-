import React from 'react';

import styles from './Content.module.css'


function Content({ text }) {
    return (
        <div className={styles.Content}>
            <a>{text}</a>
        </div>
    );
}

export default Content;
