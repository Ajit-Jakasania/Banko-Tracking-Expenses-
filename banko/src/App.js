import React, { useContext, useEffect } from 'react';
import { useState } from 'react';

import './App.css';

import Footer from './components/Footer/Footer';
import NavbarLoggedIn from './components/Navbar/NavbarLoggedIn';
import Store from './Store';
import ChooseNavBar from './components/Navbar/ChooseNavBar';


function App() {
  return (
    <Store>
      <div className="App">
        <ChooseNavBar />
        <Footer />
      </div>
    </Store>
  )

}

export default App;



