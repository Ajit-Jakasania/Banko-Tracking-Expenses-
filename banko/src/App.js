<<<<<<< HEAD
import React from 'react';
=======
import React, { useContext, useEffect } from 'react';
>>>>>>> 3514149a93d2f856996beb473dd1e466e1d8b3f2
import { useState } from 'react';

import './App.css';

import Footer from './components/Footer/Footer';
import NavbarLoggedIn from './components/Navbar/NavbarLoggedIn';
import Store from './Store';
import ChooseNavBar from './components/Navbar/ChooseNavBar';


function App() {
  return (
<<<<<<< HEAD
    <div className="App">

        <NavbarLoggedIn />
=======
    <Store>
      <div className="App">
        <ChooseNavBar />
>>>>>>> 3514149a93d2f856996beb473dd1e466e1d8b3f2
        <Footer />
      </div>
    </Store>
  )
<<<<<<< HEAD
=======

>>>>>>> 3514149a93d2f856996beb473dd1e466e1d8b3f2
}

export default App;



