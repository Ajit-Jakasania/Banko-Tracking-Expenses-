import React from 'react';
import Logo from '../Logo/Logo';

import styles from './Footer.module.css'

import FooterDiv from './FooterDiv'
import GithubLinks from './GithubLinks';

function Footer() {
    return (
        <div className={styles.container}>

            {/* I put this in a separate div such that we can center Banko based on the main Footer div */}
            <div className={styles.containerLeft}>
                <FooterDiv text="About Us!" />
                <Logo />
            </div>

            <GithubLinks />
        </div>
    );
}

export default Footer;
