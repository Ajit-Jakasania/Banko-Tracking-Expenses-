import React, { useEffect } from 'react';
import { useState } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import NavbarDefault from '../../components/Navbar/NavbarDefault'
import PageContent from './components/PageContent';
import Footer from '../../components/Footer/Footer'
import $ from "jquery"
import RegisterPage from './../registerPage/RegisterPage'




function WelcomePage({ props }) {




    return (
        <div>
            <PageContent />
        </div>
    );
}


export default WelcomePage;